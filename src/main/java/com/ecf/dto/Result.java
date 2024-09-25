package com.ecf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    public static Result ok(){
        return new Result(200,true, null, null, null);
    }
    public static Result ok(Object data){
        return new Result(200,true, null, data, null);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(200,true, null, data, total);
    }
    public static Result fail(String errorMsg){
        return new Result(500,false, errorMsg, null, null);
    }
}
