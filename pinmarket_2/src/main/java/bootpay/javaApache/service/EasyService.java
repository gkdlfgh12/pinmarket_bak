package bootpay.javaApache.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bootpay.javaApache.model.request.UserToken;
import bootpay.javaApache2.BootpayObject;

//媛꾪렪寃곗젣李�, �깮泥댁씤利� 湲곕컲 媛꾪렪 寃곗젣 �벑
public class EasyService {
    static public HttpResponse getUserToken(BootpayObject bootpay, UserToken userToken) throws Exception {
        if(bootpay.token == null || bootpay.token.isEmpty()) throw new Exception("token 媛믪씠 鍮꾩뼱�엳�뒿�땲�떎.");
        if(userToken.userId == null || userToken.userId.isEmpty()) throw new Exception("userId 媛믪쓣 �엯�젰�빐二쇱꽭�슂.");

        HttpClient client = HttpClientBuilder.create().build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        HttpPost post = bootpay.httpPost("request/user/token", new StringEntity(gson.toJson(userToken), "UTF-8"));

        post.setHeader("Authorization", bootpay.token);
        return client.execute(post);
    }
}
