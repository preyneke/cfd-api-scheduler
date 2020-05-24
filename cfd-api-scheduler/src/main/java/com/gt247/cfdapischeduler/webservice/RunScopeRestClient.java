package com.gt247.cfdapischeduler.webservice;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.HttpMethod.*;

@Component
public class RunScopeRestClient {

    private static org.slf4j.Logger log = LoggerFactory.getLogger("GetHoldingsRestClient.class");

    @Value("${runscope_base_url}")
    private String runScopeUrl;


    RestTemplate restTemplate = new RestTemplate();


    public boolean sendTrigger(String trigger) {
        String apiUrl = runScopeUrl + trigger;
        log.debug("RunScope Rest URL: " + runScopeUrl + trigger);

        boolean wasOk=false;
        try {

            ResponseEntity<String> response
                    = restTemplate.getForEntity(apiUrl, String.class);

            wasOk = response.getStatusCode() == HttpStatus.CREATED;
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error("Could not call GetHoldings REST service on: " + apiUrl, e);
        }

        return   wasOk;
    }
}
