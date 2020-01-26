package com.skplanet.hackerton.demo.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skplanet.hackerton.demo.Model.Auction;
import com.skplanet.hackerton.demo.Model.Cow;
import com.skplanet.hackerton.demo.Model.Farmer;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public class DemoService {
    final String cowUrl = "http://172.21.112.65:3000/api/Cow";
    final String farmerUrl = "http://172.21.112.65:3000/api/Farmer";
    final String auctionUrl = "http://172.21.112.65:3000/api/Auction";

    public List<Cow> getCowList() {
        List<Cow> cows = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(cowUrl, String.class);
//        List<String> items = Arrays.asList(result.split("\\s*,\\s*"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            cows  = mapper.readValue(result, new TypeReference<List<Cow>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cows;
    }

    public void addCow(Cow cow) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonCow = null;
        try {
            jsonCow = mapper.writeValueAsString(cow);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpEntity<String> entity = new HttpEntity<>(jsonCow.toString(), headers);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.exchange(cowUrl, HttpMethod.POST, entity, String.class);
    }

    public List<Farmer> getFarmerList() {
        List<Farmer> farmers = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(farmerUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            farmers  = mapper.readValue(result, new TypeReference<List<Farmer>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return farmers;

    }

    public void addFarmer(Farmer farmer) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonFarmer = null;
        try {
            jsonFarmer = mapper.writeValueAsString(farmer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpEntity<String> entity = new HttpEntity<>(jsonFarmer.toString(), headers);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.exchange(farmerUrl, HttpMethod.POST, entity, String.class);
    }


    public List<Auction> getAutionList() {
        List<Auction> auctions = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(auctionUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            auctions  = mapper.readValue(result, new TypeReference<List<Auction>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auctions;

    }
    public void addAuction(Auction auction) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonAuction = null;
        try {
            jsonAuction = mapper.writeValueAsString(auction);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpEntity<String> entity = new HttpEntity<>(jsonAuction.toString(), headers);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.exchange(auctionUrl, HttpMethod.POST, entity, String.class);
    }
}
