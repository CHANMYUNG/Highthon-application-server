package com.highthon.highthon3server.service.storage;

import com.highthon.highthon3server.exception.FilesAreEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${storage.baseDir}")
    private String baseDir;

    public Map<String, String> uploadFiles(MultipartFile[] files) {
        System.out.println(getClass().getName());
        Map<String, String> filenameMap = new HashMap<>();

        if (files == null || !(files.length > 0)) throw new FilesAreEmptyException();

        for (MultipartFile file : files) {
            try {
                if (file.isEmpty()) continue;
                String filename = file.getOriginalFilename();

                byte[] bytes = file.getBytes();

                String uploadedFilename = UUID.randomUUID().toString().replace("-", "") + getExtensionFromFilename(filename);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(baseDir + uploadedFilename)));
                stream.write(bytes);
                stream.close();
                filenameMap.put(filename, uploadedFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (filenameMap.size() == 0) throw new FilesAreEmptyException();

        return filenameMap;
    }

    private String getExtensionFromFilename(String filename) {
        int idx = filename.lastIndexOf(".");
        if (idx == -1) return "";
        return filename.substring(idx);
    }

    public File downloadFile(String filename) {
        return new File(baseDir + filename);
    }
}
