package BaiduAI.test;

import java.util.HashMap;
import java.util.Map;

import BaiduAI.util.*;

public class EasydlObjectDetection {

    public static String easydlObjectDetection() {
        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/detection/identifyCooker";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("/Users/hugoo/Desktop/my.jpg")));
            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.d4eeb342796a81c2ca93003c0d717c2f.2592000.1555844293.282335-15826850";

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        EasydlObjectDetection.easydlObjectDetection();
    }
}

