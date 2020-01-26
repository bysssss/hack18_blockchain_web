package com.skplanet.hackerton.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Auction {
    private String cow;
    private String broker;
    private String transactionId;
    private String timestamp;

    public String getCow() {
        return cow;
    }

    public void setCow(String cow) {
        if (cow.contains("#")) {
            String[] cowId = cow.split("#");
            this.cow = cowId[1];
        } else {
            this.cow = "resource:com.skp.hack.Cow#" + cow;
        }
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        if (broker.contains("#")) {
            String[] brokerId = broker.split("#");
            this.broker = brokerId[1];
        } else {
            this.broker = "resource:com.skp.hack.Broker#" + broker;
        }
    }
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId.substring(0,10) + "...";
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
//        String server_format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
//        String local_format = "yyyy-MM-dd hh:mm";
//
//        SimpleDateFormat format = new SimpleDateFormat(server_format, Locale.US);
//        Date date = null;
//        try {
//            date = format.parse(timestamp);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        format.applyPattern(local_format);
//        String newDate = format.format(date);

        this.timestamp = timestamp;
    }
}
