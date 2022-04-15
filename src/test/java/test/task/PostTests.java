package test.task;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.task.api.PostApi;
import test.task.models.Post;

import java.util.List;

import static data_provider.Configs.getBaseUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("REST API Testing using JUnit5")
@Feature("Testing Posts")

public class PostTests {

    private PostApi postApi = new PostApi(getBaseUrl());

    @Test
    @Story("GET Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Checking existing post")
    public void getExistingPostReturnsValidData() {
        Post exPost = postApi.getPost(2);
        assertEquals(1, exPost.getUserid());
        assertEquals("qui est esse", exPost.getTitle());
        assertEquals("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla", exPost.getBody());
        assertNotNull(exPost.getId());
    }

    @Test
    @Story("GET Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the details of post with id-2 with RestAssured")
    public void verifyPost() {
        // Given
        given()
                .filter(new AllureRestAssured())
                // WHEN
                .when()
                .get(getBaseUrl() + "/posts/2")
                // THEN
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("title", equalTo("qui est esse"))
                .body("body", equalTo("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"));
    }

    @Test
    @Story("GET Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : List of existing posts")
    public void getListOfPosts() {
        List<Post> posts = postApi.getPosts();
        assertEquals(100, posts.size(), "Wrong size");
        posts.forEach(post -> {
            Assertions.assertNotNull(post.getId());
            Assertions.assertNotNull(post.getUserid());
            Assertions.assertNotNull(post.getBody());
            Assertions.assertNotNull(post.getTitle());
        });
    }

    //will testing if API'll have a connect
    /*@Test
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the error in the creation of a new post")
    void createPostShouldNotCreateNewPostWhenStringUserId() {
        // given
        Map<String, Object> badPost = new HashMap<String, Object>() {
            {
                put("userId", "string");
            }
        };
        // when - then
        postApi.addPost(badPost, HttpStatus.SC_BAD_REQUEST);
    }*/


    //will testing if API'll have a connect
    /*@Test
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the creation of a new post")
    void createPostShouldCreateNewPost() {
        // given
        Post post = Post.builder()
                .userid(1)
                .title("test title")
                .body("test body")
                .build();
        // when
        Post addedPost = postApi.addPost(post);
        // then
        assertEquals(1, addedPost.getUserid());
        assertEquals("test title", addedPost.getTitle());
        assertEquals("test body", addedPost.getBody());
        assertNotNull(addedPost.getId());
    }*/


}
