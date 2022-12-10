package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.api.Medline;
import com.nci.webapp.AlzApp.config.RestConfig;
import com.nci.webapp.AlzApp.model.RSSFeedReader;
import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.FetcherException;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.sql.rowset.spi.XmlReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class InformationsController {

    @Autowired
    RestConfig restConfig;

    @GetMapping ("/knowing-the-disease")
    public String informations(Model model){

        /**
         * CALLING MEDLINE API TO  GET INFORMATION ABOUT ALZHEIMER
         */

        Medline api = restConfig.restTemplate().getForObject("https://medlineplus.gov/download/genetics/condition/alzheimer-disease.json", Medline.class);

        List textList = api.getTexts();
//        System.out.println(textList.toString());

        List synonyms = api.getSynonyms();

        String synonymsString = Arrays.toString(synonyms.toArray()).replace("[","").replace("]","");
//        System.out.println(synonyms.toString());

        //Setting the models to display the information found in the api

        model.addAttribute("link", api.getGhr_page());
        model.addAttribute("name", api.getName());
        model.addAttribute("text",textList.get(0));
        model.addAttribute("synonyms", synonymsString);

        /**
         * RSS FEED READER
         * Alzheimer's Society Blog
         */

        FeedFetcher fetcher = new HttpURLFeedFetcher();
        List<RSSFeedReader> articles = new ArrayList();
        try {
            SyndFeed feed = fetcher.retrieveFeed(new URL("https://www.beingpatient.com/feed/"));
            for (Object o: feed.getEntries()) {
                RSSFeedReader article = new RSSFeedReader();
                SyndEntry entry = (SyndEntry) o;
               article.setTitle(entry.getTitle());
               article.setLink(entry.getLink());
               article.setDescription(entry.getDescription().getValue());
               article.setDate(entry.getPublishedDate());

                articles.add(article);
//                System.out.println(articles.toString());

            }

        } catch (IOException | FeedException | FetcherException e) {
            e.printStackTrace();
        }

        model.addAttribute("rssArticles", articles);

        return "/informations";
    }









}
