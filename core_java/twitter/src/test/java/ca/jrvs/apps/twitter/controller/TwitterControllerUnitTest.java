package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
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
public class TwitterControllerUnitTest {


    @Mock
    TwitterService service;
    @InjectMocks
    TwitterController controller;

    @Test
    public void postTweet() {
        // mock service.postTweet() to return an empty Tweet object, and test not null.
        //     this way we are purely testing the behavior of service.postTweet without dependencies.
        when(service.postTweet(any())).thenReturn(new Tweet());
        String[] args1 = {"post", "Test", "1:-1"};
        Tweet tweet1 = controller.postTweet(args1);
        assertNotNull(tweet1);

        // test IllegalArgumentException thrown when coordinates are not in correct form.
        String[] args2 = {"post", "Test", "1"};
        try {
            controller.postTweet(args2);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // test IllegalArgumentException thrown when incorrect number of arguments is provided.
        String[] args3 = {"post", "Test"};
        try {
            controller.postTweet(args3);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // test IllegalArgumentException thrown when coordinates are out of range.
        String[] args4 = {"post", "Test", "250:89"};
        try {
            controller.postTweet(args4);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void showTweet() {
        // mock service.showTweet() to return an empty Tweet object, and test not null.
        //     this way we are purely testing the behavior of service.showTweet without dependencies.
        when(service.showTweet(any(), any())).thenReturn(new Tweet());
        String[] args1 = {"show", "1357924681357924681"};
        Tweet tweet = controller.showTweet(args1);
        assertNotNull(tweet);

        // test IllegalArgumentException thrown when more arguments is provided.
        String[] args2 = {"show", "12345", "yoo!"};
        try {
            controller.showTweet(args2);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteTweet() {
        // mock service.showTweet() to return an empty Tweet object, and test not null.
        //     this way we are purely testing the behavior of service.showTweet without dependencies.
        when(service.deleteTweets(any())).thenReturn(new ArrayList<>());
        String[] args1 = {"show", "1357924681357924681,12345"};
        List<Tweet> tweets = controller.deleteTweet(args1);
        assertNotNull(tweets);

        // test IllegalArgumentException thrown when more arguments is provided.
        String[] args2 = {"show", "13579,12345", "yoo!"};
        try {
            controller.deleteTweet(args2);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
}