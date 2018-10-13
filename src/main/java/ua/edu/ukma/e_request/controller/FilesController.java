package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.e_request.services.FileStorageService;

@RestController
public class FilesController {

    private final FileStorageService fileStorageService;


    public FilesController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/uploadfile")
    public String saveFiles(@RequestParam("file") MultipartFile file) {
       String fileName = fileStorageService.saveFile(1234, 1234, file);
       return "/";
    }
}
