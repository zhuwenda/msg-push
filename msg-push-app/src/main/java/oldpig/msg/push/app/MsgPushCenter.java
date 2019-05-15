package oldpig.msg.push.app;


import oldpig.msg.push.app.confg.ConfigurationGetter;

import java.util.Map;

public interface MsgPushCenter {

    void setConfigurationGetter(ConfigurationGetter configurationGetter);

    EventPushResult push(String userId, String eventId, Map<String,Object> context);

}
