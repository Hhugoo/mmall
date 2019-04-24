package com.mmall.controller.AITest;


import com.baidu.aip.face.AipFace;
import com.google.gson.JsonObject;
import com.mmall.common.Const;
import com.mmall.service.IFaceService;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baiduAI/face/")
public class FaceController {

    @Autowired
    private IFaceService iFaceService;

    @RequestMapping("face_detect.do")
    @ResponseBody
    public String faceDetect(String photo) throws Exception {
        return iFaceService.faceDetect(photo);
    }

    @RequestMapping("create_group.do")
    @ResponseBody
    public String createGroup(String groupId) throws Exception {
        return iFaceService.createGroup(groupId);
    }

    @RequestMapping("delete_group.do")
    @ResponseBody
    public String deleteGroup(String groupId) throws Exception {
        return iFaceService.deleteGroup(groupId);
    }

    @RequestMapping("get_groupList.do")
    @ResponseBody
    public String getGroupList() throws Exception {
        return iFaceService.getGroupList();
    }

    @RequestMapping("face_register.do")
    @ResponseBody
    public String faceRegister(String photo, String groupId, String userId, @RequestParam(value = "userInfo", defaultValue = "") String userInfo) throws Exception {
        return iFaceService.faceRegister(photo, groupId, userId, userInfo);
    }

    @RequestMapping("face_delete.do")
    @ResponseBody
    public String faceDelete(String groupId, String userId, String faceToken) throws Exception {
        return iFaceService.faceDelete(groupId, userId, faceToken);
    }

    @RequestMapping("face_delete_from_group.do")
    @ResponseBody
    public String faceDeleteFromGroup(String groupId, String userId) throws Exception {
        return iFaceService.faceDeleteFromGroup(groupId, userId);
    }

    @RequestMapping("get_userList_from_group.do")
    @ResponseBody
    public String getUserlistFromGroup(String groupId) throws Exception {
        return iFaceService.getUserlistFromGroup(groupId);
    }

    @RequestMapping("get_userInfo_byGroupAndUserId.do")
    @ResponseBody
    public String getUserinfoByGroupAndUserId(String groupId, String userId) {
        return iFaceService.getUserinfoByGroupAndUserId(groupId, userId);
    }

    @RequestMapping("search_face_from_group")
    @ResponseBody
    public String searchFaceFromGroup(String photo, String groupIdList) throws Exception {
        return iFaceService.searchFaceFromGroup(photo, groupIdList);
    }



}
