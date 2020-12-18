package com.example.skilltrain.bean;

public class TokenBean {

    /**
     * msg : 操作成功
     * code : 200
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImY1YTg5OTNhLWQ2MzItNDQ0MC1iMTRmLWUxMGY5NzRlODNiMiJ9.fnKgNtGNUnEszZTYusV-g0WemUgO7cg7Z9tpNObWXscQX417ra4aT4nUz2Cr4ub0uCMWJXHEqnVScst49PVB0Q
     */

    private String msg;
    private int code;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
