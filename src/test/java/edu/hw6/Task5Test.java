package edu.hw6;

import org.junit.jupiter.api.Test;
import static edu.hw6.task5.Task5.hackerNewsTopStories;
import static edu.hw6.task5.Task5.news;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    void topStoriesTest() {
        long[] stories = hackerNewsTopStories();

        assertThat(stories.length).isGreaterThan(400);
    }

    @Test
    void newsTest() {
        assertThat(news(38331750)).isEqualTo("Lindenmayer Systems");
        assertThat(news(38331349)).isEqualTo(
            "Zero-k: A libre sci-fi RTS game, with an economy based on metal and energy");
        assertThat(news(38331669)).isEqualTo("Comparing Humans, GPT-4, and GPT-4V on Abstraction and Reasoning Tasks");
        assertThat(news(0)).isEqualTo(null);
        assertThat(news(-1)).isEqualTo(null);
    }
}
