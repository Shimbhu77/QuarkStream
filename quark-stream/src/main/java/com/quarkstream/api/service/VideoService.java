package com.quarkstream.api.service;

import com.quarkstream.api.exceptions.VideoException;
import com.quarkstream.api.model.Video;
import com.quarkstream.api.model.dto.VideoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    Video uploadVideo(VideoDTO videoDTO, MultipartFile file) throws VideoException;
    Video getVideoByVideoId(Integer videoId) throws VideoException;
    List<Video> getAllVideos();
}
