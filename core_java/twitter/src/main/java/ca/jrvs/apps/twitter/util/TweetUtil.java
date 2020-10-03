package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

import java.util.List;

public class TweetUtil {

    public static Tweet buildTweet(String text, List<Double> location) {
        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(location);
        tweet.setCoordinates(coordinates);
        return tweet;
    }
}
