package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {

    private TwitterDao dao;

    @Before
    public void setUp() {
        // set up and pass dependency
        TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper();
        this.dao = new TwitterDao(twitterHttpHelper);
    }

    @Test
    public void create() throws Exception {
        String text = "let's have some fun!";
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(1d);
        coordinates.add(-1d);
        Tweet expected = TweetUtil.buildTweet(text, coordinates);
        Tweet actual = dao.create(expected);

        assertEquals(text, actual.getText());
        assertNotNull(actual.getCoordinates());
        assertEquals(2, actual.getCoordinates().getCoordinates().size());
    }

    @Test
    public void show() throws Exception {
        String id = "1308955743278837760";
        String text = "hey let's have some fun!";
        Tweet actual = dao.findById("1308955743278837760");

        assertEquals(text, actual.getText());
        assertEquals(id, actual.getIdStr());
    }

    @Test
    public void deleteById() throws Exception {
        String id = "1308955743278837760";
        String text = "hey let's have some fun!";
        Tweet actual = dao.deleteById("1308955743278837760");

        assertEquals(text, actual.getText());
        assertEquals(id, actual.getIdStr());
    }
}