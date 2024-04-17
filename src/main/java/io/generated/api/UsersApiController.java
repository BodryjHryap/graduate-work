package io.generated.api;

import ru.skypro.homework.dto.generatedDto.NewPasswordDto;
import ru.skypro.homework.dto.generatedDto.UpdateUserDto;
import ru.skypro.homework.dto.generatedDto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-07T11:59:23.949615055Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<UserDto> getUser() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserDto>(objectMapper.readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"image\" : \"image\",\n  \"role\" : \"USER\",\n  \"phone\" : \"phone\",\n  \"id\" : 0,\n  \"email\" : \"email\"\n}", UserDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> setPassword(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody NewPasswordDto body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UpdateUserDto> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UpdateUserDto body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UpdateUserDto>(objectMapper.readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"phone\" : \"phone\"\n}", UpdateUserDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UpdateUserDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UpdateUserDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUserImage(@Parameter(description = "") @Valid @RequestPart(value="image", required=false) MultipartFile image
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}