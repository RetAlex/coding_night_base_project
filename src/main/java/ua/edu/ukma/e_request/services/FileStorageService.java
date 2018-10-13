package ua.edu.ukma.e_request.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.e_request.resources.Resources;

import javax.mail.Multipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileStorageService {

    public String saveFile(long userId, long eventId, byte[] bytes, String fileName) {
            String filePath = Resources.filePathPrefix + "/" + userId + "/" + eventId;
        try (FileOutputStream fos = new FileOutputStream("pathname")) {
            fos.write(bytes);
        } catch (IOException ioe)
        {

        }
        return "";
    }
}
