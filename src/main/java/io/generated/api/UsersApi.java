/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.54).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.generated.api;

import ru.skypro.homework.dto.generatedDto.NewPasswordDto;
import ru.skypro.homework.dto.generatedDto.UpdateUserDto;
import ru.skypro.homework.dto.generatedDto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-07T11:59:23.949615055Z[GMT]")
@Validated
public interface UsersApi {

    @Operation(summary = "Получение информации об авторизованном пользователе", description = "", tags={ "Пользователи" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized") })
    @RequestMapping(value = "/users/me",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserDto> getUser();


    @Operation(summary = "Обновление пароля", description = "", tags={ "Пользователи" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK"),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        
        @ApiResponse(responseCode = "403", description = "Forbidden") })
    @RequestMapping(value = "/users/set_password",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> setPassword(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody NewPasswordDto body
);


    @Operation(summary = "Обновление информации об авторизованном пользователе", description = "", tags={ "Пользователи" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized") })
    @RequestMapping(value = "/users/me",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<UpdateUserDto> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UpdateUserDto body
);


    @Operation(summary = "Обновление аватара авторизованного пользователя", description = "", tags={ "Пользователи" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK"),
        
        @ApiResponse(responseCode = "401", description = "Unauthorized") })
    @RequestMapping(value = "/users/me/image",
        consumes = { "multipart/form-data" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<Void> updateUserImage(@Parameter(description = "") @Valid @RequestPart(value="image", required=false) MultipartFile image
);

}

