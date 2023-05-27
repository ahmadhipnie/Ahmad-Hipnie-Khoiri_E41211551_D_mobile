package com.example.tosepatu.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://ef63-2001-448a-5122-1fed-182-3675-7662-85b8.ngrok-free.app/api/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit== null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().addInterceptor(getHeaderAppJsonInterceptor()).connectTimeout(120, TimeUnit.SECONDS)
                            .readTimeout(120, TimeUnit.SECONDS).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static Interceptor getHeaderAppJsonInterceptor() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return new HeaderInterceptor(headers);
    }

}
