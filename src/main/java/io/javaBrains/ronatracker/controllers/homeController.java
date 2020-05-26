package io.javaBrains.ronatracker.controllers;

import io.javaBrains.ronatracker.models.LocationStats;
import io.javaBrains.ronatracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class homeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        //Taking list of objects
        //Converting into String
        //Mapping To integer, each object maps to the Integer Value which is the total cases for the record
        //Take String, sum it up and then assign it to an Integer
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestConfirmedCase()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        int totalLastWeekCases = allStats.stream().mapToInt(stat -> stat.getDiffFromLastWeek()).sum();
        model.addAttribute("locationStats", allStats );
        model.addAttribute("totalReportedCases", totalReportedCases );
        model.addAttribute("totalNewCases", totalNewCases );
        model.addAttribute("totalLastWeekCases", totalLastWeekCases);


        return "home";
    }
}
