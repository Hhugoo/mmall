//package BaiduAI.service;
//
//import com.baidu.aip.face.AipFace;
//import org.json.JSONObject;
//
//public interface IFaceService {
//
//    JSONObject faceDetect(AipFace client, String photo) throws Exception;
//
//    void createGroup(AipFace client, String groupId);
//
//    void deleteDroup(AipFace client, String groupId);
//
//    void getGroupList(AipFace client);
//
//    void faceRegister(AipFace client, String photo, String groupId, String userId, String userInfo) throws Exception;
//
//    void faceDelete(AipFace client, String groupId, String userId, String faceToken);
//
//    void faceDeleteFromGroup(AipFace client, String groupId, String userId);
//
//    void getUserlistFromGroup(AipFace client, String groupId);
//
//    void getUserinfoByGroupAndUserId(AipFace client, String groupId, String userId);
//
//    void searchFaceFromGroup(AipFace client, String photo, String groupIdList) throws Exception;
//}
