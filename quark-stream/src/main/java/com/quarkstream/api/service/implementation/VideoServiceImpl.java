package com.quarkstream.api.service.implementation;

import ch.qos.logback.core.util.StringUtil;
import com.quarkstream.api.exceptions.VideoException;
import com.quarkstream.api.model.Video;
import com.quarkstream.api.model.dto.VideoDTO;
import com.quarkstream.api.repository.VideoRepository;
import com.quarkstream.api.service.VideoService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Value("${s3.video}")
    String directory;

    @Autowired
    private VideoRepository videoRepository;


    @PostConstruct
    public void init()
    {
        File file = new File(directory);

        if(!file.exists())
        {
            file.mkdir();

            log.info(" Videos folder is created for storing uploaded videos.");
        }
        else
        {
            log.info("Videos folder is already exists.");
        }
    }

    @Override
    public Video uploadVideo(VideoDTO videoDTO, MultipartFile file) throws VideoException {

        try{

            Video video = new Video();

            // extracting data from file

            String fileOriginalFilename = file.getOriginalFilename();

            String fileType = file.getContentType();

            InputStream inputStream = file.getInputStream();


            // cleaning names of file and folder

            String cleanFileOriginalName = StringUtils.cleanPath(fileOriginalFilename);

            String cleanFolderName = StringUtils.cleanPath(directory);

            // generating filePath

            Path path = Paths.get(cleanFolderName, cleanFileOriginalName);

            // saving video to folder

            Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);


            // creating videos information

            video.setVideoType(fileType);
            video.setVideoOriginalName(fileOriginalFilename);
            video.setVideoTitle(videoDTO.getVideoTitle());
            video.setVideoDescription(videoDTO.getVideoDescription());
            video.setVideoUrl(path.toString());
            video.setUploadedTs(LocalDateTime.now());

            return videoRepository.save(video);



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        throw new VideoException("Unable to upload video, some technical error occurs.");
    }

    @Override
    public Video getVideoByVideoId(Integer videoId) throws VideoException {

        Optional<Video> optionalVideo = videoRepository.findById(videoId);

        return optionalVideo.orElseThrow(() -> new VideoException("Cannot find video with id: " + videoId));
    }

    @Override
    public List<Video> getAllVideos() {
        return List.of();
    }
}
