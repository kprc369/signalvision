package com.semtle.psj.test.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberVo {
    private String id;              //아이디
    private String pw;              //비밀번호
    private String memberNo;        //회원번호
    private String m_ip;            //IP 주소
}
