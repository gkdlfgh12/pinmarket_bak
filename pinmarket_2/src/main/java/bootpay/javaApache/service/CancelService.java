package bootpay.javaApache.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bootpay.javaApache.model.request.Cancel;
import bootpay.javaApache2.BootpayObject;

public class CancelService {
    static public HttpResponse receiptCancel(BootpayObject bootpay, Cancel cancel) throws Exception {
        if(bootpay.token == null || bootpay.token.isEmpty()) throw new Exception("token 媛믪씠 鍮꾩뼱�엳�뒿�땲�떎.");
        if(cancel.receiptId == null || cancel.receiptId.isEmpty()) throw new Exception("receiptId 媛믪쓣 �엯�젰�빐二쇱꽭�슂.");


        HttpClient client = HttpClientBuilder.create().build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        HttpPost post = bootpay.httpPost("cancel", new StringEntity(gson.toJson(cancel), "UTF-8"));
        post.setHeader("Authorization", bootpay.token);
        return client.execute(post);
    }
}
