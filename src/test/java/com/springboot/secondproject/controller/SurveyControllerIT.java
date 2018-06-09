package com.springboot.secondproject.controller;

import com.springboot.secondproject.SpringbootSecondProjectApplication;
import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootSecondProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

    @LocalServerPort
    private int port;

    @Test
    public void test() {
        String url = "http://localhost:" + port + "/surveys/Survey1/questions/Question1";
        String expected = "<Question><id>Question1</id><description>Largest Country in the World</description><correctAnswer>Russia</correctAnswer><options><options>India</options><options>Russia</options><options>United States</options><options>China</options></options></Question>";

        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.asMediaType(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


        System.out.println(responseEntity);
        Assert.assertEquals("Validating the response", expected, responseEntity.getBody());

        String actual = restTemplate.getForObject(url, String.class);

        Assert.assertEquals("Validating the response", expected, actual);
        System.out.println("Validating the response \n expected : " + expected + " \n actual : " + actual);
    }

}
