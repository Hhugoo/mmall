package com.mmall.controller.AITest;

import com.mmall.service.IFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baiduAI/face/")
public class CharacterController {

    @Autowired
    private IFaceService iFaceService;

    @RequestMapping("word_recognition.do")
    @ResponseBody
    public String wordRecognition(String photo) {
        return iFaceService.wordsRecognition(photo);
    }

}
