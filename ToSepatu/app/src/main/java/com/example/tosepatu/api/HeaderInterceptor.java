package com.example.tosepatu.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    private HashMap<String, String> requestHeaders;

    public HeaderInterceptor(HashMap<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders; // Assuming AppPreferences is a singleton
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String path = chain.request().url().toString();
        System.out.println("<<<<<<<<< " + path);

        Request request = mapHeaders(chain);
        return chain.proceed(request);
    }

    private Request mapHeaders(Chain chain) {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();

        for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        return requestBuilder.build();
    }
}
