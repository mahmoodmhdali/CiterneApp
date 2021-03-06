package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.helpermodel.UserProfilePasswordValidator;
import com.citerneApp.project.model.UserProfile;
import com.citerneApp.project.service.UserService;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class UserNotAuthController extends AbstractController {

    @Autowired
    UserService userService;

    @GetMapping("/auth/token/{token}")
    public ResponseEntity getUserByToken(@PathVariable String token) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.getUserByToken(token))
                .returnClientResponse();
    }

    @PostMapping("/updatePassword/{token}")
    public ResponseEntity updatePassword(@PathVariable String token,
            @ModelAttribute @Valid UserProfilePasswordValidator userProfilePasswordValidator,
            BindingResult userProfilePasswordValidatorBindingResults) {

        // Validate User Inputs
        ResponseBodyEntity responseBodyEntity = super.checkValidationResults(userProfilePasswordValidatorBindingResults, null);

        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }

        // Valdidate Passwords matching
        if (!userProfilePasswordValidator.matchPasswords()) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("confirmPassword", this.getMessageBasedOnLanguage("user.controller.passwordMismatch", null))
                    .returnClientResponse();
        }

        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.changeUserPasswordByToken(token, userProfilePasswordValidator))
                .returnClientResponse();
    }

    @PostMapping("/resetPassword")
    public ResponseEntity resetPassword(UserProfile user) throws AddressException {
        if (user != null && user.getEmail() != null) {
            userService.sendEmailAndUpdateToken(user.getEmail());
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("success", "Email sent to " + user.getEmail())
                    .returnClientResponse();
        } else {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("success", "User does not exist")
                    .returnClientResponse();
        }
    }

    @PostMapping("/createAccount")
    public ResponseEntity addUser(@ModelAttribute @Valid UserProfile userProfile, BindingResult userProfileBindingResults) throws AddressException {
        // Validate User Inputs
        ResponseBodyEntity responseBodyEntity = super.checkValidationResults(userProfileBindingResults, null);
        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }
        userProfile.setType(2);
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.addUser(userProfile))
                .returnClientResponse();
    }

}
