package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.exception.FilesAreEmptyException;
import com.highthon.highthon3server.exceptionHandler.ErrorResponse;
import com.highthon.highthon3server.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/upload")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> uploadFile(@RequestParam("files") MultipartFile[] files) {
        return storageService.uploadFiles(files);
    }

    @GetMapping("/upload/{filename}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("filename") String filename) throws IOException {
        String mediaType = URLConnection.guessContentTypeFromName(filename);
        mediaType = mediaType != null ? mediaType : "application/octet-stream";

        File file = storageService.downloadFile(filename);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"")
                .contentType(MediaType.parseMediaType(mediaType))
                .contentLength(file.length())
                .body(resource);
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse fileNotFoundException(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e);
    }

    @ExceptionHandler({FilesAreEmptyException.class, MultipartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse filesAreEmptyException(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e);
    }
}
