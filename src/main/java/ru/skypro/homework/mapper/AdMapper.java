package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.generatedDto.AdDto;
import ru.skypro.homework.dto.generatedDto.AdsDto;
import ru.skypro.homework.dto.generatedDto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.generatedDto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdMapper {
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "id")
    Ad adDtoToAd(AdDto adDto);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "images", target = "image", qualifiedByName = "getListOfImageLinks")
    AdDto adToAdDto(Ad ad);

    Ad createOrUpdateAdDtoToAd(CreateOrUpdateAdDto createOrUpdateAdDto);
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "images", target = "image", qualifiedByName = "getListOfImageLinks")
    ExtendedAdDto adToExtendedAdDto(Ad ad);

    @Mapping(source = "size", target = "count")
    @Mapping(source = "adList", target = "results")
    AdsDto adListToAdsDto(Integer size, List<Ad> adList);

    @Named("getListOfImageLinks")
    default List<String> getListOfImageLinks(List<Image> images) {
        return (images == null || images.isEmpty()) ? null :
                images.stream().map(i -> "/image/" + i.getId()).collect(Collectors.toList());
    }
}
