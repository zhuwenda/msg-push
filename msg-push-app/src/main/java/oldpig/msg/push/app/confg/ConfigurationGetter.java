package oldpig.msg.push.app.confg;

import java.util.List;
import java.util.Set;

public interface ConfigurationGetter {

    List<ChannelConfig> getGlobalChannelConfigs();

    List<EventConfig> getGlobalEventConfigs();

    String getUserIdInChannel(String userId,String channelId);

    /** 获取某个用户在某个事件上配置的通道 */
    Set<String> getUserChannelIdInEvent(String userId, String eventId);

}
