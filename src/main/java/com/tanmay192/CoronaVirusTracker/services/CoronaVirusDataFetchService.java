package com.tanmay192.CoronaVirusTracker.services;

import com.tanmay192.CoronaVirusTracker.models.LocationStats;
import com.tanmay192.CoronaVirusTracker.repository.CoronaVirusDataRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CoronaVirusDataFetchService {

    private final static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    @Autowired
    private CoronaVirusDataRepository coronaVirusDataRepository;

    @PostConstruct
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Scheduled(cron = "0 30 10 * * ?", zone = "GMT+5.30")
    public void fetchCoronaVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader reader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

        coronaVirusDataRepository.deleteAll();
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            int n = record.size();
            locationStats.setState(record.get("Province/State"));
            locationStats.setCountry(record.get("Country/Region"));
            locationStats.setNewNumberOfCases(Long.parseLong(record.get(n - 1)));
            locationStats.setChangeInNumberOfCases(Long.parseLong(record.get(n - 1))
                    - Integer.parseInt(record.get(n - 2)));

            coronaVirusDataRepository.save(locationStats);
        }
    }
}
