package bootpay.javaApache.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.github.scribejava.core.httpclient.HttpClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bootpay.javaApache.model.request.Payload;
import bootpay.javaApache2.BootpayObject;

public class LinkService {
    static public HttpResponse requestLink(BootpayObject bootpay, Payload payload) throws Exception {
        if(bootpay.token == null || bootpay.token.isEmpty()) throw new Exception("token 媛믪씠 鍮꾩뼱�엳�뒿�땲�떎.");
//        if(userToken.userId == null || userToken.userId.isEmpty()) throw new Exception("userId 媛믪쓣 �엯�젰�빐二쇱꽭�슂.");

		/* HttpClient client = HttpClientBuilder.create().build(); */
        CloseableHttpClient client = HttpClientBuilder.create().build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        HttpPost post = bootpay.httpPost("request/user/token", new StringEntity(gson.toJson(payload), "UTF-8"));

        post.setHeader("Authorization", bootpay.token);
        return client.execute(post);
    }
}
