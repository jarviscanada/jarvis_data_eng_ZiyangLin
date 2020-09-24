package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;

public class TwitterDao implements CrdDao<Tweet, String> {

    static final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

    // URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    // URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    // response code
    private static final int HTTP_OK = 200;

    private final HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) {
        URI createURI = constructURI(tweet);
        HttpResponse response = httpHelper.httpPost(createURI);
        return parseResponse(response, HTTP_OK);
    }

    public Tweet parseResponse(HttpResponse response, int expectedStatus) {
        // check if response code and entity is as expected
        if (expectedStatus != response.getStatusLine().getStatusCode()) {
            throw new RuntimeException("error: actual response status code differ from expected one.");
        } else if (response.getEntity() == null) {
            throw new RuntimeException("error: empty response received.");
        }

        // convert response entity to String
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
            logger.debug(jsonStr);
        } catch (IOException ex) {
            throw new RuntimeException("error: failed to convert response entity to JSON string.");
        }

        // convert entity String to Tweet object
        Tweet tweet = null;
        try {
            tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException ex) {
            throw new RuntimeException("error: failed to convert JSON string to object.");
        }

        return tweet;
    }

    @Override
    public Tweet findById(String s) {
        URI uri = URI.create(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s);
        HttpResponse response = httpHelper.httpGet(uri);
        return parseResponse(response, HTTP_OK);
    }

    @Override
    public Tweet deleteById(String s) {
        URI uri = URI.create(API_BASE_URI + DELETE_PATH + ".json" + QUERY_SYM + "id" + EQUAL + s);
        HttpResponse response = httpHelper.httpPost(uri);
        return parseResponse(response, HTTP_OK);
    }

    private URI constructURI(Tweet tweet) {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        return URI.create(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL +
                percentEscaper.escape(tweet.getText()) + AMPERSAND + "long" + EQUAL +
                tweet.getCoordinates().getCoordinates().get(0) + AMPERSAND + "lat" + EQUAL +
                tweet.getCoordinates().getCoordinates().get(1));
    }

}
