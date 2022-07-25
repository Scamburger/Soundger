package ru.scamburger.Soundger.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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

