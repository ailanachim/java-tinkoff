package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task5 {

    public final static String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    public final static String BASE_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    public static long[] hackerNewsTopStories(HttpClient client) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOP_STORIES_URL))
                .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String ans = response.body();
            ans = ans.substring(1, ans.length() - 1);
            String[] strings = ans.split(",");

            long[] array = new long[strings.length];
            for (int i = 0; i < array.length; i++) {
                array[i] = Long.parseLong(strings[i]);
            }

            return array;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new long[] {};
        }
    }

    public static String news(HttpClient client, long id) {
        HttpRequest request =
            HttpRequest.newBuilder()
                .uri(URI.create(getNewsUrl(id)))
                .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String ans = response.body();

            final Pattern pattern = Pattern.compile("\"title\":\"([^\"]*)\"");
            Matcher matcher = pattern.matcher(ans);

            if (matcher.find()) {
                MatchResult matchResult = matcher.toMatchResult();
                return matchResult.group(1);
            }
            LOGGER.error("News title was not found");
            return null;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private static String getNewsUrl(long id) {
        return BASE_URL.formatted(id);
    }
}
