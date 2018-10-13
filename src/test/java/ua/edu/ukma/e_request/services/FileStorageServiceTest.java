package ua.edu.ukma.e_request.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
public class FileStorageServiceTest {

    @Autowired
    private final FileStorageService fileStorageService;

    public FileStorageServiceTest(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Test
    public void saveFile() {
        File testingFile = new File("kovalenko.txt");

        try {
            File file = new File("abc.txt");
            //init array with file length
            byte[] bytesArray = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
            fileStorageService.saveFile(1233, 12312, bytesArray, testingFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}