import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BookStoreApiSteps {


    String strValidUserName = "admin";
    String strValidPassword = "password";
    String strBaseURL = "http://localhost:8081/";
    String strValidEndpoint = "getTopResultsApi";
    String strValidParam = "count";



    @Step
    public String  getResult()
    {
        String requestURL = "http://localhost:8081/getTopResultsApi" ;
        Response response = given()
            .auth()
            .basic("admin", "password").queryParam("count","3").get(requestURL);

        //String jsonResponse = response.body().toString();
        ResponseBody body = response.getBody();

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());
        return body.asString();
    }


    @Step ("Login with user name as {0}, password as {1} and count value is {2}")
    public Response loginWithValidCredentialsAndCallApiWithCount(String userName, String usrPassword, String count)
    {
        String requestURL = strBaseURL+ strValidEndpoint ;

        Response response = given()
                .auth()
                .basic(userName, usrPassword).when().queryParam("count",count).get(requestURL);

        ResponseBody responseBody = response.getBody();

        return response;

    }

    @Step ("Check the status code if the endpoint name ({0}) is incorrect")
    public Response checkStatusCodeWithInvalidEndpoint(String endpointName, int count)
    {
        String requestURL = strBaseURL +endpointName ;

        Response response = given()
                .auth()
                .basic(strValidUserName, strValidPassword).when().queryParam("count",count).get(requestURL);

        ResponseBody responseBody = response.getBody();

        return response;

    }


    @Step ("Call API when the query parameter name ({0}) is incorrect")
    public Response callAPIWithInvalidQueryParameter(String strParamCount, int count)
    {
        String requestURL = strBaseURL +strValidEndpoint ;

        Response response = given()
                .auth()
                .basic(strValidUserName, strValidPassword).when().queryParam(strParamCount,count).get(requestURL);

        return response;

    }

    @Step ("Check the status code if the endpoint name ({0}) is incorrect")
    public Response callAPIWithInvalidEndpoint(String endpointName, int count)
    {
        String requestURL = strBaseURL+endpointName ;

        Response response = given()
                .auth()
                .basic(strValidUserName, strValidPassword).when().queryParam(strValidParam,count).get(requestURL);

        return response;

    }

    @Step("Checking if returned Status code {1} is as expected {0}")
    public void isStatusCodeCorrect(int expectedCode, int actualCode)
    {
        Assert.assertEquals(expectedCode,actualCode);
    }

    @Step("Check the number of words returned and compare them with count {1} passed in the query parameter")
    public void isCorrectObjectsReturned(String jsonBody, int count)
    {
        JSONObject jsonObject = new JSONObject(jsonBody);
        Assert.assertEquals(jsonObject.length(), count);
    }



    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
