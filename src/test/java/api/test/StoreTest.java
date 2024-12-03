package api.test;

import api.endPoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

public class StoreTest {

    Faker faker;
    Store storePayLoad;

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        storePayLoad = new Store();


        storePayLoad.setId(faker.idNumber().hashCode());
        storePayLoad.setPetId(faker.idNumber().hashCode());
        storePayLoad.setQuantity(faker.number().numberBetween(1, 10));
        storePayLoad.setShipDate(new Date());


    }


    @Test(priority = 1)
    public void tetPostStore() {
        Response response = StoreEndPoints.createStore(storePayLoad);
        response.then().log().all();

    }

    @Test(priority = 2)
    public void getStoreByOrderId(){
        Response response =StoreEndPoints.readStore(this.storePayLoad.getPetId());
        response.then().log().all();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test (priority = 3)
    public void DeleteStoreByOrderID(){
        Response response =StoreEndPoints.deleteStore(this.storePayLoad.getPetId());
        response.then().log().all();
    }
}

