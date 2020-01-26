package com.skplanet.hackerton.demo.Model;


public class Cow {
    private String assetId;
    private String value;
    private String farmer;
    private String broker;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        if (farmer.contains("#")) {
            String[] farmerId = farmer.split("#");
            this.farmer = farmerId[1];
        } else {
            this.farmer = "resource:com.skp.hack.Farmer#" + farmer;
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
}
