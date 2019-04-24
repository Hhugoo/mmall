package com.mmall.service;

import com.baidu.aip.face.AipFace;
import com.google.gson.JsonObject;
import org.json.JSONObject;

public interface IFaceService {

    String wordsRecognition(String photo);

    String faceDetect(String photo) throws Exception;

    String createGroup(String groupId);

    String deleteGroup(String groupId);

    String getGroupList();

    String faceRegister(String photo, String groupId, String userId, String userInfo) throws Exception;

    String faceDelete(String groupId, String userId, String faceToken);

    String faceDeleteFromGroup(String groupId, String userId);

    String getUserlistFromGroup(String groupId);

    String getUserinfoByGroupAndUserId(String groupId, String userId);

    String searchFaceFromGroup(String photo, String groupIdList) throws Exception;

}
