package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.example.TwitterApiTest;
import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper {

    static final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);

    /**
     * Dependencies are specified as private member variables
     */
    private final OAuthConsumer consumer;
    private final DefaultHttpClient httpClient;

    public TwitterHttpHelper() {
        final String CONSUMER_KEY = System.getenv("consumerKey");
        final String CONSUMER_SECRET = System.getenv("consumerSecret");
        final String ACCESS_TOKEN = System.getenv("accessToken");
        final String TOKEN_SECRET = System.getenv("tokenSecret");
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
        this.consumer = consumer;
        // default = single connection
        this.httpClient = new DefaultHttpClient();
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        try {
            HttpPost request = new HttpPost(uri);
            consumer.sign(request);
            HttpClient httpClient = HttpClientBuilder.create().build();
            return httpClient.execute(request);
        } catch (OAuthException ex) {
            throw new RuntimeException("error: fail to execute POST request due to OAuthException.");
        } catch (IOException ex) {
            throw new RuntimeException("error: fail to execute POST request due to IOException.");
        }
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        try {
            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        } catch (OAuthException ex) {
            throw new RuntimeException("error: fail to execute GET request due to OAuthException.");
        } catch (IOException ex) {
            throw new RuntimeException("error: fail to execute GET request due to IOException.");
        }
    }

    public static void main(String[] args) throws IOException {
        TwitterHttpHelper test = new TwitterHttpHelper();

        String status = "today is a good day!!!";
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpResponse response = test.httpPost(URI.create("http://api.twitter.com/1.1/statuses/update.json?status=" +
                percentEscaper.escape(status)));
        logger.debug(EntityUtils.toString(response.getEntity()));
    }
}
