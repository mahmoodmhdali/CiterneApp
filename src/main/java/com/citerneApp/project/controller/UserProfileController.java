package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.UserProfile;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserProfileController extends AbstractController {

    @GetMapping
    public ResponseEntity getUsers() {
        UserProfile user = super.getAuthenticatedUser();
        Long excludeLoggedInUserID = -999999L; // in case you need to include logged in user to the list its set to his ID
        if (super.getAuthenticatedUser() != null) {
            excludeLoggedInUserID = super.getAuthenticatedUser().getId();
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("users", userService.getUsers(excludeLoggedInUserID, user.getType()))
                .returnClientResponse();
    }

    @GetMapping("/{pageNumber}/{maxResult}/{type}")
    public ResponseEntity getUsersPagination(@PathVariable Integer pageNumber, @PathVariable Integer maxResult, @PathVariable Integer type) {
        Long excludeLoggedInUserID = -999999L; // in case you need to include logged in user to the list its set to his ID
        if (super.getAuthenticatedUser() != null) {
            excludeLoggedInUserID = super.getAuthenticatedUser().getId();
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("users", userService.getUsersPagination(excludeLoggedInUserID, type, pageNumber, maxResult))
                .returnClientResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.getUser(id))
                .returnClientResponse();
    }

    @GetMapping("/auth/token/{token}")
    public ResponseEntity getUserByToken(@PathVariable String token) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.getUserByToken(token))
                .returnClientResponse();
    }

    @RequestMapping("/view")
    public ResponseEntity getUserByEmail(@RequestParam("email") String email) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.getUser(email))
                .returnClientResponse();
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@ModelAttribute @Valid UserProfile userProfile, BindingResult userProfileBindingResults) throws AddressException {
        // Validate User Inputs
        ResponseBodyEntity responseBodyEntity = super.checkValidationResults(userProfileBindingResults, null);
        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }
        userProfile.setType(1);
        UserProfile loggedInUser = this.getAuthenticatedUser();
        if (loggedInUser != null) {
            if (loggedInUser.getType() != 1 && loggedInUser.getType() != 99) {
                responseBodyEntity = ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.UNAUTHORIZED_USER_ACTION)
                        .setHttpResponseEntityResultDescription("Access denied for this resource. Contact your service provider for more help")
                        .getResponse();
                return ResponseBuilder.getInstance()
                        .setHttpStatus(HttpStatus.OK)
                        .setHttpResponseEntity(responseBodyEntity)
                        .returnClientResponse();
            }
        } else {
            responseBodyEntity = ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.UNAUTHORIZED_USER_ACTION)
                    .setHttpResponseEntityResultDescription("Access denied for this resource. Contact your service provider for more help")
                    .getResponse();
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.addUser(userProfile))
                .returnClientResponse();
    }

    @PostMapping("/update")
    public ResponseEntity updateUser(@ModelAttribute @Valid UserProfile userProfile, BindingResult groupBindingResults) {
        ResponseBodyEntity responseBodyEntity = this.checkValidationResults(groupBindingResults, new String[]{"password", "confirmPassword"});
        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }

        if (null != userProfile.getType()) {
            switch (userProfile.getType()) {
                case 0: {
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 99: {
                    break;
                }
                default:
                    break;
            }
        }

        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .setHttpResponseEntity(userService.updateUser(userProfile))
                .returnClientResponse();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {

        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(userService.deleteUser(id))
                .returnClientResponse();
    }

}
