package BaiduAI.test;

import BaiduAI.Common.Const;
import BaiduAI.service.IFaceService;
import BaiduAI.service.impl.FaceServiceImpl;
import BaiduAI.util.ImageToByte;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.ocr.AipOcr;
import com.mmall.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class FaceRecognition {

    private static AipFace client = new AipFace(Const.APP_ID_FACE, Const.API_KEY_FACE, Const.SECRET_KEY_FACE);

    private static Scanner in = new Scanner(System.in);

//    @Autowired
//    public IFaceService iFaceService1;

    public static void main(String[] args) throws Exception {

        int choose;

        System.out.println("welcome to hugoo face kku!");
        while(true) {
            System.out.println("******************************************");
            System.out.println("* 1.faceDetect          2.createGroup    *");
            System.out.println("* 3.deleteGroup         4.getGroupList   *");
            System.out.println("* 5.faceRegister        6.faceDelete     *");
            System.out.println("* 7.faceDeleteFromGroup                  *");
            System.out.println("* 8.getUserlistFromGroup                 *");
            System.out.println("* 9.getUserinfoByGroupAndUserId          *");
            System.out.println("* 10.searchFaceFromGroup                 *");
            System.out.println("******************************************");
            System.out.println("please choose:");
            choose = in.nextInt();
            String getchar = in.nextLine();
            switch (choose) {
                case 1:faceDetect();break;
                case 2:createGroup();break;
                case 3:deleteGroup();break;
                case 4:getGroupList();break;
                case 5:faceRegister();break;
                case 6:faceDelete();break;
                case 7:faceDeleteFromGroup();break;
                case 8:getUserlistFromGroup();break;
                case 9:getUserinfoByGroupAndUserId();break;
                case 10:searchFaceFromGroup();break;
            }

        }

    }

    private static void faceDetect() throws Exception {
        String photo;
        System.out.println("please input photo name:");
        photo = in.nextLine();
        System.out.println(photo);
        faceDetect_R(client, photo);
    }

    private static void createGroup() {
        String groupId;
        System.out.println("please input group name:");
        groupId = in.nextLine();
        createGroup_R(client, groupId);
    }

    private static void deleteGroup() {
        String groupId;
        System.out.println("please input group name:");
        groupId = in.nextLine();
        deleteDroup_R(client, groupId);
    }

    private static void getGroupList() {
        getGroupList_R(client);
    }

    private static void faceRegister() throws Exception {
        String photo;
        String groupId;
        String userId;
        String info;
        System.out.println("please input photo name:");
        photo = in.nextLine();
        System.out.println("please input groupId:");
        groupId = in.nextLine();
        System.out.println("please input userId:");
        userId = in.nextLine();
        System.out.println("please input user's Information:");
        info = in.nextLine();
        faceRegister_R(client, photo, groupId, userId, info);
    }

    private static void faceDelete() {
        String groupId;
        String userId;
        String faceToken;
        System.out.println("please input groupId:");
        groupId = in.nextLine();
        System.out.println("please input userId:");
        userId = in.nextLine();
        System.out.println("please input user's Information:");
        faceToken = in.nextLine();
        faceDelete_R(client, groupId, userId, faceToken);
    }

    private static void faceDeleteFromGroup() {
        String groupId;
        String userId;
        System.out.println("please input groupId:");
        groupId = in.nextLine();
        System.out.println("please input userId:");
        userId = in.nextLine();
        faceDeleteFromGroup_R(client, groupId, userId);
    }

    private static void getUserlistFromGroup() {
        String groupId;
        System.out.println("please input groupId:");
        groupId = in.nextLine();
        getUserlistFromGroup_R(client, groupId);
    }

    private static void getUserinfoByGroupAndUserId() {
        String groupId;
        String userId;
        System.out.println("please input groupId:");
        groupId = in.nextLine();
        System.out.println("please input userId:");
        userId = in.nextLine();
        getUserinfoByGroupAndUserId_R(client, groupId, userId);
    }

    private static void searchFaceFromGroup() throws Exception {
        String photo;
        String groupIdList;
        System.out.println("please input photo name:");
        photo = in.nextLine();
        System.out.println("please input groupIdList, use , to splt:");
        groupIdList = in.nextLine();
        searchFaceFromGroup_R(client, photo, groupIdList);
    }










    public static void faceDetect_R(AipFace client, String photo) throws Exception {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "4"); // 最多处理人脸的数目，默认为1
        options.put("face_field", "age,beauty"); //年龄，美貌
        //包括 age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype，逗号相隔
        options.put("face_type", "LIVE"); //表示生活照
        // IDCARD->身份证芯片照 WATERMARK->带水印证件照 CERT->证件照片. 默认LIVE


        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrc+photo);
        String image = new String(imageByte);

        JSONObject res = client.detect(image, Const.BASE64, options);
        System.out.println(res.toString(2));
