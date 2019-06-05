//package BaiduAI.service.impl;
//
//import BaiduAI.Common.Const;
//import BaiduAI.service.IFaceService;
//import BaiduAI.util.ImageToByte;
//import com.baidu.aip.face.AipFace;
//import org.json.JSONObject;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//
//@Service("iFaceService")
//public class FaceServiceImpl implements IFaceService{
//
//
//    //人脸检测
//    public JSONObject faceDetect(AipFace client, String photo) throws Exception {
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("max_face_num", "3"); // 最多处理人脸的数目，默认为1
//        options.put("face_field", "age,beauty"); //年龄，美貌
//        //包括 age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype，逗号相隔
//        options.put("face_type", "LIVE"); //表示生活照
//        // IDCARD->身份证芯片照 WATERMARK->带水印证件照 CERT->证件照片. 默认LIVE
//
//
//        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrc+photo);
//        String image = new String(imageByte);
//
//        JSONObject res = client.detect(image, Const.BASE64, options);
////        System.out.println(res.toString(2));
//        return res;
//    }
//
//
//    //创建用户组
//    public void createGroup(AipFace client, String groupId) {
//        HashMap<String, String> options = new HashMap<String, String>();
//
//        JSONObject res = client.groupAdd(groupId, options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //删除用户组
//    public void deleteDroup(AipFace client, String groupId) {
//        HashMap<String, String> options = new HashMap<String, String>();
//
//        JSONObject res = client.groupDelete(groupId, options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //获取用户组列表
//    public void getGroupList(AipFace client) {
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("start", "0"); //默认值0，起始序号
//        options.put("length", "50");  //返回数量，默认值100，最大值1000
//
//        JSONObject res = client.getGroupList(options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //人脸注册
//    public void faceRegister(AipFace client, String photo, String groupId, String userId, String userInfo) throws Exception {
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("user_info", userInfo); //用户信息
//        options.put("quality_control", "NORMAL"); //表示图片质量控制 默认NONE->不控制；LOW->较低；NORMAL->一般；HIGH->较高
//        options.put("liveness_control", "LOW");  //活体检测控制 默认NONE->不控制；LOW->较低；NORMAL->一般；HIGH->较高
//
//        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrc+photo);
//        String image = new String(imageByte);
//
//        JSONObject res = client.addUser(image, Const.BASE64, groupId, userId, options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //将人脸从人脸库中删除
//    public void faceDelete(AipFace client, String groupId, String userId, String faceToken) {
//        HashMap<String, String> options = new HashMap<String, String>();
//
//        JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //将人脸从特定组中删除
//    public void faceDeleteFromGroup(AipFace client, String groupId, String userId) {
//        HashMap<String, String> options = new HashMap<String, String>();
//
//        JSONObject res = client.deleteUser(groupId, userId, options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //获取指定用户组的用户列表
//    public void getUserlistFromGroup(AipFace client, String groupId) {
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("start", "0"); //默认值0，起始序号
//        options.put("length", "50");  //返回数量，默认值100，最大值1000
//
//        JSONObject res = client.getGroupUsers(groupId, options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //获取指定用户组的指定用户的信息
//    public void getUserinfoByGroupAndUserId(AipFace client, String groupId, String userId) {
//        HashMap<String, String> options = new HashMap<String, String>();
//
//        JSONObject res = client.getUser(userId, groupId, options);
//        System.out.println(res.toString(2));
//    }
//
//
//    //特定组中人脸搜索
//    public void searchFaceFromGroup(AipFace client, String photo, String groupIdList) throws Exception{
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("quality_control", "NORMAL"); //图片质量控制
//        //LOW->较低；NORMAL->一般；HIGH->较高；默认NONE
//        options.put("liveness_control", "LOW"); //活体检测
//        //LOW->较低；NORMAL->一般；HIGH->较高；默认NONE
////        options.put("user_id", "233451"); //当需要对特定用户进行比对时，指定user_id进行比对。即人脸认证功能。
//        options.put("max_user_num", "3"); //查找后返回的用户数量。返回相似度最高的几个用户，默认为1，最多返回20个。
//
//        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrc + photo);
//        String image = new String(imageByte);
//
//        // 人脸搜索
//        JSONObject res = client.search(image, Const.BASE64, groupIdList, options);
//        System.out.println(res.toString(2));
//    }
//
//
//}
