package bootpay.javaApache2;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import bootpay.javaApache.model.request.Cancel;
import bootpay.javaApache.model.request.Payload;
import bootpay.javaApache.model.request.Subscribe;
import bootpay.javaApache.model.request.SubscribePayload;
import bootpay.javaApache.model.request.UserToken;


public class Test {
    static Bootpay bootpay;
    public static void main(String[] args) {
        bootpay = new Bootpay("5b8f6a4d396fa665fdc2b5ea", "rm6EYECr6aroQVG2ntW0A6LpWnkTgP4uQ3H18sDDUYw=");
//        bootpay.getAccessToken();
//        api = new BootpayApi("5b8f6a4d396fa665fdc2b5ea", "rm6EYECr6aroQVG2ntW0A6LpWnkTgP4uQ3H18sDDUYw=");
        goGetToken();
//        getBillingKey();
//        requestSubscribe();
//        reserveSubscribe();
//        destroyBillingKey();
//        reserveCancelSubscribe();
//        receiptCancel();
//        getUserToken();
//        requestLink();
//        submit();
//        goVerfity();
//        certificate();
    }

    public static void goGetToken() {
        try {
            HttpResponse res = bootpay.getAccessToken();
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getBillingKey() {
        Subscribe subscribeBilling = new Subscribe();
        subscribeBilling.itemName = "�젙湲곌껐�젣 �뀒�뒪�듃 �븘�씠�뀥";
        subscribeBilling.orderId = "" + (System.currentTimeMillis() / 1000);
        subscribeBilling.pg = "nicepay";
//        subscribeBilling.cardNo = "5570**********1074"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
//        subscribeBilling.cardPw = "**"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
//        subscribeBilling.expireYear = "**"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
//        subscribeBilling.expireMonth = "**"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
//        subscribeBilling.identifyNumber = ""; //二쇰�쇰벑濡앸쾲�샇
        subscribeBilling.cardNo = "5570420456641074"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
        subscribeBilling.cardPw = "83"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
        subscribeBilling.expireYear = "26"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
        subscribeBilling.expireMonth = "12"; //�떎�젣 �뀒�뒪�듃�떆�뿉�뒗 *** 留덉뒪�겕泥섎━媛� �븘�땶 �닽�옄�뿬�빞 �븿
        subscribeBilling.identifyNumber = "8610141038021"; //二쇰�쇰벑濡앸쾲�샇

        try {
            HttpResponse res = bootpay.getBillingKey(subscribeBilling);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void destroyBillingKey() {
        try {
            HttpResponse res = bootpay.destroyBillingKey("6100e7ea0d681b001fd4de69");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestSubscribe() {
        SubscribePayload payload = new SubscribePayload();
        payload.billingKey = "6100e8c80d681b001dd4e0d7";
        payload.itemName = "�븘�씠�뀥01";
        payload.price = 1000;
        payload.orderId = "" + (System.currentTimeMillis() / 1000);


        try {
            HttpResponse res = bootpay.requestSubscribe(payload);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reserveSubscribe() {
        SubscribePayload payload = new SubscribePayload();

        payload.billingKey = "6100e77a0d681b002ad4e5d9";
        payload.itemName = "�븘�씠�뀥01";
        payload.price = 1000;
        payload.orderId = "" + (System.currentTimeMillis() / 1000);
        payload.executeAt = (System.currentTimeMillis() / 1000) + 10000;

        try {
            HttpResponse res = bootpay.reserveSubscribe(payload);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reserveCancelSubscribe() {
        try {
            HttpResponse res = bootpay.reserveCancelSubscribe("6100e892019943002150fef3");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiptCancel() {
        Cancel cancel = new Cancel();
        cancel.receiptId = "6100e77a019943003650f4d5";
        cancel.name = "愿�由ъ옄";
        cancel.reason = "�뀒�뒪�듃 寃곗젣";

//        String receipt_id = "";
        try {
            HttpResponse res = bootpay.receiptCancel(cancel);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getUserToken() {
        UserToken userToken = new UserToken();
        try {
            HttpResponse res = bootpay.getUserToken(userToken);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestLink() {
        Payload payload = new Payload();
        try {
            HttpResponse res = bootpay.requestLink(payload);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void submit() {
        try {
            HttpResponse res = bootpay.submit("");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goVerfity() {
        try {
            HttpResponse res = bootpay.verify("6100e8e7019943003850f9b0");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void certificate() {
        try {
            HttpResponse res = bootpay.certificate("593f8febe13f332431a8ddae");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void goCancel() {
//        Cancel cancel = new Cancel();
//        cancel.receiptId = "593f8febe13f332431a8ddae";
//        cancel.name = "愿�由ъ옄 �솉湲몃룞";
//        cancel.reason = "�깮諛� 吏��뿰�뿉 �쓽�븳 援щℓ�옄 痍⑥냼�슂泥�";
//
//        try {
//            HttpResponse res = bootpay.receiptCancel(cancel);
//            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
//            System.out.println(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void goSubscribeBilling() {
//        Subscribe subscribeBilling = new SubscribeBilling();
//        subscribeBilling.billing_key = "5b025b33e13f33310ce560fb";
//        subscribeBilling.item_name = "�젙湲곌껐�젣 �뀒�뒪�듃 �븘�씠�뀥";
//        subscribeBilling.price = 3000;
//        subscribeBilling.order_id = "" + (System.currentTimeMillis() / 1000);
//
//
//        try {
//            HttpResponse res = api.subscribe_billing(subscribeBilling);
//            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
//            System.out.println(str);
//            System.out.println(new Gson().toJson(subscribeBilling));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void goRemoteForm() {
//        RemoteForm form = new RemoteForm();
//        form.pg = "danal";
//        form.fm = Arrays.asList("card", "phone");
//        form.n = "�뀒�뒪�듃 寃곗젣";
//        form.o_key = "unique_value_1234"; // 媛�留뱀젏�쓽 �긽�뭹 怨좎쑀 �궎
//        form.is_r_n = false; // 援щℓ�옄媛� �긽�뭹紐� �엯�젰 �뿀�슜�븷吏� 留먯�
//        form.is_r_p = false; // 援щℓ�옄媛� 媛�寃� �엯�젰 �뿀�슜�븷吏� 留먯�
//        form.is_addr = false; // 二쇱냼李� 異붽� �븷吏� 留먯�
//        form.is_da = false; // 諛곗넚鍮� 異붽� �븷吏� 留먯�
//        form.is_memo = false;  // 援щℓ�옄濡쒕��꽣 硫붾え瑜� 諛쏆쓣 吏�
//        form.tfp = 0d; // 鍮꾧낵�꽭 湲덉븸
//        form.ip = 10000d; // �븘�씠�뀥 �뙋留ㅺ툑�븸
//        form.dp = 0d; // �뵒�뒪�뵆�젅�씠�슜 媛�寃�, �븷�씤�쟾 媛�寃⑹쓣 �쓽誘명븿, 荑좏룿�씠�굹 �봽濡쒕え�뀡�뿉 �쓽�븳 媛�寃� �뵒�뒪移댁슫�듃 媛쒕뀗 �븘�슂 - �럹�씠肄� �븣臾몄뿉 �깮湲� 媛쒕뀗
//        form.dap = 0d;  // 湲곕낯諛곗넚鍮�
//        form.dap_jj = 0d; // �젣二� 諛곗넚鍮�
//        form.dap_njj = 0d; // �젣二� �쇅 吏��뿭 �룄�꽌�궛媛� 異붽�鍮꾩슜
//
//
//       try {
//        HttpResponse res = api.remote_form(form);
//        String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
//        System.out.println(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
