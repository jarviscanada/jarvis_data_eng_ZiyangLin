package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TwitterService implements Service {

    private final CrdDao<Tweet, String> dao;

    public TwitterService(CrdDao<Tweet, String> dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        int code  = validateTweet(tweet);
        if (code == 0) {
            return dao.create(tweet);
        } else if (code == 1) {
            throw new IllegalArgumentException("error: longitude must be in range [-180, 180]");
        } else {
            throw new IllegalArgumentException("error: latitude must be in range [-90, 90].");
        }
    }

    /**
     * Find the corresponding tweet for input id, and throw an exception if the id is invalid.
     * @param id tweet id
     * @param fields set fields not in the list to null
     * @return
     */
    @Override
    public Tweet showTweet(String id, String[] fields) {
        if (!validateId(id)) {
            throw new IllegalArgumentException("error: tweet id is invalid.");
        } else {
            return dao.findById(id);
        }
    }

    /**
     * Test all id in ids for validity, and only delete those id are valid.
     * Throw an exception when there is no valid id in the array.
     *
     * @param ids tweet IDs which will be deleted
     * @return list of deleted tweets
     */
    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<String> validIds = new ArrayList<>();
        List<Tweet> deleted = new ArrayList<>();
        for (String id : ids) {
            if (validateId(id)) {
                validIds.add(id);
            }
        }

        if (validIds.size() == 0) {
            throw new IllegalArgumentException("error: no valid id is provided.");
        } else {
            validIds.forEach(id -> deleted.add(dao.deleteById(id)));
            return deleted;
        }
    }

    /**
     * If a tweet is longer than 140 characters, remove the exceeding part.
     * If the provided longitude and latitude are not in range [-180, 180] and [-90, 90] respectively,
     *     return the corresponding error code.
     *
     * @param tweet a tweet to be test valid.
     * @return 0 if tweet is valid or is trimmed to valid, 1 if longitude invalid, 2 if latitude invalid.
     */
    public int validateTweet(Tweet tweet) {
        if (tweet.getText().length() > 140) {
            tweet.setText(tweet.getText().substring(0, 139));
            return 0;
        } else if (tweet.getCoordinates().getCoordinates().get(0) > 180 ||
                tweet.getCoordinates().getCoordinates().get(0) < -180) {
            return 1;
        } else if (tweet.getCoordinates().getCoordinates().get(0) > 90 ||
                tweet.getCoordinates().getCoordinates().get(0) < -90) {
            return 2;
        }
        return 0;
    }

    /**
     * Test if the id string can be parsed into long and if its length is 19.
     *
     * @param id the id string to test
     * @return true if valid, and false if invalid.
     */
    public boolean validateId(String id) {
        if (id.length() != 19 && id.length() != 18) {
            return false;
        }
        try {
            Long.parseLong(id);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
