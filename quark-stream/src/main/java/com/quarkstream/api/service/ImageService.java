package com.quarkstream.api.service;

import com.quarkstream.api.exceptions.ImageException;
import com.quarkstream.api.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image uploadImage(Image image, MultipartFile multipartFile) throws ImageException;
    Image getImageByImageId(Integer imageId) throws ImageException;
}
