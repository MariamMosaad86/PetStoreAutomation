package api.endPoints;

//UserEndPoints.java
// Created for perform ---> Create, Update, Read, Delete requests to the user API,

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

public class UserEndPoints2 {
    // Method created for getting URL's from properties file
    static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes");    //Load properties file /// routes here is name of propertie file
        return routes;

    }

    public static Response createUser(User payload) {

        String post_url = getURL().getString("post_url");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public static Response readUser(String userName) {

        String get_url = getURL().getString("get_url");
        Response response = RestAssured.given()
                .pathParam("username", userName)
                .when()
                .get(get_url);

        return response;
    }

    public static Response updateUser(String userName, User payload) {

        String update_url = getURL().getString("update_url");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(update_url);

        return response;
    }

    public static Response deleteUser(String userName) {

        String delete_url = getURL().getString("delete_url");
        Response response = RestAssured.given()
                .pathParam("username", userName)
                .when()
                .delete(delete_url);

        return response;
    }

}