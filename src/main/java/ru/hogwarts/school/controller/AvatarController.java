package ru.hogwarts.school.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/avatars")
public class AvatarController {

    private final AvatarService avatarService;
    private final StudentService studentService;

    @Autowired
    public AvatarController(AvatarService avatarService, StudentService studentService) {
        this.avatarService = avatarService;
        this.studentService = studentService;
    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Avatar> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("studentId") Long studentId) throws IOException {
        Student student = studentService.getStudentById(studentId);
        Avatar avatar = student.getAvatar();

        if (avatar == null) {
            avatar = new Avatar();
            avatar.setStudent(student);
        }

        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        Path filePath = Paths.get("./avatars" + fileName);
        Files.write(filePath, file.getBytes());

        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());

        avatar = avatarService.saveAvatar(avatar);

        return ResponseEntity.ok().body(avatar);
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@PathVariable("id") Long avatarId) throws IOException {
        Avatar avatar = avatarService.getAvatarById(avatarId);

        byte[] data = avatar.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/download/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable("id") Long avatarId) throws IOException {
        Avatar avatar = avatarService.getAvatarById(avatarId);

        Path path = Paths.get(avatar.getFilePath());
        byte[] data = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}