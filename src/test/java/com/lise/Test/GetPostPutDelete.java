package com.lise.Test;

import com.google.gson.Gson;
import com.lise.BaseClass;
import com.lise.Model.*;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;



import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetPostPutDelete extends BaseClass {
    int id;


  private  static  Logger logger = LogManager.getLogger();
    @Test
    public void getAllTest() {


        List<GetResponse> list = new ArrayList<>();
        Response getResponse = getUser();
        JSONArray jsonArray = new JSONArray(getResponse.asString());
        for (int i = 0; i < jsonArray.length(); i++) {
            Gson gson = new Gson();
            GetResponse student = gson.fromJson(jsonArray.getJSONObject(i).toString(), GetResponse.class);
            list.add(student);
        }
        logger.info("This is  Info message");
        logger.fatal("This  is fatal message");
        logger.error("This is error message ");
        logger.debug("This is debug message ");
        logger.warn("This is warn message");
        logger.trace("This is trace message");

        assertThat(getResponse.getStatusCode(),is(HttpStatus.SC_OK));
    }

    @Test
    public void postTest() {
        //System.out.println("Post Method=>" + RestAssured.baseURI);
        PostBody postBody = new PostBody();
        Faker faker = new Faker();
        postBody.setName(faker.name().name());
        postBody.setEmail(faker.internet().emailAddress());
        postBody.setGender("male");
        postBody.setStatus("active");
        PostResponse postResponse = postUser(postBody);

        JSONObject jsonObject = new JSONObject(postResponse);

        id = postResponse.getId();
        int id = jsonObject.getInt("id");
        Response res = deleteUser(id);

    }

    @Test
    public void updateTest() {
       // System.out.println("update Method =>" + RestAssured.baseURI);
        PostBody postBody = new PostBody();
        Faker faker = new Faker();
        postBody.setName(faker.name().name());
        postBody.setEmail(faker.internet().emailAddress());
        postBody.setGender("male");
        postBody.setStatus("active");
        PostResponse postResponse = postUser(postBody);

        JSONObject jsonObject = new JSONObject(postResponse);
        int id = jsonObject.getInt("id");

        PutBody putBody = new PutBody();
        Faker faker1 = new Faker();
        putBody.setName(faker1.name().name());
        putBody.setEmail(faker1.internet().emailAddress());
        putBody.setGender("male");
        putBody.setStatus("inactive");
        PutResponse updateResponse = updateUser(putBody, id);

        Response res = deleteUser(id);
    }
    @Test
    public void deleteTest() {

        PostBody postBody = new PostBody();
        Faker faker = new Faker();
        postBody.setName(faker.name().name());
        postBody.setEmail(faker.internet().emailAddress());
        postBody.setGender("female");
        postBody.setStatus("inactive");
        PostResponse postResponse = postUser(postBody);
        JSONObject jsonObject = new JSONObject(postResponse);
        int id = jsonObject.getInt("id");
        Response deleteResponse = deleteUser(id);
        assertThat(deleteResponse.getStatusCode(), is(HttpStatus.SC_NO_CONTENT));

    }
    @Test
    public void newBranchTest1(){
        System.out.println("Print new branch origin ");
    }

    //Get Method
    public Response getUser() {
        Response getResponse = given()
                .request(Method.GET, "/users");
        return getResponse;
    }

    //Post Method
    public PostResponse postUser(PostBody postBody) {
        PostResponse postResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(postBody)
                .when()
                .request(Method.POST, "/users")
                .as(PostResponse.class);
        return postResponse;
    }

    //Update Method
    public PutResponse updateUser(PutBody putBody, int id) {
        PutResponse putResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(putBody)
                .when()
                .request(Method.PUT, "/users/" + id)
                .as(PutResponse.class);
        return putResponse;
    }
    //Delete Method
    public Response deleteUser(int id) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .request(Method.DELETE, "/users/" + id);
        return response;
    }
}