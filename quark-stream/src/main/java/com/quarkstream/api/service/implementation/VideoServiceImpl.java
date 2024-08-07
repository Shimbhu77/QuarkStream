package com.quarkstream.api.service.implementation;

import com.quarkstream.api.model.Video;
import com.quarkstream.api.repository.VideoRepository;
import com.quarkstream.api.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video uploadVideo(Video video, MultipartFile multipartFile) {
        return null;
    }
}
