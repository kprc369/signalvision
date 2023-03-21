package com.semtle.psj.test.Vo;

import com.semtle.psj.test.Enum.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResultVo {
    private ResultEnum result;
    private String msg;
    private Object data;
}