package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.e_request.services.FileStorageService;

import java.io.IOException;

@Controller
@RequestMapping("/e_request")
public class FilesController {

    private final FileStorageService fileStorageService;


    public FilesController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/upload")
    public String renderPage(){
        return "e_request/view/uploadfile";
    }

    @PostMapping("/upload_file")
    public String saveFiles(@RequestParam("file") MultipartFile file, Model model, @RequestParam(name = "userId") long userId, @RequestParam(name = "eventId") long eventId) {
        if (file.isEmpty() || file.getOriginalFilename()==null) {
            model.addAttribute("error", "errors.fileEmpty");
            return "redirect:/e_request/upload";
        }
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1);
        if(extension.isEmpty()) extension="txt";
        try {
            fileStorageService.saveFile(userId, eventId, file.getBytes(), extension);
        } catch (IOException e) {
            model.addAttribute("error", "errors.invalidFile");
            return "redirect:/e_request/upload";
        }
        return "redirect:/e_request/upload";
    }
}
