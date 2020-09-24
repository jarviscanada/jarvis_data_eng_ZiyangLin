package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {

    @Test
    public void httpPost() throws Exception{
        TwitterHttpHelper test = new TwitterHttpHelper();
        String status = "today is a good day!!!";
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpResponse response = test.httpPost(URI.create("http://api.twitter.com/1.1/statuses/update.json?status=" +
                percentEscaper.escape(status)));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}