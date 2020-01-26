package com.skplanet.hackerton.demo.controller;

import com.skplanet.hackerton.demo.Model.Auction;
import com.skplanet.hackerton.demo.Model.Cow;
import com.skplanet.hackerton.demo.Model.Farmer;
import com.skplanet.hackerton.demo.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DemoController {
    DemoService demoService = new DemoService();

    @RequestMapping("/")
    public String index(Model model) {
        List<Cow> cowList = demoService.getCowList();
        model.addAttribute("cows", cowList);
        model.addAttribute("cow", new Cow());

        return "redirect:/cow";
    }

    @RequestMapping("/cow")
    public String cows(Model model) {
        List<Cow> cowList = demoService.getCowList();
        model.addAttribute("cows", cowList);
        model.addAttribute("cow", new Cow());

        return "cow";
    }

    @RequestMapping(value = "/addCow", method = RequestMethod.POST)
    public String addCow(@ModelAttribute Cow cow, Model model) {
        model.addAttribute("cow", new Cow());
        demoService.addCow(cow);
        return "redirect:/cow";
    }

    @RequestMapping("/farmer")
    public String farmers(Model model) {
        List<Farmer> farmerList = demoService.getFarmerList();
        model.addAttribute("farmers", farmerList);
        model.addAttribute("farmer", new Farmer());

        return "farmer";
    }

    @RequestMapping(value = "/addFarmer", method = RequestMethod.POST)
    public String addFarmer(@ModelAttribute Farmer farmer, Model model) {
        model.addAttribute("farmer", new Farmer());
        demoService.addFarmer(farmer);
        return "redirect:/farmer";
    }

    @RequestMapping("/auction")
    public String auctions(Model model) {
        List<Auction> auctionList = demoService.getAutionList();
        model.addAttribute("auctions", auctionList);
        model.addAttribute("auction", new Auction());

        return "auction";
    }

    @RequestMapping(value = "/addAuction", method = RequestMethod.POST)
    public String addAuction(@ModelAttribute Auction auction, Model model) {
        model.addAttribute("auction", new Farmer());
        demoService.addAuction(auction);
        return "redirect:/auction";
    }
}
