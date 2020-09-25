package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

    private final TwitterController controller;

    @Autowired
    public TwitterCLIApp(TwitterController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {

        // setting up dependencies
        TwitterHttpHelper httpHelper = new TwitterHttpHelper();
        TwitterDao dao = new TwitterDao(httpHelper);
        TwitterService service = new TwitterService(dao);
        TwitterController controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);

        app.run(args);
    }

    public void run(String[] args) {
        if (args.length != 2 && args.length != 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post|show|delete [option1] [option2]");
        }

        String operation = args[0];
        switch (operation) {
            case "post":
                printTweet(controller.postTweet(args));
                break;
            case "show":
                printTweet(controller.showTweet(args));
                break;
            case "delete":
                controller.deleteTweet(args).forEach(this::printTweet);
                break;
            default:
                throw new IllegalArgumentException("USAGE: TwitterCLIApp post|show|delete [option1] [option2]");
        }
    }

    public void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonUtil.toJson(tweet, true, false));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("error: unable to convert tweet object to json for printing.");
        }
    }

}
