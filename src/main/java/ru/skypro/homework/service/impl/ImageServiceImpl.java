package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.EmptyFileException;
import ru.skypro.homework.exception.ImageCanNotReadEception;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final UserService userService;

    public ImageServiceImpl(ImageRepository imageRepository, UserService userService) {
        this.imageRepository = imageRepository;
        this.userService = userService;
    }

    @Override
    public Image createImage(MultipartFile file, Ad ad) {
        Image image = new Image();
        fileToImage(file, image);
        image.setAd(ad);

        return imageRepository.save(image);
    }

    @Override
    public byte[] getAdImage(Long id) {
        return getImageFromDB(id).getData();
    }

    private void fileToImage(MultipartFile file, Image image) {
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }
        byte[] imageData;
        try {
            imageData = file.getBytes();
        } catch (IOException e) {
            throw new ImageCanNotReadEception("File can not be read");
        }
        image.setData(imageData);
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
    }

    private Image getImageFromDB(long id) {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }


}
