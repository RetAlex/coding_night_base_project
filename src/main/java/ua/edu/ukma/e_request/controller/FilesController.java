package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.e_request.services.FileStorageService;

import java.io.IOException;

@RestController
public class FilesController {

    private final FileStorageService fileStorageService;


    public FilesController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/uploadfile")
    public Model saveFiles(@RequestParam("file") MultipartFile file, Model model, @RequestParam long userId, long eventId) {
        if (file.isEmpty()) {
            model.addAttribute("error", "File is empty!");
            return model;
        }
        try {
            fileStorageService.saveFile(userId, eventId, file.getBytes(), file.getOriginalFilename());
        } catch (IOException ioe) {

        }
        return null;
    }
}
