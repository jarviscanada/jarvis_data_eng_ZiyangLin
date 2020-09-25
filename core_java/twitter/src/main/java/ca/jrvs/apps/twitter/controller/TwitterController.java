package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;

import java.util.ArrayList;
import java.util.List;

public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private final Service service;

    public TwitterController(Service service) {
        this.service = service;
    }

    @Override
    public Tweet postTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }

        String text = args[1];
        String coord = args[2];
        String[] coordArray = coord.split(COORD_SEP);
        if (coordArray.length != 2) {
            throw new IllegalArgumentException("error: illegal location format, please use \"latitude:longitude\".");
        } else if (text.isEmpty()) {
            throw new IllegalArgumentException("error: empty text is not allowed, please try again.");
        }

        List<Double> coordinates = new ArrayList<>();
        try {
            coordinates.add(Double.parseDouble(coordArray[0]));
            coordinates.add(Double.parseDouble(coordArray[1]));
        } catch (Exception ex) {
            throw new IllegalArgumentException("error: illegal location input, " +
                    "lat in range [-180, 180], long in range [-90, 90].");
        }

        if (coordinates.get(0) < -180 || coordinates.get(0) > 180) {
            throw new IllegalArgumentException("error: illegal location input, " +
                    "lat in range [-180, 180], long in range [-90, 90].");
        } else if (coordinates.get(1) < -90 || coordinates.get(1) > 90) {
            throw new IllegalArgumentException("error: illegal location input, " +
                    "lat in range [-180, 180], long in range [-90, 90].");
        }

        Tweet toPost = TweetUtil.buildTweet(text, coordinates);
        return service.postTweet(toPost);
    }

    @Override
    public Tweet showTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"tweet_id\"");
        }
        String id = args[1];

        return service.showTweet(id, null);

    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp delete \"tweet_id1,tweet_id2,...\"");
        }

        String[] ids = args[1].split(COMMA);
        return service.deleteTweets(ids);
    }
}
