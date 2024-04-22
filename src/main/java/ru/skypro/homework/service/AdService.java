package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.generatedDto.AdDto;
import ru.skypro.homework.dto.generatedDto.AdsDto;
import ru.skypro.homework.dto.generatedDto.CreateOrUpdateAdDto;

public interface AdService {

    AdsDto getAllAds();

    AdDto createAd(CreateOrUpdateAdDto createAd, MultipartFile image, Authentication authentication);
}
