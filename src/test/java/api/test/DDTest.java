package api.test;

import api.endPoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String firstName, String lastName, String userEmail, String password, String phone) {
        User userPayLoad = new User();

        userPayLoad.setId(Integer.parseInt(userID));
        userPayLoad.setUsername(userName);
        userPayLoad.setFirstName(firstName);
        userPayLoad.setLastName(lastName);
        userPayLoad.setEmail(userEmail);
        userPayLoad.setPassword(password);
        userPayLoad.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayLoad);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)

    public void testDeleteUserByName(String userName){

        Response response = UserEndPoints.deleteUser(userName);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

}
