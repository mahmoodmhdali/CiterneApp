package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.UserProfile;
import com.citerneApp.project.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorites")
public class FavoriteController extends AbstractController {

    @Autowired
    FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity getFavoritees() {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorites", favoriteService.getFavoritees())
                .returnClientResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity getFavoritesByID(@PathVariable Long id) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", favoriteService.getFavorite(id))
                .returnClientResponse();
    }

    @GetMapping("/add/{eventId}")
    public ResponseEntity addNewFavorite(@PathVariable Long eventId) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", favoriteService.addFavorite(eventId))
                .returnClientResponse();
    }

    @GetMapping("/remove/{eventId}")
    public ResponseEntity removeFavorite(@PathVariable Long eventId) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", favoriteService.deleteFavorite(eventId))
                .returnClientResponse();
    }

    @GetMapping("/user")
    public ResponseEntity getFavoritesByUser() {
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
                .addHttpResponseEntityData("favorite", favoriteService.getFavoritesByUserID(userID))
                .returnClientResponse();
    }

}
