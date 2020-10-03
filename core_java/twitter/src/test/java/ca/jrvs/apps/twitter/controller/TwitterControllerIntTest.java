package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterControllerIntTest {

    private TwitterDao dao;
    private TwitterService service;
    private TwitterController controller;

    @Before
    public void setUp() {
        TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper();
        this.dao = new TwitterDao(twitterHttpHelper);
        this.service = new TwitterService(dao);
        this.controller = new TwitterController(service);
    }

    @Test
    public void postTweet() {
        String text = "let's have some fun!!";
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(1d);
        coordinates.add(-1d);
        String[] args = {"post", "let's have some fun!!", "1:-1"};
        Tweet actual = controller.postTweet(args);

        assertEquals(text, actual.getText());
        assertEquals(2, actual.getCoordinates().getCoordinates().size());
    }

    @Test
    public void showTweet() {
        String[] args = {"show", "1309357395655757824"};
        Tweet actual = controller.showTweet(args);

        assertEquals("1309357395655757824", actual.getIdStr());
    }

    @Test
    public void deleteTweet() {
        String ids = "1309357395655757824" + ",123456789";
        String[] args = {"delete", ids};
        List<Tweet> actual = controller.deleteTweet(args);

        assertEquals("1309357395655757824", actual.get(0).getIdStr());
    }
}