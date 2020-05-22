package com.hchc.testservice.pack;

import com.hchc.testservice.util.DatetimeUtil;
import lombok.Data;

import java.util.Date;

@Data
public class Output {
    private String code;
    private String message;
    private String respTime = DatetimeUtil.format(new Date());
    private Object data;

    public Output() {

    }

    public Output(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Output ok(){
        return ok(null);
    }

    public static Output ok(Object data) {
        return new Output("0", "success", data);
    }

    public static Output fail(String message) {
        return new Output("999", message, null);
    }

}
