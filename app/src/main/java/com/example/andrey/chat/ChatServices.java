package com.example.andrey.chat;



import retrofit2.Call;
import retrofit2.http.GET;


public interface ChatServices {

    @GET("api/chat/channels/")
    Call<ChatRetrofit> getQuery();
}
