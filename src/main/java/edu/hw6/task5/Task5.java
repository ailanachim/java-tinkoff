package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class Task5 {

    private Task5() {
    }

    public static long[] hackerNewsTopStories() {
        HttpRequest request =
            HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .build();

        try {
            var response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String ans = response.body();
            ans = ans.substring(1, ans.length() - 1);
            String[] strings = ans.split(",");

            long[] array = new long[strings.length];
            for (int i = 0; i < array.length; i++) {
                array[i] = Long.parseLong(strings[i]);
            }

            return array;
        } catch (Exception e) {
            return new long[] {};
        }
    }

    public static String news(long id) {
        HttpRequest request =
            HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/%d.json".formatted(id)))
                .build();

        try {
            var response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String ans = response.body();

            final Pattern pattern = Pattern.compile("\"title\":\"([^\"]*)\"");
            Matcher matcher = pattern.matcher(ans);

            if (matcher.find()) {
                MatchResult matchResult = matcher.toMatchResult();
                return matchResult.group(1);
            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }
}
