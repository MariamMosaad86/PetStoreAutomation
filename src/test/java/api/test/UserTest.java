package api.test;

import api.endPoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker faker;
    User userPayLoad;

    public Logger logger ; // for logs

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayLoad = new User();

        userPayLoad.setId(faker.idNumber().hashCode());
        userPayLoad.setUsername(faker.name().username());
        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().safeEmailAddress());
        userPayLoad.setPassword(faker.internet().password(5,10));
        userPayLoad.setPhone(faker.phoneNumber().cellPhone());

        // logs
        logger= LogManager.getLogger(this.getClass());
        logger.debug("debugging.....");


    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("*************** Creating User **************");

        Response response = UserEndPoints.createUser(userPayLoad);
        response.then().log().all();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        logger.info("*************** User is created **************");

    }

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("*************** Reading user info **************");

        Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());
        response.then().log().all();

        int StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 200);

        logger.info("*************** User Info is displayed **************");
    }

    @Test(priority = 3)
    public void testUpdateUser() {
        logger.info("*************** Updating user **************");

        // Update data using payLoad
        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayLoad.getUsername(), userPayLoad);
        response.then().log().all();

        logger.info("*************** User is updated **************");


        // Checking data after update

        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayLoad.getUsername());
        responseAfterUpdate.then().log().all();

        int StatusCode = responseAfterUpdate.getStatusCode();
        Assert.assertEquals(StatusCode, 200);


    }
//    @Test (priority = 4) // like ResponseAfterUpdate
    public void testGetUserAfterUpdate(){
        Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());
        response.then().log().all();
    }

    @Test(priority = 5)
    public void testDeleteUser() {
        logger.info("*************** Deleting user **************");

        Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());
        response.then().log().all();

        int StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 200);

        logger.info("*************** User is deleted **************");


    }
}