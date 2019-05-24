package com.project.defne.controller;

import com.project.defne.service.report.ReportingService;
import com.project.defne.service.report.resource.per_time.PerTimeRQ;
import com.project.defne.service.report.resource.per_time.PerTimeRS;
import com.project.defne.service.report.resource.per_time_fail_by_code.PerTimeFailByCodeRS;
import com.project.defne.service.report.resource.per_time_fail_by_code.PerTimeSuccessFailRS;
import com.project.defne.service.report.resource.per_time_fail_by_code_and_client.PerTimeFailByCodeAndClientRS;
import com.project.defne.service.report.resource.per_time_per_client.PerTimePerClientRQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Ceren A. @ 5/23/2019
 * While listening ${SPOT}
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportingService reportingService;

    @PostMapping("/perTime")
    public PerTimeRS getPerTimeRequest(@RequestBody @Valid PerTimeRQ perTimeRQ){
        return reportingService.getPerTimeRequest(perTimeRQ);
    }

    @PostMapping("/perTimeSuccessFail")
    public PerTimeSuccessFailRS getPerTimeSuccessFail(@RequestBody @Valid PerTimeRQ perTimeRQ){
        return reportingService.getPerTimeSuccessFail(perTimeRQ);
    }

    @PostMapping("/perTime/perTimeFailByCode")
    public PerTimeFailByCodeRS getPerTimeRequestFailByCode(@RequestBody @Valid PerTimeRQ perTimeRQ){
        return reportingService.getPerTimeRequestFailByCode(perTimeRQ);
    }

    @PostMapping("/perTimeFailByCodeAndClient")
    public PerTimeFailByCodeAndClientRS getPerTimeRequestFailByCodeAndClient(@RequestBody  @Valid PerTimePerClientRQ perTimePerClientRQ){
        return reportingService.getPerTimeRequestFailByCodeAndClient(perTimePerClientRQ);
    }
}
