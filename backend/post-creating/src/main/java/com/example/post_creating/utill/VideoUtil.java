package com.example.post_creating.utill;

import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class VideoUtil {
    public static Duration getVideoDuration(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("video", ".tmp");
        try {
            file.transferTo(tempFile);
            FFprobe ffprobe = new FFprobe("ffprobe"); // Ensure ffprobe is in PATH
            FFmpegProbeResult probeResult = ffprobe.probe(tempFile.getAbsolutePath());
            return Duration.ofSeconds((long) probeResult.getFormat().duration);
        } finally {
            tempFile.delete();
        }
    }
}
