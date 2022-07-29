package ru.scamburger.Soundger.service.FileStorage;

import org.hibernate.cfg.NotYetImplementedException;

// Может быть начнем когда нибудь хранить файлы в облаке
public class CloudFileStorageProvider implements FileStorageProvider {

    @Override
    public byte[] getFile(long id) {
        throw new NotYetImplementedException();
    }

    @Override
    public boolean saveFile(long id, byte[] content) {
        throw new NotYetImplementedException();
    }

}
