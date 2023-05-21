package com.example.tosepatu.api;

import com.example.tosepatu.model.login.Login;
import com.example.tosepatu.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<Login> loginResponse(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<Register> registerResponse(
            @Field("id") String id,
            @Field("id_users") String id_users,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password);
}