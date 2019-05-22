package oldpig.msg.push.app;


import oldpig.msg.push.app.confg.ConfigurationGetter;

import java.util.Map;

public interface MsgPushCenter {

    void setConfigurationGetter(ConfigurationGetter configurationGetter);

    EventPushResult push(String eventId, String userId,  Map<String,Object> context);

}
