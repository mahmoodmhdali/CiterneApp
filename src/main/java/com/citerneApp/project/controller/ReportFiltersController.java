/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.service.ReportFiltersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports/filters")
public class ReportFiltersController extends AbstractController {

    @Autowired
    ReportFiltersService reportFiltersService;

    @GetMapping("/{reportID}")
    public ResponseEntity getReports(@PathVariable Long reportID) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("reports", reportFiltersService.getFiltersByReportId(reportID))
                .returnClientResponse();
    }

    @GetMapping("/getDropDownValues/{reportFilterID}")
    public ResponseEntity getDropDownByReportFilterId(@PathVariable Long reportFilterID) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("reports", reportFiltersService.getDropDownByReportFilterId(reportFilterID))
                .returnClientResponse();
    }

}
