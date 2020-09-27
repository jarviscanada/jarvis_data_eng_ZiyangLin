package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
// @Configuration is a Spring config file which defines dependency relationship
public class TwitterCLIBean {

    public static void main(String[] args) {
        // create an IoC container
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);
    }

    // Ioc will automatically create dependencies and manage their relationship
    @Bean
    public TwitterCLIApp twitterCLIApp(TwitterController controller) {
        return new TwitterCLIApp(controller);
    }

    @Bean
    public Controller twitterController(TwitterService service) {
        return new TwitterController(service);
    }

    @Bean
    public Service twitterService(TwitterDao dao) {
        return new TwitterService(dao);
    }

    @Bean
    public TwitterDao twitterDao(TwitterHttpHelper httpHelper) {
        return new TwitterDao(httpHelper);
    }

    @Bean
    TwitterHttpHelper helper() {
        return new TwitterHttpHelper();
    }
}
