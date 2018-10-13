package ua.edu.ukma.e_request.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

@Service
public class FileStorageService {

    public String saveFile(long userId, long eventId, MultipartFile file) {

        return "";
    }
}
