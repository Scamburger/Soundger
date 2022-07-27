package ru.scamburger.Soundger.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileService {

    public static byte[] returnContent(String path) throws IOException {
        byte[] array = Files.readAllBytes(new File(path).toPath());
        return array;
    }

    public static void saveFileInDirectory(byte[] array, String path) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(array);
        }
    }

}
