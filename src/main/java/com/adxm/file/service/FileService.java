package com.adxm.file.service;

import org.springframework.web.multipart.MultipartFile;

public abstract class FileService {

    public abstract String fileUpload(MultipartFile file);

}
