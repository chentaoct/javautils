package com.ju.model.base;

import com.wt.monitor.model.common.PrintableModel;

import java.io.Serializable;

/**
 * Created by tao on 2018/5/16.
 */
public class BaseRespDTO extends PrintableModel implements Serializable {

    private static final long serialVersionUID = 9004130712513157286L;
    private String errCode;
    private String errTitle;
    // 英文错误描述
    private String errMsg;
    // 中文错误描述
    private String errMsgCn;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrTitle() {
        return errTitle;
    }

    public void setErrTitle(String errTitle) {
        this.errTitle = errTitle;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsgCn() {
        return errMsgCn;
    }


}
