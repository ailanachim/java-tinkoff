package edu.hw6;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static edu.hw6.task5.Task5.hackerNewsTopStories;
import static edu.hw6.task5.Task5.news;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class Task5Test {

    @Test
    void topStoriesTest() throws IOException, InterruptedException {
        String responseStories = "[38430074,38429291,38418865,38402368,38429460,38401188,38429817]";
        long[] expectedArray = new long[] {38430074, 38429291, 38418865, 38402368, 38429460, 38401188, 38429817};


        HttpClient TopStoriesClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        Mockito.when(TopStoriesClient.send(any(), eq(HttpResponse.BodyHandlers.ofString()))).thenReturn(response);
        Mockito.when(response.body()).thenReturn(responseStories);

        long[] stories = hackerNewsTopStories(TopStoriesClient);

        assertThat(stories).isEqualTo(expectedArray);
    }

    @Test
    void newsTest() throws IOException, InterruptedException {
        HttpClient NewsClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> newsResponse = Mockito.mock(HttpResponse.class);
        String newsInfo = """
            {"by":"Tycho87",
            "descendants":39,
            "id":38430074,
            "kids":[38431114,38430976,38431133,38431028,38431144,38431225,38431417],
            "score":84,
            "time":1701076732,
            "title":"Where Is OpenCV 5?",
            "type":"story",
            "url":"https://opencv.org/blog/where-is-opencv-5/"
            }""";

        String title = "Where Is OpenCV 5?";

        Mockito.when(NewsClient.send(any(), eq(HttpResponse.BodyHandlers.ofString()))).thenReturn(newsResponse);
        Mockito.when(newsResponse.body()).thenReturn(newsInfo);

        assertThat(news(NewsClient, 38430074)).isEqualTo(title);
    }
}
