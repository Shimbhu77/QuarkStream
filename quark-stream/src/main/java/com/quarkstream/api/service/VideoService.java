package com.quarkstream.api.service;

import com.quarkstream.api.model.Video;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    public Video uploadVideo(Video video, MultipartFile multipartFile);
}
