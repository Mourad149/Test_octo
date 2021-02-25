package com.octo.api;

import com.octo.dto.video.VideoDTO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {
    @LocalServerPort
    private int port = 8888;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="https://localhost";

    @Given("^the client calls the api with level (.*) and tags (.*)$")
    public void calls_api(String level, String tags) {
        String url = postUrl + ":" + port + "/api/v1/videos";

        String[] newTags=tags.split("; ");
        List<String> myList = new ArrayList<>();
        for (String str : newTags) {
            myList.add(str);
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("level", level)
                .queryParam("tags", myList);

                List<VideoDTO> videos=restTemplate.getForObject(uriBuilder.toUriString(),List.class);
            log.info(videos);

    };
    @Then("^the client should receive status code of (\\d+)$")
    public void receive_status(Integer arg0){
        throw new cucumber.api.PendingException();

    };

}