//        return res;
    }


    //创建用户组
    public static void createGroup_R(AipFace client, String groupId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.groupAdd(groupId, options);
        System.out.println(res.toString(2));
    }


    //删除用户组
    public static void deleteDroup_R(AipFace client, String groupId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.groupDelete(groupId, options);
        System.out.println(res.toString(2));
    }


    //获取用户组列表
    public static void getGroupList_R(AipFace client) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("start", "0"); //默认值0，起始序号
        options.put("length", "50");  //返回数量，默认值100，最大值1000

        JSONObject res = client.getGroupList(options);
        System.out.println(res.toString(2));
    }


    //人脸注册
    public static void faceRegister_R(AipFace client, String photo, String groupId, String userId, String userInfo) throws Exception {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", userInfo); //用户信息
        options.put("quality_control", "NORMAL"); //表示图片质量控制 默认NONE->不控制；LOW->较低；NORMAL->一般；HIGH->较高
        options.put("liveness_control", "LOW");  //活体检测控制 默认NONE->不控制；LOW->较低；NORMAL->一般；HIGH->较高

        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrcForRegister+photo);
        String image = new String(imageByte);

        JSONObject res = client.addUser(image, Const.BASE64, groupId, userId, options);
        System.out.println(res.toString(2));
    }


    //将人脸从人脸库中删除
    public static void faceDelete_R(AipFace client, String groupId, String userId, String faceToken) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
        System.out.println(res.toString(2));
    }


    //将人脸从特定组中删除
    public static void faceDeleteFromGroup_R(AipFace client, String groupId, String userId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.deleteUser(groupId, userId, options);
        System.out.println(res.toString(2));
    }


    //获取指定用户组的用户列表
    public static void getUserlistFromGroup_R(AipFace client, String groupId) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("start", "0"); //默认值0，起始序号
        options.put("length", "50");  //返回数量，默认值100，最大值1000

        JSONObject res = client.getGroupUsers(groupId, options);
        System.out.println(res.toString(2));
    }


    //获取指定用户组的指定用户的信息
    public static void getUserinfoByGroupAndUserId_R(AipFace client, String groupId, String userId) {
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.getUser(userId, groupId, options);
        System.out.println(res.toString(2));
    }


    //特定组中人脸搜索
    public static void searchFaceFromGroup_R(AipFace client, String photo, String groupIdList) throws Exception{
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL"); //图片质量控制
        //LOW->较低；NORMAL->一般；HIGH->较高；默认NONE
        options.put("liveness_control", "LOW"); //活体检测
        //LOW->较低；NORMAL->一般；HIGH->较高；默认NONE
//        options.put("user_id", "233451"); //当需要对特定用户进行比对时，指定user_id进行比对。即人脸认证功能。
        options.put("max_user_num", "3"); //查找后返回的用户数量。返回相似度最高的几个用户，默认为1，最多返回20个。

        byte[] imageByte = ImageToByte.ImageToBase64Byte(Const.FaceImageSrcForTest + photo);
        String image = new String(imageByte);

        // 人脸搜索
        JSONObject res = client.search(image, Const.BASE64, groupIdList, options);
        System.out.println(res.toString(2));
    }
}
