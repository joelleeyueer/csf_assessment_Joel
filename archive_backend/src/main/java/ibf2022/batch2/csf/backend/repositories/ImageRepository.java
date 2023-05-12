package ibf2022.batch2.csf.backend.repositories;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.services.UploadToS3Service;

@Repository
public class ImageRepository {

	@Autowired
	private UploadToS3Service uploadToS3Service;

	//TODO: Task 3
	// You are free to change the parameter and the return type
	// Do not change the method's name
	public void upload(MultipartFile incomingZMultipartFile) throws IOException{
		uploadToS3Service.unzipFile(incomingZMultipartFile);
	}
}
