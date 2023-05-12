package ibf2022.batch2.csf.backend.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileVerificationService {

    public boolean isImageFile(String fileName) {
        List<String> supportedExtensions = Arrays.asList(".jpg", ".jpeg", ".png", ".gif");
        String extension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
        return supportedExtensions.contains(extension);
    }
    
    
}
