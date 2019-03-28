package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.Subscription;
import com.citerneApp.project.service.SubscriptionService;
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
@RequestMapping("/subscriptions")
public class SubscriptionController extends AbstractController {

    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity getSubscriptiones() {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("subscriptions", subscriptionService.getSubscriptiones())
                .returnClientResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCountersByType(@PathVariable Long id) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("Subscription", subscriptionService.getSubscription(id))
                .returnClientResponse();
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@ModelAttribute @Valid Subscription subscription, BindingResult subscriptionBindingResults) throws AddressException {
        // Validate User Inputs
        ResponseBodyEntity responseBodyEntity = super.checkValidationResults(subscriptionBindingResults, null);
        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }

        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(subscriptionService.addSubscription(subscription))
                .returnClientResponse();
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUser(@ModelAttribute @Valid Subscription subscription, BindingResult subscriptionBindingResults) {
        // Validate User Inputs
        ResponseBodyEntity responseBodyEntity = super.checkValidationResults(subscriptionBindingResults, null);
        if (responseBodyEntity != null) {
            return ResponseBuilder.getInstance()
                    .setHttpStatus(HttpStatus.OK)
                    .setHttpResponseEntity(responseBodyEntity)
                    .returnClientResponse();
        }

        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntity(subscriptionService.deleteSubscription(subscription.getEmail()))
                .returnClientResponse();
    }

}
