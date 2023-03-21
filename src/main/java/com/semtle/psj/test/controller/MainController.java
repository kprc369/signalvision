package com.semtle.psj.test.controller;

import com.semtle.psj.test.MainService;
import com.semtle.psj.test.Vo.ControllInfoVo;
import com.semtle.psj.test.Vo.MemberVo;
import com.semtle.psj.test.Vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    MainService mainService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /* 회원가입 요청 */
    @ResponseBody
    @RequestMapping(value = "/signUp")
    public String login(MemberVo member){ return mainService.signUp(member);}

    /* 로그인 요청 */
    @ResponseBody
    @RequestMapping(value = "/signIn")
    public String signIn(MemberVo member){return mainService.signIn(member);}

    /* 로그인 요청하는 IP주소 업데이트 */
    @ResponseBody
    @RequestMapping(value = "/changeIP")
    public String changeIP(MemberVo member){return mainService.changeIP(member);}

    /* 데이터 요청 */
    @ResponseBody
    @RequestMapping(value = "/getControlData")
    public String getControlData(ControllInfoVo controllInfo){ return mainService.getControlData(controllInfo);}

    /* 알람 데이터 INSERT */
    @ResponseBody
    @RequestMapping(value = "/insertData")
    public String insertData(){ return mainService.insertData(); }
    /* 알람 요청(모니터링) */
    @ResponseBody
    @RequestMapping(value = "/doAlram")
    public String doAlram(){ return mainService.doAlram();}


    /* 알람 동기화 */
    @RequestMapping(value = "/updateAlram")
    public String updateAlram(){return mainService.updateAlram();}


    ///////////////////////////////////////////////////////////////////////////

    /* 입고 요청 */
    @ResponseBody
    @RequestMapping(value = "/ipgo")
    public String ipgo(ProductVo product){
        return mainService.ipgo(product);
    }

    /* 출고 요청 */
    @ResponseBody
    @RequestMapping(value = "/chulgo")
    public String chulgo(ProductVo product){
        return mainService.chulgo(product);
    }
    /* 입고 리스트 요청 */
    @ResponseBody
    @RequestMapping(value = "/ipgoList")
    public String ipgoList(ProductVo product) { return mainService.ipgoList(product); }

    /* 출고 리스트 요청 */
    @ResponseBody
    @RequestMapping(value = "/chulgoList")
    public String chulgoList(ProductVo product) { return mainService.chulgoList(product);}

}
