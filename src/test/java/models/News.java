package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.datafaker.Faker;

import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
public record News(
        @JsonProperty("id") long id,
        @JsonProperty("newsCategoryId") int newsCategoryId,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("creatorId") int creatorId,
        @JsonProperty("createDate") long createDate,
        @JsonProperty("publishDate") long publishDate,
        @JsonProperty("publishEnabled") boolean publishEnabled,
        @JsonProperty("creatorName") String creatorName
) {
    public static News generateNews(){
        Faker faker = new Faker(new Locale("ru"));
        return new News(
                0,
                faker.number().numberBetween(1, NewsCategory.values().length),
                faker.lorem().characters(20),
                faker.lorem().characters(50),
                0,
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                false,
                ""
        );
    }

    public boolean isPublished() {
        return publishEnabled;
    }

}