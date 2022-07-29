package ru.scamburger.Soundger.service.FileStorage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PhysicalFileStorageProvider implements FileStorageProvider {

    // todo: move this path to configuration
    private final String StorageDirectory = "C:\\SoundgerData\\";
    private final String FileExtension = ".mp3";

    public byte[] getFile(long id) {
        try {
            return Files.readAllBytes(getFullPath(id));
        } catch (IOException e) {
            return null;
        }
    }

    public boolean saveFile(long id, byte[] content) {
        String path = getFullPath(id).toString();
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(content);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private Path getFullPath(long id) {
        String fileName = String.valueOf(id) + FileExtension;
        return Paths.get(StorageDirectory, fileName);
    }
}
