package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;

public interface ImageService {
    Image createImage(MultipartFile image, Ad ad);
}
