package bootpay.javaApache.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import bootpay.javaApache2.BootpayObject;

public class VerificationService {
    static public HttpResponse verify(BootpayObject bootpay, String receiptId) throws Exception {
        if(bootpay.token == null || bootpay.token.isEmpty()) throw new Exception("token 媛믪씠 鍮꾩뼱�엳�뒿�땲�떎.");

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = bootpay.httpGet("receipt/" + receiptId);
        get.setHeader("Authorization", bootpay.token);
        return client.execute(get);
    }

    static public HttpResponse certificate(BootpayObject bootpay, String receiptId) throws Exception {
        if(bootpay.token == null || bootpay.token.isEmpty()) throw new Exception("token 媛믪씠 鍮꾩뼱�엳�뒿�땲�떎.");

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = bootpay.httpGet("certificate/" + receiptId);
        get.setHeader("Authorization", bootpay.token);
        return client.execute(get);
    }
}
