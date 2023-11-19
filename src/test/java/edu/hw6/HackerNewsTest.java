package edu.hw6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HackerNewsTest {

    @Test
    void hackerNewsTopStories() {
        HackerNews hackerNews = new HackerNews();
        long[] topStories = hackerNews.hackerNewsTopStories();
        assertNotNull(topStories);
    }

    @Test
    void news() {
        HackerNews hackerNews = new HackerNews();
        String newsTitle = hackerNews.news(37570037);
        assertEquals("JDK 21 Release Notes", newsTitle);

    }
}
