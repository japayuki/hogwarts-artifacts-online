package dev.gtmedia.hogwartsartifactonline.system;

public class ResultResponse {
    private boolean flag;
    private Integer code;
    private String message;
    private Object payload;

    public ResultResponse(boolean flag, Integer code, String message, Object payload) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.payload = payload;
    }

    public ResultResponse(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public ResultResponse() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
