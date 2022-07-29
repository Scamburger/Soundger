package ru.scamburger.Soundger.service.FileStorage;

public interface FileStorageProvider {

    byte[] getFile(long id);

    boolean saveFile(long id, byte[] content);

}
