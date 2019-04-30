package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
