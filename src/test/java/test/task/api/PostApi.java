package test.task.api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import test.task.models.Post;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostApi {
    private String postsUrl;

    public PostApi(String baseUrl) {
        this.postsUrl = baseUrl + "/posts";
    }

    public Post getPost(long id, int expectedStatusCode) {
        Response response = given().get(postsUrl + "/" + id);
        validateStatusCode(response, expectedStatusCode);
        return response.as(Post.class);
    }

    public Post getPost(long id) {
        return getPost(id, HttpStatus.SC_OK);
    }

    public List<Post> getPosts() {
        Response response = given().get(postsUrl);
        validateStatusCode(response, HttpStatus.SC_OK);
        return Arrays.asList(response.as(Post[].class));
    }

    public Post addPost(Post post, int expectedStatusCode) {
        Response response = given()
                .body(post)
                .post(postsUrl);
        validateStatusCode(response, expectedStatusCode);
        return response.as(Post.class);
    }

    public Post addPost(Post post) {
        return addPost(post, HttpStatus.SC_CREATED);
    }

    public Post addPost(Map<String, Object> post, int expectedStatusCode) {
        Response response = given()
                .body(post)
                .post(postsUrl);
        validateStatusCode(response, expectedStatusCode);
        return response.as(Post.class);
    }

    private void validateStatusCode(Response response, int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

}
