package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

    @Mock
    TwitterDao dao;
    @InjectMocks
    TwitterService service;

    @Test
    public void postTweet() {
        // mock dao.create() to return an empty Tweet object, and test not null.
        //     this way we are purely testing the behavior of service.postTweet without dependencies.
        when(dao.create(any())).thenReturn(new Tweet());
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(1d);
        coordinates.add(-1d);
        Tweet tweet = service.postTweet(TweetUtil.buildTweet("Test", coordinates));
        assertNotNull(tweet);
    }

    @Test
    public void showTweet() {
        // mock dao.findById() to return an empty Tweet object, and test not null.
        //     this way we are purely testing the behavior of service.showTweet without dependencies.
        when(dao.findById(any())).thenReturn(new Tweet());
        String id = "1357924681357924681";
        Tweet tweet = service.showTweet(id, null);
        assertNotNull(tweet);
    }

    @Test
    public void deleteTweets() {
        // mock dao.findById() to return an empty Tweet object, and test not null.
        //     this way we are purely testing the behavior of service.deleteTweets without dependencies.
        when(dao.deleteById(any())).thenReturn(new Tweet());
        String[] ids = {"aabbcc", "123456"};

        // an exception is expected since input is 2 invalid ids.
        try {
            service.deleteTweets(ids);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
}