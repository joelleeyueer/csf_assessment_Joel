package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Archives;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;
import ibf2022.batch2.csf.backend.services.FileVerificationService;

@RestController
public class UploadController {

	@Autowired
	private FileVerificationService fileVerificationService;

	@Autowired
	private ImageRepository imageRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> handleFileUpload(
			@RequestPart("archive") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("title") String title,
			@RequestParam("comments") String comments) throws IOException{
			
		// Process the uploaded file
		if (!file.isEmpty() && file.getContentType().equals("application/zip")) {
		List<String> uploadedFiles = new ArrayList<>();
		// Process the file here
		System.out.println("Name: " + name);
		System.out.println("Title: " + title);
		System.out.println("Comments: " + comments);

		try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    String fileName = entry.getName();

                    if (fileVerificationService.isImageFile(fileName)) {
                        // Process the image file here (e.g., save it, extract metadata, etc.)
                        // You can add the file name or other details to the uploadedFiles list
						System.out.println("File name: " + fileName);
                        uploadedFiles.add(fileName);
                    }
                }
            }
        }

		Archives incomingArchives = imageRepository.upload(file);
		incomingArchives.setTitle(title);
		incomingArchives.setName(name);
		incomingArchives.setComments(comments);


		return ResponseEntity.ok("File and data uploaded successfully");
		} else {
		return ResponseEntity.badRequest().body("No file uploaded");
		}
	}
}
