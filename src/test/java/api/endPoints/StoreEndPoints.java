package api.endPoints;

import api.payload.Store;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StoreEndPoints {
    public static Response createStore(Store payLoad) {
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(payLoad)
                .when()
                .post(Routes.store_post_url);
        return response;
    }

    public static Response readStore(int orderID) {
        Response response = RestAssured.given()
                .pathParam("orderId", orderID)
                .when()
                .get(Routes.store_get_url);
        return response;
    }

    public static Response deleteStore(int orderID) {
        Response response = RestAssured.given()
                .pathParam("orderId", orderID)
                .when()
                .delete(Routes.store_get_url);
        return response;
    }
}
