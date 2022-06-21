package bootpay.javaApache.model.request;

import java.util.List;

public class SubscribePayload {
    public String billingKey;
    public String itemName;
    public long price;
    public int taxFree;
    public String orderId;
    public int quota;
    public int interest;
    public User userInfo;
    public List<Item> items;
    public String feedbackUrl;
    public String feedbackContentType;
    public SubscribeExtra extra;
    public String schedulerType; //�젙湲곌껐�젣 �삁�빟�떆
    public long executeAt; //�젙湲곌껐�젣 �삁�빟�떆
}
