package ua.edu.ukma.e_request.services;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.resources.E_RequestResources;
import ua.edu.ukma.e_request.utils.logger.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileStorageService {
    private final E_RequestResources e_requestResources;

    public FileStorageService(E_RequestResources e_requestResources) {
        this.e_requestResources = e_requestResources;
    }

    public void saveFile(long userId, long eventId, byte[] bytes, String extension) throws IOException {
        FileUtils.writeToFile(e_requestResources.getFilePathPrefix() + "/" + userId + "/" + eventId+"."+extension, new String(bytes));
    }
}
