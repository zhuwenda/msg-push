package oldpig.msg.push.app;


import oldpig.msg.push.app.confg.ConfigurationGetter;

import java.util.Map;

public interface MsgPushCenter {

    void setConfigurationGetter(ConfigurationGetter configurationGetter);

    /**
     * 给指定事件的指定用户发送消息，
     * @param eventId 事件ID
     * @param userId 用户ID
     * @param context 上下文对象，给事件消息配置中的模板使用，进行变量动态取值
     * @return
     */
    EventPushResult push(String userId, String eventId,  Map<String,Object> context);

}
