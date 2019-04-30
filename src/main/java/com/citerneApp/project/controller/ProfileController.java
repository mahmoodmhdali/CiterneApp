package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.Profile;
import com.citerneApp.project.service.ProfileService;
import java.io.IOException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profiles")
public class ProfileController extends AbstractController {

    @Autowired
    ProfileService profileService;

    @GetMapping
    public ResponseEntity getProfilees() {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("profiles", profileService.getProfilees())
                .returnClientResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCountersByType(@PathVariable Long id) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("Profile", profileService.getProfile(id))
                .returnClientResponse();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getCountersByType(@PathVariable String name) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("Profile", profileService.getProfile(name))
                .returnClientResponse();
    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity addPass(@RequestPart("info") @Valid Profile profile, BindingResult adminPassesBindingResults,
            @RequestPart(value = "uploadFile1", required = false) MultipartFile file1) throws AddressException, IOException {
        // Validate User Inputs
        ResponseBodyEntity responseBodyEntity = super.checkValidationResults(adminPassesBindingResults, null);
        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(profileService.addProfile(profile, file1))
                .returnClientResponse();
    }

    @PostMapping(value = "/edit", consumes = "multipart/form-data")
    public ResponseEntity editPass(@RequestPart("info") @Valid Profile profile, BindingResult adminPassesBindingResults,
            @RequestPart(value = "uploadFile1", required = false) MultipartFile file1) throws AddressException, IOException {
        // Validate User Inputs
        ResponseBodyEntity responseBodyEntity = super.checkValidationResults(adminPassesBindingResults, null);
        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(profileService.editProfile(profile, file1))
                .returnClientResponse();
    }

}
