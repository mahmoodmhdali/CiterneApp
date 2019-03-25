package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.service.UserOutletOfferPurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userOutletOfferPurchased")
public class UserOutletOfferPurchasedController extends AbstractController {

    @Autowired
    UserOutletOfferPurchasedService userOutletOfferPurchasedService;

    @GetMapping
    public ResponseEntity getUserOutletOfferPurchaseds() {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("userOutletOfferPurchaseds", userOutletOfferPurchasedService.getUserOutletOfferPurchaseds())
                .returnClientResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserOutletOfferPurchased(@PathVariable Long id) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("userOutletOfferPurchased", userOutletOfferPurchasedService.getUserOutletOfferPurchased(id))
                .returnClientResponse();
    }

    @GetMapping("/purchase/{offerID}/{userID}")
    public ResponseEntity myPackages(@PathVariable Long offerID, @PathVariable Long userID) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("userCompanyPasses", userOutletOfferPurchasedService.addUserOutletOfferPurchased(offerID, userID))
                .returnClientResponse();
    }

}
