package com.quarkstream.api.controller;

import com.quarkstream.api.model.Video;
import com.quarkstream.api.model.dto.VideoDTO;
import com.quarkstream.api.repository.VideoRepository;
import com.quarkstream.api.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/rest/video/v1")
@Tag(name = "Video API docs", description = "Video Management")
public class VideoController {

    @Autowired
    private VideoService videoService;


    @Operation(summary = "Upload video using this endpoint.")
    @PostMapping(path = "/upload-video",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Video> uploadVideo(@RequestParam("title") String title,@RequestParam("description") String description, @RequestPart("file") MultipartFile file)
    {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setVideoTitle(title);
        videoDTO.setVideoDescription(description);

        Video video  = videoService.uploadVideo(videoDTO,file);

        return new ResponseEntity<Video>(video, HttpStatus.CREATED);
    }


    @Operation(summary = "Stream video using this endpoint which sends all data once")
    @GetMapping(path = "/public/stream-video/{videoId}")
    public ResponseEntity<Resource> streamVideo(@PathVariable("videoId") Integer videoId)
    {

        Video video  = videoService.getVideoByVideoId(videoId);

        String filePath = video.getVideoUrl();

        if(Objects.isNull(video.getVideoType()))
        {
            video.setVideoType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        }

        Resource resource = new FileSystemResource(filePath);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(video.getVideoType())).body(resource);
    }
}
