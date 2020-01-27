package com.myshop.services.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String upload(MultipartFile image) throws IOException;
}

