package com.tanmay192.CoronaVirusTracker.controller;

import com.tanmay192.CoronaVirusTracker.models.LocationStats;
import com.tanmay192.CoronaVirusTracker.services.CoronaVirusDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    CoronaVirusDataAccessService coronaVirusDataAccessService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<LocationStats> locationStatsList = coronaVirusDataAccessService.getAllLocationStats();
        Long totalConfirmedCases = coronaVirusDataAccessService.getTotalConfirmedCases();
        Long changeInConfirmedCases = coronaVirusDataAccessService.getChangeInConfirmedCases();
        model.addAttribute("locationStatsList", locationStatsList);
        model.addAttribute("totalConfirmedCases", totalConfirmedCases);
        model.addAttribute("changeInConfirmedCases", changeInConfirmedCases);

        return "index";
    }
}
