package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String TOKEN_PREFIX = "token_";

    public interface RedisCacheExtime {
        int REDIS_SESSION_EXTIME = 60 * 30; // 30分钟
    }

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    public interface Cart {
        int CHECKED = 1;//购物车中选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public interface Role {
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; //管理员
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线");
        private int code;
        private String value;
        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum OrderStatusEnum {
        CANCELED(0, "未支付"),
        NO_PAY(10, "已取消"),
        PAID(20, "已付款"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "订单完成"),
        ORDER_CLOSE(60, "订单关闭");

        OrderStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public interface AlipayCallback {
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayPlatformEnum {
        ALIPAY(1, "支付宝");

        PayPlatformEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum {
        ONLINE_PAY(1, "在线支付");

        PaymentTypeEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static PaymentTypeEnum codeOf(int code) {
            for (PaymentTypeEnum paymentTypeEnum : values()) {
                if (paymentTypeEnum.getCode() == code) {
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }







    public static final String MasterImagePath = "/Users/hugoo/Desktop/BaiduAI/";
    public static final String FaceImageSrc = MasterImagePath + "FaceRecognition/";
    public static final String FaceImageSrcForRegister = FaceImageSrc + "registerFace/";
    public static final String FaceImageSrcForTest = FaceImageSrc + "testFace/";
    public static final String BASE64 = "BASE64";

    public static final String APP_ID_OCR = "15675502";
    public static final String API_KEY_OCR = "8EYfhwV3vjm19ztB6NZUQctY";
    public static final String SECRET_KEY_OCR = "RXkFmw4kpBF0ZEeOGkBp6ALfuX6167KR";

    public static final String APP_ID_FACE = "15676897";
    public static final String API_KEY_FACE = "H1GwdF3VAYDczKGH2TP32UWQ";
    public static final String SECRET_KEY_FACE = "hPju91zVGLacVxOgM4sftoBys1hCe2PG";

}
