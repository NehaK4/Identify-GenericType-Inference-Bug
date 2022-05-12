package com.javac.plugin;

public class GenericArgumentException extends Exception{
    private String errorCode;
    private String msg;
    private String causse;
    private String desc;

    public GenericArgumentException( String msg, String cause1, String desc) {

        this.errorCode = errorCode;
        this.msg = msg;
        this.causse = cause1;
        this.desc = desc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getCausse() {
        return causse;
    }

    public void setCausse(String causse) {
        this.causse = causse;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "GenericArgumentException{"  +
                " msg='" + msg + '\'' +
                ", causse='" + causse + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
