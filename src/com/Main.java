/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import org.xml.sax.SAXException;
import twitter4j.JSONException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sanji
 */
public class Main {

    public static void main(String[] args) throws IOException, JSONException, SAXException, ParserConfigurationException {
        ArrayList<Tweet> tweetlist = new ArrayList<Tweet>();
        TweetExtractor extractor = new TweetExtractor();
        ArrayList<TweetAnalyser> positivetweets = new ArrayList<TweetAnalyser>();
        ArrayList<TweetAnalyser> negativetweets = new ArrayList<TweetAnalyser>();
        ArrayList<TweetAnalyser> neutraltweets = new ArrayList<TweetAnalyser>();
        tweetlist = extractor.retrieveTweets("Nuwan_Prabhath");
        TweetAnalyser ta;
        String tweet_status;
        int count = 0;
        if (tweetlist.size() == 0) {
            System.out.println("Empty");
            System.exit(0);
        }
        System.out.println(tweetlist.size());

        for (int j = 0; j < tweetlist.size(); j++) {
            ta = new TweetAnalyser();
            ta.setTweet(tweetlist.get(j).getText());
            tweet_status = SentimentalResponse.readJsonFromUrl(tweetlist.get(j).getText());
            ta.setTweet_status(tweet_status);
            int i=0;
            if (tweet_status.equals("positive")) {
                i++;
            System.out.println("J"+j);
                positivetweets.add(ta);
               // i+=positivetweets.size();
                System.out.println("size po"+i);                
                System.out.println(ta.toString()+"ok1");
            } else if (tweet_status.equals("negative")) {
                i++;
            System.out.println("J"+j);
                negativetweets.add(ta);
               //i+=negativetweets.size();
                System.out.println("size ne"+i);
                System.out.println(ta.toString()+"ok2");
            } else if (tweet_status.equals("neutral")) {
                i++;
            System.out.println("J"+j);
                neutraltweets.add(ta);
                // i+=neutraltweets.size();
                System.out.println("size neu"+i);
                System.out.println(ta.toString()+"ok3");
            }

        }

    }

}
