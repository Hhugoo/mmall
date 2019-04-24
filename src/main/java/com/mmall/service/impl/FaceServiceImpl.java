package com.mmall.service.impl;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.ocr.AipOcr;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mmall.common.Const;
import com.mmall.service.IFaceService;
import com.mmall.util.ImageToByte;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("iFaceService")
public class FaceServiceImpl implements IFaceService{

    private static AipFace client = new AipFace(Const.APP_ID_FACE, Const.API_KEY_FACE, Const.SECRET_KEY_FACE);

    private static AipOcr clientWords = new AipOcr(Const.APP_ID_OCR, Const.API_KEY_OCR, Const.SECRET_KEY_OCR);


    public String wordsRecognition(String photo) {
        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("detect_direction", "true");  // 检测图像朝向
//        options.put("probability", "true");  // 返回识别结果中每一行的置信度


//        String Image = Const.MasterImagePath + "CharacterRecognition/handWord.png";


        JSONObject res = clientWords.basicAccurateGeneral(photo, options);
        return res.toString();
    }

    //人脸检测
    public String faceDetect(String photo) throws Exception {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "3"); // 最多处理人脸的数目，默认为1
        options.put("face_field", "age,beauty"); //年龄，美貌
        //包括 age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype，逗号相隔
        options.put("face_type", "LIVE"); //表示生活照
        // IDCARD->身份证芯片照 WATERMARK->带水印证件照 CERT->证件照片. 默认LIVE


//        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrc+photo);
//        String image = new String(imageByte);


        JSONObject res = client.detect(photo, Const.BASE64, options);
//        System.out.println(res.toString(2));
        return res.toString();
    }


    //创建用户组
    public String createGroup(String groupId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.groupAdd(groupId, options);
        return res.toString();
    }


    //删除用户组
    public String deleteGroup(String groupId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.groupDelete(groupId, options);
        return res.toString();
    }


    //获取用户组列表
    public String getGroupList() {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("start", "0"); //默认值0，起始序号
        options.put("length", "50");  //返回数量，默认值100，最大值1000

        JSONObject res = client.getGroupList(options);
        return res.toString();
    }


    //人脸注册
    public String faceRegister(String photo, String groupId, String userId, String userInfo) throws Exception {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", userInfo); //用户信息
        options.put("quality_control", "NORMAL"); //表示图片质量控制 默认NONE->不控制；LOW->较低；NORMAL->一般；HIGH->较高
        options.put("liveness_control", "LOW");  //活体检测控制 默认NONE->不控制；LOW->较低；NORMAL->一般；HIGH->较高

        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrc+photo);
        String image = new String(imageByte);

        JSONObject res = client.addUser(image, Const.BASE64, groupId, userId, options);
        return res.toString();
    }


    //将人脸从人脸库中删除
    public String faceDelete(String groupId, String userId, String faceToken) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
        return res.toString();
    }


    //将人脸从特定组中删除
    public String faceDeleteFromGroup(String groupId, String userId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.deleteUser(groupId, userId, options);
        return res.toString();
    }


    //获取指定用户组的用户列表
    public String getUserlistFromGroup(String groupId) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("start", "0"); //默认值0，起始序号
        options.put("length", "50");  //返回数量，默认值100，最大值1000

        JSONObject res = client.getGroupUsers(groupId, options);
        return res.toString();
    }


    //获取指定用户组的指定用户的信息
    public String getUserinfoByGroupAndUserId(String groupId, String userId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.getUser(userId, groupId, options);
        return res.toString();
    }


    //特定组中人脸搜索
    public String searchFaceFromGroup(String photo, String groupIdList) throws Exception{
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL"); //图片质量控制
        //LOW->较低；NORMAL->一般；HIGH->较高；默认NONE
        options.put("liveness_control", "LOW"); //活体检测
        //LOW->较低；NORMAL->一般；HIGH->较高；默认NONE
//        options.put("user_id", "233451"); //当需要对特定用户进行比对时，指定user_id进行比对。即人脸认证功能。
        options.put("max_user_num", "3"); //查找后返回的用户数量。返回相似度最高的几个用户，默认为1，最多返回20个。

        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrc + photo);
        String image = new String(imageByte);

        // 人脸搜索
        JSONObject res = client.search(image, Const.BASE64, groupIdList, options);
        return res.toString();
    }


}
