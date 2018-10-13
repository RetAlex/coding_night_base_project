package ua.edu.ukma.e_request.services;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.e_oss.config.EOssApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EOssApplication.class)
public class FileStorageServiceTest {

    @Autowired
    public FileStorageService fileStorageService;


    @Test
    public void saveFile() {
        try {
            File file = new File("abc.txt");
            file.createNewFile();
            //init array with file length
            byte[] bytesArray = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
            fileStorageService.saveFile(1233, 12312, bytesArray, file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}