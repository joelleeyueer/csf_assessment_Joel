package ibf2022.batch2.csf.backend.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import ibf2022.batch2.csf.backend.models.Archives;

@Service
public class UploadToS3Service {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${do.spaces.region}")
    private String region;

    public Archives upzipAndUpload(MultipartFile incomingFile) throws IOException{

        Archives archives = new Archives();

        archives.setBundleId(generateUUID());
        archives.setDate(LocalDate.now());
        

        HashSet<String> fileNameSet = new HashSet<>();
        byte[] buffer = new byte[1024];
        
        try (ZipInputStream zis = new ZipInputStream(incomingFile.getInputStream())) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                //check if its a file
                if (!zipEntry.isDirectory()) {

                    //check if file name already exists
                    if (fileNameSet.contains(fileName)) {
                        System.out.println("Duplicate file name " + fileName + ". Will be keeping the old one.");
                        continue;
                    } else {
                        fileNameSet.add(fileName);
                    }

                    ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        bAOS.write(buffer, 0, len);
                    }
                    byte[] incomingFileBytes = bAOS.toByteArray();
                    ObjectMetadata currentPhotoMetadata = new ObjectMetadata(); 
                    currentPhotoMetadata.setContentLength(incomingFileBytes.length);
                    String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
                    String contentType = getContentType(fileExtension);

                    if (contentType == null) {
                        System.out.println("File type not supported for " + fileName + ".");
                        continue;
                    }
                    currentPhotoMetadata.setContentType(contentType);
                    currentPhotoMetadata.addUserMetadata("FileName", fileName);

                    // Call uploadToS3 method passing fileBytes and metadata
                    uploadToS3(new ByteArrayInputStream(incomingFileBytes), currentPhotoMetadata, fileName);
                    String incomingUrl = generateImageUrl(fileName);
                    archives.addImageUrl(incomingUrl);

                    bAOS.close();
                }
                zipEntry = zis.getNextEntry();

            }
        }

        return archives;
    }

    private String getContentType(String fileExtension) {
        switch (fileExtension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            default:
                return null;
        }
    }

    private void uploadToS3(InputStream fileBytes, ObjectMetadata currentPhotoMetadata, String fileName) {
        try {
            // Upload to S3
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileBytes, currentPhotoMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
            System.out.println("Upload to S3 successful");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private String generateImageUrl(String fileName) {
        String imageUrl = "https://" + bucketName + "." + s3Client.getRegionName() + ".digitaloceanspaces.com/" + fileName;
        System.out.println("Image URL: " + imageUrl);
        return imageUrl;
    }

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().substring(0,8);
        return randomUUIDString;
    }
    

}

    
