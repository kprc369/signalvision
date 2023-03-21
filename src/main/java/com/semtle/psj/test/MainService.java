package com.semtle.psj.test;

import com.google.gson.Gson;
import com.semtle.psj.test.Vo.ControllInfoVo;
import com.semtle.psj.test.Vo.MemberVo;
import com.semtle.psj.test.Vo.ProductVo;
import com.semtle.psj.test.dao.MainDao;
import com.semtle.psj.test.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class MainService {
    @Autowired
    Gson gson;
    @Autowired
    MainDao mainDao;
    @Autowired
    Util util;

    //회원가입
    public String signUp(MemberVo member){
        Map<String, Object> result = new HashMap<>();
        log.info("signUp"+member.toString());
        String pw_encrypted = util.encryptSHA256(member.getPw());
        member.setPw(pw_encrypted);
        int rst = mainDao.signUp(member);
        if(rst <1){
            log.error("ipgo : Fail Insert Data");
            result.put(util.RESULT, util.FAIL);
            result.put(util.MSG, "Fail Insert Data");                 //접수 DB 입력 실패
            return gson.toJson(result);
        }
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");

        return gson.toJson(result);
    }
    //로그인
    public String signIn(MemberVo member){
        Map<String, Object> result = new HashMap<>();
        //log.info("signIn"+member.toString());
        //member.setPw(pw_encrypted);
        List<MemberVo> rst = mainDao.signIn(member);
        //log.info("리스트 :"+gson.toJson(rst));
        if(rst.size() <1){
            log.error("SignIn : Fail Login");
            result.put(util.RESULT, util.FAIL);
            result.put(util.MSG, "Fail Login");                 //로그인 실패

            return gson.toJson(result);
        } else{                                                 //해당 ID 있을시 비밀번호 일치 확인
            String pw_encrypted = util.encryptSHA256(member.getPw());
            if(pw_encrypted.equals (rst.get(0).getPw())){
                if(rst.get(0).getM_ip() != "" && rst.get(0).getM_ip() != null){
                    rst.get(0).setPw(null);
                    int changeRst = mainDao.changeIP(member);
                    if(changeRst < 1){
                        log.error("SignIn : Fail Login");
                        result.put(util.RESULT, util.FAIL);
                        result.put(util.MSG, "Fail Login");                 //IP 변경 실패
                    }
                } else{
                    log.info("else진입3");
                }
            } else{
                log.info("else진입4");
            }
        }
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");
        result.put("data",gson.toJson(rst.get(0)));

        return gson.toJson(result);
    }

    //로그인 시도하는 IP주소 변경
    public String changeIP(MemberVo member){
        Map<String, Object> result = new HashMap<>();
        int rst = mainDao.changeIP(member);
        if(rst <1){
            log.error("ChangeIP : Fail Change IP");
            result.put(util.RESULT, util.FAIL);
            result.put(util.MSG, "Fail");                 //로그인 실패

            return gson.toJson(result);
        }
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");
        return gson.toJson(result);
    }

    public String insertData(){
        Map<String, Object> result = new HashMap<>();
        int rst = mainDao.insertData();
        if(rst <1){
            log.error("INSERT DATA : Fail INSERT DATA");
            result.put(util.RESULT, util.FAIL);
            result.put(util.MSG, "Fail");                 //로그인 실패

            return gson.toJson(result);
        }
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");
        return gson.toJson(result);
    }

    //데이터 가져오기
    public String getControlData(ControllInfoVo controllInfo){
        Map<String,Object> result = new HashMap<>();
        log.info("dataList:"+gson.toJson(controllInfo));
        List<ControllInfoVo> dataList = mainDao.getControlData();
        log.info(gson.toJson(dataList));
        result.put("dataList",dataList);
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");
        log.info(gson.toJson(result));
        return gson.toJson(result);
    }
    //알람 모니터링
    public String doAlram(){
        int rst = mainDao.doAlram();
        if(rst >0){
            return "Y";
        } else{
            return "N";
        }
    }
    //알람 울린 후 동기화
    public String updateAlram(){
        int rst = mainDao.updateAlram();
        return "";
    }

    public String ipgo(ProductVo product){
        Map<String, Object> result = new HashMap<>();
        log.info("ipgo:"+gson.toJson(product));
        int rst = mainDao.ipgo(product);
        if(rst <1){
            log.error("ipgo : Fail Insert Data");
            result.put(util.RESULT, util.FAIL);
            result.put(util.MSG, "Fail Insert Data");                 //접수 DB 입력 실패
            return gson.toJson(result);
        }
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");

        return gson.toJson(result);
    }

    public String chulgo(ProductVo product){
        Map<String, Object> result = new HashMap<>();
        log.info("chulgo:"+gson.toJson(product));
        int rst = mainDao.chulgo(product);
        log.info("rst:"+rst);
        if(rst <1){
            log.error("chuglo : Fail Update Data");
            result.put(util.RESULT, util.FAIL);
            result.put(util.MSG, "Fail Update Data");                 //접수 DB 입력 실패
            return gson.toJson(result);
        }
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");

        return gson.toJson(result);
    }

    public String ipgoList(ProductVo product){
        Map<String,Object> result = new HashMap<>();
        log.info("ipgoList:"+gson.toJson(product));
        List<ProductVo> productList = mainDao.getIpgoList(product.getCompanyNo());
        result.put("productList",productList);
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");
        return gson.toJson(result);
    }

    public String chulgoList(ProductVo product){
        Map<String,Object> result = new HashMap<>();
        log.info("chulgoList:"+gson.toJson(product));
        List<ProductVo> productList = mainDao.getChulgoList(product.getCompanyNo());
        result.put("productList",productList);
        result.put(util.RESULT, util.SUCCESS);
        result.put(util.MSG, "");
        return gson.toJson(result);
    }




}
