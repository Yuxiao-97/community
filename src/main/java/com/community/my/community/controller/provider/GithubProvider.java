package com.community.my.community.controller.provider;

import com.alibaba.fastjson.JSON;
import com.community.my.community.controller.dto.AccessTokenDTO;
import com.community.my.community.controller.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public  String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String url = "https://github.com/login/oauth/access_token";
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String accessToken = split[0].split("=")[1];
            return accessToken;
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
//        String url = "https://api.github.com/user"  ;
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser  = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
