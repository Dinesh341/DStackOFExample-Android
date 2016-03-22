package stack.com.stackapi.api;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;
import retrofit.mime.MultipartTypedOutput;

/**
 * ApiRequestInterface.java
 * This interface contains all the API request list.
 */
public interface ApiRequestInterface {

    /***
     * To register and checking if the user already exists with email
     */
    @GET("/questions")
    void getAnswerDetails(@QueryMap Map<String, String> postCredential, Callback<String> callback);
    /***
     * To register and checking if the user already exists with email
     */
    @GET("/me/associated")
    void getUserProfile(@QueryMap Map<String, String> postCredential, Callback<String> callback);
}
