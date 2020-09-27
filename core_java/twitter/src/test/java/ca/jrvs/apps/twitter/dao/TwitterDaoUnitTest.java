package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;
    @InjectMocks
    TwitterDao dao;

    @Test
    public void create() throws Exception {
        String text = "hey let's have some fun!";
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(1d);
        coordinates.add(-1d);

        // when mockHelper.httpPost() return value is not null, make it throw an exception.
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            Tweet expected = TweetUtil.buildTweet(text, coordinates);
            dao.create(expected);
            fail();
        } catch (RuntimeException ex) {
            assertTrue(true);
        }

        // test happy path.
        // make a spyDao which can fake parseResponse return value
        String tweetJsonStr = "{\n"
                + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "  \"id\":1097607853932564480,\n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"test with location\",\n"
                + "  \"entities\":{\n"
                + "    \"hashtags\":[],\n"
                + "    \"user_mentions\":[]\n"
                + "  },\n"
                + "  \"coordinates\":null,\n"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expected = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);

        // mock parseResponse, make spyDao.parseResponse return expectedTweet
        // note that any() and anyInt() are argument matcher to match type contract, but not passing any
        //     real values
        doReturn(expected).when(spyDao).parseResponse(any(), anyInt());
        Tweet tweet = spyDao.create(TweetUtil.buildTweet(text, coordinates));
        assertEquals("test with location", tweet.getText());
    }

    @Test
    public void findById() throws Exception {
        // test happy path.
        // make a spyDao which can fake parseResponse return value
        String tweetJsonStr = "{\n"
                + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "  \"id\":1097607853932564480,\n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"test with location\",\n"
                + "  \"entities\":{\n"
                + "    \"hashtags\":[],\n"
                + "    \"user_mentions\":[]\n"
                + "  },\n"
                + "  \"coordinates\":null,\n"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);

        // mock parseResponse, make spyDao.parseResponse return expectedTweet
        doReturn(expectedTweet).when(spyDao).parseResponse(any(), anyInt());
        Tweet tweet = spyDao.findById("1097607853932564480");
        assertEquals("1097607853932564480", tweet.getIdStr());
    }

    @Test
    public void deleteById() throws Exception {
        // test happy path.
        // make a spyDao which can fake parseResponseBody return value
        String tweetJsonStr = "{\n"
                + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "  \"id\":1097607853932564480,\n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"test with location\",\n"
                + "  \"entities\":{\n"
                + "    \"hashtags\":[],\n"
                + "    \"user_mentions\":[]\n"
                + "  },\n"
                + "  \"coordinates\":null,\n"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);

        // mock parseResponse, make spyDao.parseResponse return expectedTweet
        doReturn(expectedTweet).when(spyDao).parseResponse(any(), anyInt());
        Tweet tweet = spyDao.deleteById("1097607853932564480");
        assertEquals("1097607853932564480", tweet.getIdStr());
    }
}