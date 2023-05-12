package ibf2022.batch2.csf.backend.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> handleFileUpload(
			@RequestPart("archive") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("title") String title,
			@RequestParam("comments") String comments) {
			
		// Process the uploaded file
		if (!file.isEmpty()) {
		// Process the file here
		// You can access the file using 'file.getInputStream()' or other operations

		// Process other data fields
		System.out.println("Name: " + name);
		System.out.println("Title: " + title);
		System.out.println("Comments: " + comments);

		return ResponseEntity.ok("File and data uploaded successfully");
		} else {
		return ResponseEntity.badRequest().body("No file uploaded");
		}
	}
}
