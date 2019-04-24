package BaiduAI.test;

import BaiduAI.Common.Const;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

public class CharacterRecognition {

    public static void main(String[] args) {

        AipOcr client = new AipOcr(Const.APP_ID_OCR, Const.API_KEY_OCR, Const.SECRET_KEY_OCR);
        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("detect_direction", "true");  // 检测图像朝向
//        options.put("probability", "true");  // 返回识别结果中每一行的置信度


//        String Image = Const.MasterImagePath + "CharacterRecognition/handWord.png";
        String Image = Const.MasterImagePath + "CharacterRecognition/printWord.png";


        JSONObject res = client.basicAccurateGeneral(Image, options);
        System.out.println(res.toString(1));


    }

    @Test
    public void s() {
        Object a = 1;
        System.out.println((int) a);
    }


}
