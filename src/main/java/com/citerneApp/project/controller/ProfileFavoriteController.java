package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.UserProfile;
import com.citerneApp.project.service.ProfileFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profileFavorites")
public class ProfileFavoriteController extends AbstractController {

    @Autowired
    ProfileFavoriteService profileFavoriteService;

    @GetMapping
    public ResponseEntity getFavoritees() {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorites", profileFavoriteService.getProfileFavoritees())
                .returnClientResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity getProfileFavoritesByID(@PathVariable Long id) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", profileFavoriteService.getProfileFavorite(id))
                .returnClientResponse();
    }

    @GetMapping("/add/{profileId}")
    public ResponseEntity addNewProfileFavorite(@PathVariable Long profileId) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", profileFavoriteService.addProfileFavorite(profileId))
                .returnClientResponse();
    }

    @GetMapping("/remove/{profileId}")
    public ResponseEntity removeProfileFavorite(@PathVariable Long profileId) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", profileFavoriteService.deleteProfileFavorite(profileId))
                .returnClientResponse();
    }

    @GetMapping("/user")
    public ResponseEntity getProfileFavoritesByUser() {
        UserProfile user = this.getAuthenticatedUser();
        if (user == null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("user", "User not logged In")
                    .returnClientResponse();
        }
        Long userID = user.getId();
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", profileFavoriteService.getProfileFavoritesByUserID(userID))
                .returnClientResponse();
    }

}
