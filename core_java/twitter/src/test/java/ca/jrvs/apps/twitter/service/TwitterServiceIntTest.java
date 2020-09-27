package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {

    private TwitterDao dao;
    private TwitterService service;

    @Before
    public void setUp() {
        TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper();
        this.dao = new TwitterDao(twitterHttpHelper);
        this.service = new TwitterService(dao);
    }

    @Test
    public void postTweet() {
        String text = "let's have some fun!";
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(1d);
        coordinates.add(-1d);
        Tweet expected = TweetUtil.buildTweet(text, coordinates);
        Tweet actual = service.postTweet(expected);

        assertEquals(text, actual.getText());
        assertEquals(2, actual.getCoordinates().getCoordinates().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void postTweet2() {
        String text = "let's have some fun!";
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(190d);
        coordinates.add(-1d);
        Tweet expected = TweetUtil.buildTweet(text, coordinates);
        service.postTweet(expected);
    }
    @Test
    public void showTweet() {
        String id = "1308980863208697857";
        String text = "today is a good day";
        Tweet actual = service.showTweet("1308980863208697857", null);

        assertEquals(text, actual.getText());
        assertEquals(id, actual.getIdStr());
    }

    @Test
    public void deleteTweets() {
        String id = "1308980863208697857";
        String text = "today is a good day";
        String[] ids = {"1308980863208697857", "1234"};
        List<Tweet> actual = service.deleteTweets(ids);

        assertEquals(text, actual.get(0).getText());
        assertEquals(id, actual.get(0).getIdStr());
    }
}