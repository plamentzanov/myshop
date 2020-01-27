package com.myshop.services.services.implementations;

import com.cloudinary.Cloudinary;
import com.myshop.services.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public String upload(MultipartFile image) throws IOException {
        File file = File.createTempFile("temp-file", image.getOriginalFilename());
        image.transferTo(file);
        return this.cloudinary.uploader().upload(file, new HashMap())
                .get("url")
                .toString();
    }
}
