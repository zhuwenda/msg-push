package oldpig.msg.push.transport.core;

import java.util.Map;

public class PushResult {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";

    private String status;
    private String statusMsg;
    private Map<String,Object> extInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    public static PushResult success(){
        PushResult pushResult = new PushResult();
        pushResult.setStatus(PushResult.STATUS_SUCCESS);
        return pushResult;
    }

    public static PushResult fail(){
        PushResult pushResult = new PushResult();
        pushResult.setStatus(PushResult.STATUS_FAIL);
        return pushResult;
    }

    public static PushResult fail(String msg){
        PushResult pushResult = new PushResult();
        pushResult.setStatus(PushResult.STATUS_FAIL);
        pushResult.setStatusMsg(msg);
        return pushResult;
    }
}
