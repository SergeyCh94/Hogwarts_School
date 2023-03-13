package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
@Transactional
public class AvatarService {

    private final AvatarRepository avatarRepository;

    private final StudentService studentService;

    @Value("${application.avatars.folder}")
    private String avatarDir;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public Avatar saveAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public void deleteAvatar(Long avatarId) {
        avatarRepository.deleteById(avatarId);
    }

    private String saveFileToDisk(MultipartFile file, Long studentId) throws IOException {
        Path filePath = Path.of(avatarDir, studentId + ".jpg");
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);)
        {
            bis.transferTo(bos);
        }
        return filePath.toString();
    }

    public Avatar getAvatarById(Long avatarId) {
        return avatarRepository.findById(avatarId).orElseThrow();
    }
}
