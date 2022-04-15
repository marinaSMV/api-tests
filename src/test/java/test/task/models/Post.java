package test.task.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Post {
    @JsonAlias("userId")
    int userid;

    @JsonAlias("id")
    int id;

    @JsonAlias("title")
    String title;

    @JsonAlias("body")
    String body;

}
