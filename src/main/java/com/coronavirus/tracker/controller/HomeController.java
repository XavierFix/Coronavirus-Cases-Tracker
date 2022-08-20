package com.coronavirus.tracker.controller;

import com.coronavirus.tracker.models.Location;
import com.coronavirus.tracker.services.CoronaVirusDataServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  CoronaVirusDataServices dataServices;
  @GetMapping("/")
  public String home(Model model){
    List<Location> allStats = dataServices.getAllStats();
    int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalReport()).sum();
    model.addAttribute("locationStats", allStats );
    model.addAttribute("totalReportedCases", totalCases);

    return "index";
  }
}
