package com.semtle.psj.test.dao;

import com.semtle.psj.test.Vo.ControllInfoVo;
import com.semtle.psj.test.Vo.MemberVo;
import com.semtle.psj.test.Vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class MainDao extends BaseDao{
    //회원가입
    public int signUp(MemberVo member){
        String query = "INSERT INTO member_admin(id,pw) VALUES(?,?)";
        return jdbcTemplate.update(query,member.getId(),member.getPw());
    }
    //로그인
    public List<MemberVo> signIn(MemberVo member){
        String query = "SELECT * FROM MEMBER_ADMIN WHERE id=?";
        return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(MemberVo.class),member.getId());
    }
    //IP 변경
    public int changeIP(MemberVo member){
        String query = "UPDATE MEMBER_ADMIN SET M_IP=? WHERE id=?";
        return jdbcTemplate.update(query,member.getM_ip(),member.getId());
    }

    public int insertData(){
        String query = "INSERT INTO CONTROL_INFO(INFO,IS_SOUND) VALUES(?,?)";
        return jdbcTemplate.update(query,0,"");
    }

    //데이터 받기
    public List<ControllInfoVo> getControlData(){
        String query = "SELECT * FROM CONTROL_INFO";
        return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(ControllInfoVo.class));
    }

    //알람
    public int doAlram(){
        String query = "SELECT COUNT(*) FROM CONTROL_INFO WHERE IS_SOUND !='Y'";
        return jdbcTemplate.queryForObject(query,Integer.class);
    }


    //알람 is_sound 동기화
    public int updateAlram(){
        String query = "UPDATE CONTROL_INFO SET IS_SOUND = 'Y' WHERE IS_SOUND != 'Y'";
        return jdbcTemplate.update(query);
    }





















    //입고
    public int ipgo(ProductVo product){
        String query = "INSERT INTO PRODUCT(companyno,pbno,status) VALUES(?,?,?)";
        return jdbcTemplate.update(query,product.getCompanyNo(),product.getBarcode(),product.getStatus());
    }
    //출고
    public int chulgo(ProductVo product){
        String query = "UPDATE PRODUCT SET STATUS = ? WHERE COMPANYNO = ? AND PBNO= ? AND STATUS = 0";
        return jdbcTemplate.update(query,product.getStatus(),product.getCompanyNo(),product.getBarcode());
    }
    //입고 리스트
    public List<ProductVo> getIpgoList(String compayNo){
        String query = "SELECT PBNO AS BARCODE,STATUS,COMPANYNO FROM PRODUCT WHERE COMPANYNO = ? AND STATUS = 0";
        return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(ProductVo.class),compayNo);
    }
    //출고 리스트
    public List<ProductVo> getChulgoList(String compayNo){
        String query = "SELECT PBNO AS BARCODE,STATUS,COMPANYNO FROM PRODUCT WHERE COMPANYNO = ? AND STATUS = 9";
        return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(ProductVo.class),compayNo);
    }
}
