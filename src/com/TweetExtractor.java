/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import org.xml.sax.SAXException;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanji
 */
public class TweetExtractor {

    private ConfigurationBuilder cb;
    private Twitter twitterApp;
    private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
    private String userToSearch;
    private int maxTweets;

    public TweetExtractor() throws ParserConfigurationException, SAXException, IOException {
        //PropertyConfigurator.configure("src/main/resources/log4j.properties");
        buildConfiguration();   // set the API keys to the Config Builder
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitterApp = tf.getInstance();
    }

    private String getClientID() {
        int hashcode = this.hashCode();
        return hashcode + "";
    }

    /**
     * *
     *
     */
    public ArrayList<Tweet> retrieveTweets(String searchWord) {

        Paging paging;

        // set the lowest value of the tweet ID initially to one less than Long.MAX_VALUE
        long min_id = Long.MAX_VALUE - 1;
        int count = 0;
        int index = 0;
        boolean maxValueReached = false;
        userToSearch = searchWord;

        while (true) {
            try {

                //count = tweetList.size();
                // paging tweets at a rate of 100 per page
                paging = new Paging(1, 100);

                // if this is not the first iteration set the new min_id value for the page
                if (count != 0) {

                    paging.setMaxId(min_id - 1);
                }

                // get a page of the tweet timeline with tweets with ids less than the min_id value
                List<Status> tweetTempList = twitterApp.getUserTimeline(userToSearch, paging);

                // iterate the results and add to tweetList
                for (Status s : tweetTempList) {
                    if (count == maxTweets) {
                        maxValueReached = true;
                        break;
                    }
                    count++;
                    Tweet tweet = new Tweet(s.getId(), s.getCreatedAt(), s.getText());
                    tweetList.add(tweet);

                    // set the value for the min value for the next iteration
                    if (s.getId() < min_id) {
                        min_id = s.getId();
                    }
                }

                // if the results for this iteration is zero, means we have reached the API limit or we have extracted the maximum
                // possible, so break
                if (tweetTempList.size() == 0 || maxValueReached) {
                    return tweetList;
                    // break;
                }

            } catch (TwitterException e) {
                e.printStackTrace();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return tweetList;

    }

    /**
     * *
     * Method to set up the API keys for the configuration builder
     */
    private void buildConfiguration() throws IOException, SAXException, ParserConfigurationException {
        cb = new ConfigurationBuilder();
        Logger.getLogger(TweetExtractor.class).debug("Building Configuration");

        String consumerKey = "9Rk0BvBYIUZB4LiKZWmx00Dpl";
        String consumerSecret = "QmLCDeGkKgbpmxVQzFnAJoq2Wpgw3uj0AdE8VxvQRCymzSvCct";
        String accessToken = "1602877004-upomW5DK5e8BSUdhU7OW9mQzBBr05AqCbDxEzuQ";
        String accessTokenSecret = "aBaklu0v1iu4WidpqpulPjT6I8VqHOYHurTSgWK8aPcEi";
        maxTweets = 50;
       // userToSearch="dulsrazz";

        if (consumerKey == null || consumerSecret == null || accessToken == null || accessTokenSecret == null) {

            throw new NullPointerException("TWitter API Keys not set");
        }

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
    }

}
