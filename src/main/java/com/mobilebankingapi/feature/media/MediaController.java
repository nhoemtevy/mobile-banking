package com.mobilebankingapi.feature.media;

import com.mobilebankingapi.feature.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestPart MultipartFile file) {
        return mediaService.uploadSingle(file, "IMAGE");
    }

    @PostMapping("/upload-multiple")
    List<MediaResponse> uploadMultiple(@RequestPart List<MultipartFile> files) {
        return mediaService.uploadMultiple(files, "IMAGE");
    }

    @GetMapping("/{mediaName}")
    MediaResponse loadMediaByName(@PathVariable String mediaName){
        return mediaService.loadMediaByName(mediaName, "IMAGE");
    }
    @DeleteMapping("/{mediaName}")
    MediaResponse deletedMediaByName(@PathVariable String mediaName){
        return mediaService.deletedByName(mediaName, "IMAGE");
    }

}
