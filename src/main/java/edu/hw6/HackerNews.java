package edu.hw6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_FORMAT = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    private static final int STATUS_OK = 200;

    public long[] hackerNewsTopStories() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOP_STORIES_URL))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_OK) {
                String json = response.body();
                Matcher matcher = Pattern.compile("\\d+").matcher(json);

                List<Long> result = new ArrayList<>();
                while (matcher.find()) {
                    result.add(Long.parseLong(matcher.group()));
                }

                return result.stream().mapToLong(Long::longValue).toArray();
            }
        } catch (Exception ignore) {
        }

        return new long[0];
    }

    public String news(long id) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(ITEM_URL_FORMAT, id)))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_OK) {
                String json = response.body();
                // Используйте регулярное выражение для извлечения названия новости
                Matcher matcher = Pattern.compile("\"title\":\"([^\"]+)\"").matcher(json);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        } catch (Exception ignore) {
        }

        return "";
    }
}
