package com.yamamz.hroracle.retrofitAPI;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raymundo T. Melecio on 11/30/2016.
 */


public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder;
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null,null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password,
                                      String server) {

        if (username != null && password != null && server!=null) {
            String API_BASE_URL = "http://" + server + ":8080/WebService/";
            builder = new Retrofit.Builder()
                            .baseUrl(API_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create());
            String credentials = username + ":" + password;
            final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Accept", "application/json")
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}