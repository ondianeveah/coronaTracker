package io.javaBrains.ronatracker.services;

import io.javaBrains.ronatracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
    private static String VIRUS_DATA_URI = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_US.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    //Telling Spring to run this method every second
    @Scheduled(cron = "* * 1 * * * ") // Executing method once everyday
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Instance of reader that parse a string
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        //Reader Instance = a way in java to get to read things like text
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        //Why? Want to save this info in memory
        for (CSVRecord record : records) {
            //Create instance
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province_State"));
            locationStat.setCountry(record.get("Country_Region"));
            locationStat.setLatitude(record.get("Lat"));
            locationStat.setLongitude(record.get("Long_"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int previousDayCases = Integer.parseInt(record.get(record.size() - 2));
            int lastWeekCases = Integer.parseInt(record.get(record.size() - 7));
            locationStat.setDiffFromLastWeek(latestCases - lastWeekCases);
            locationStat.setLatestConfirmedCase(latestCases);
            locationStat.setDiffFromPreviousDay(latestCases - previousDayCases);
            System.out.println(locationStat);
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }

}

