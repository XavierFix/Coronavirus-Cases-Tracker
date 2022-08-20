package com.coronavirus.tracker.services;

import com.coronavirus.tracker.models.Location;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronaVirusDataServices {

private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

private List<Location> allStats = new ArrayList<>();
@PostConstruct
@Scheduled(cron = "* * * 1 * *") //sec min hr day mon yer
  public void fetchVirusData() throws IOException, InterruptedException {

   List<Location> newStats = new ArrayList<>();
  HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(VIRUS_DATA_URL))
        .build();
    HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
  StringReader cvsBody = new StringReader((httpResponse.body())); //below this is from the apache java cvs library
  Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(cvsBody);

  for (CSVRecord record : records) {
    //String state = record.get("Province/State"); this is to prove if it works :D
    //System.out.println(state);
    Location location = new Location();
    location.setState(record.get("Province/State"));
    location.setCountry(record.get("Country/Region"));
  int latestCases =    Integer.parseInt(record.get(record.size() -1));
  int previousDayCases = Integer.parseInt(record.get(record.size() -2));
  location.setLatestTotalReport(latestCases);
  location.setDiffFromPreviousCases(latestCases-previousDayCases);
newStats.add(location);
  }
  this.allStats = newStats;
  }

  public List<Location> getAllStats() {
    return allStats;
  }
}
