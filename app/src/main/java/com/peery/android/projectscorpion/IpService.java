package com.peery.android.projectscorpion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpService {
    @GET("/")
    Call<Ip> getIP();
}
