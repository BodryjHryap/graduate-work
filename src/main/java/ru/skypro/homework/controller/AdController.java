package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.generatedDto.AdDto;
import ru.skypro.homework.dto.generatedDto.AdsDto;
import ru.skypro.homework.dto.generatedDto.CreateOrUpdateAdDto;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping(path = "/ads")
public class AdController {

    private final AdService adService;
    private final CommentService commentService;
    private final ImageService imageService;

    public AdController(AdService adService, CommentService commentService, ImageService imageService) {
        this.adService = adService;
        this.commentService = commentService;
        this.imageService = imageService;
    }

    @Operation(summary = "getAds",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(
                            schema = @Schema(implementation = AdsDto.class))),
                    @ApiResponse(responseCode = "404", content = @Content)
            })
    @GetMapping
    public ResponseEntity<AdsDto> getAds() {
        log.info("Was invoked get all ads method");
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "addAds",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = AdsDto.class))
                    ),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "403", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content)
            })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAds(@RequestPart("properties") CreateOrUpdateAdDto createAd,
                                        @RequestPart("image") MultipartFile image,
                                        Authentication authentication
    ) {
        log.info("Was invoked add ad method");
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.createAd(createAd, image, authentication));
    }
}
