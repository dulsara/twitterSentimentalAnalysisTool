/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author sanji
 */
public class TweetAnalyser {

    private String tweet;
    private String tweet_status;

    public String getTweet() {
        return tweet;
    }

    public String getTweet_status() {
        return tweet_status;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public void setTweet_status(String tweet_status) {
        this.tweet_status = tweet_status;
    }

    @Override
    public String toString() {

//        String color;
//
//        switch (tweet_status) {
//            case "positive":
//                color = "green";
//                break;
//            case "negative":
//                color = "red";
//                break;
//            case "neutral":
//                color = "yellow";
//                break;
//            default:
//                color = "black";
//                break;
//        }
//        "<b><u>T</u>wo</b><br>lines</html>"

//       "<html><font color="+color+">" +    +"</font></html>\n\n"
        return tweet+"\n";
    }
}
