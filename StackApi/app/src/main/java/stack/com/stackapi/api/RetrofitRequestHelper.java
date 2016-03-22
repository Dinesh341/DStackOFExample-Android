/**
 * @category Contus
 * @package com.contus.tripwall.api
 * @version 1.0
 * @author Contus Team <developers@contus.in>
 * @copyright Copyright (C) 2015 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package stack.com.stackapi.api;


import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import stack.com.stackapi.utils.AppConstants;

/**
 * RetrofitRequestHelper.java
 */
public class RetrofitRequestHelper {
    private static ApiRequestInterface mApiRequestInterface;
    /** Webservice URL **/
    private static String API_URL = AppConstants.APP_BASE_URL;
    /** Google's JSON Parser GSON Instance**/
  //  private final Gson mGson;

    public RetrofitRequestHelper() {
        mApiRequestInterface = new RestAdapter.Builder()
                .setEndpoint(API_URL) // API Endpoint url
                .setClient(new OkClient(new OkHttpClient())) // Http client -OkHttpClient
                .setConverter(new StringConverter()) // String response converter
                .setLogLevel(RestAdapter.LogLevel.FULL)// To enable log.
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        /*AppPreference appPreference = new AppPreference(context);
                        //Custom Request header
                        request.addHeader("userid",appPreference.getUserId() );*/
                    }
                })
                .build()
                .create(ApiRequestInterface.class);
    }


    /**
     * Gets instance.
     * @return the instance of the ApiRequestInterface.
     */
    public ApiRequestInterface getInstance() {
        return mApiRequestInterface;
    }
}
