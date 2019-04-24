package com.mmall.util;

import org.apache.commons.net.util.Base64;

import java.io.File;
import java.io.FileInputStream;

public class ImageToByte {

    public static byte[] ImageToBase64Byte(String imageSrc) throws Exception{
        FileInputStream fin = new FileInputStream(new File(imageSrc));
        byte[] bytes = new byte[fin.available()];

        fin.read(bytes);
        fin.close();
        byte[] answer = Base64.encodeBase64(bytes);
        return answer;

    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf("aba"));
    }

}
