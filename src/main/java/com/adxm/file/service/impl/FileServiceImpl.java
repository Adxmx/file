package com.adxm.file.service.impl;

import com.adxm.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl extends FileService {

    @Value("${file.file-host}")
    private String fileHost;

    @Value("${file.file-path}")
    private String filePath;

    @Value("${file.file-folder-mapping}")
    private String fileFolderMapping;

    @Override
    public String fileUpload(MultipartFile file) {
        String url = null;
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String fileSuffix = split[split.length - 1];
        String fileName = UUID.randomUUID().toString().replace("-","") + "." + fileSuffix;

        // 判断文件存储路径是否存在
        File fileFolder = new File(filePath);
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }

        // 文件存储路径
        String path = filePath + fileName;
//        try {
//            file.transferTo(new File(path));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return url;
//        }

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = file.getInputStream();
            fileOutputStream = new FileOutputStream(new File(path));
            FileCopyUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        url = fileHost + fileFolderMapping + fileName;
        return url;
    }
}
