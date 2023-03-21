package com.semtle.psj.test.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class Util {
    //API return 변수명
    public static final String RESULT = "result";
    public static final String MSG = "msg";

    //API RESULT 값
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";

    public String encryptSHA256(String str) {																			//str -> SHA256(str)
        String sha="";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<byteData.length;i++) {
                sb.append(Integer.toString((byteData[i]&0xff)+0x100, 16).substring(1));
            }
            sha = sb.toString();
        } catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
            log.info("----- Encrypt Error - NoSuchAlgorithmException");
            sha = null;
        }
        return sha;
    }
}
