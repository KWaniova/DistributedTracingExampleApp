package com.example.nameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;


@RestController
@RequestMapping("/names")
class NameController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/random")
    public String random() throws Exception {
        String animal = getNameFromRemote("9000", "animals");
        String scientist = getNameFromRemote("8090", "scientist");
        String name = scientist + "-" + animal;
        return name;
    }

    private String getNameFromRemote(String port, String endpoint) {
        String serviceName = System.getenv("SERVICE_FORMATTER");
        if (serviceName == null) {
            serviceName = "localhost";
        }
        String urlPath = "http://" + serviceName + ":" + port + "/" + endpoint + "/random";
        URI uri = UriComponentsBuilder //
                .fromHttpUrl(urlPath) //
                .build(Collections.emptyMap());
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();

    }

    @GetMapping(path = "/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name + " from name generator";
    }


}