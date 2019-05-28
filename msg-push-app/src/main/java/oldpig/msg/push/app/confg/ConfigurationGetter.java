package oldpig.msg.push.app.confg;

import java.util.List;
import java.util.Set;

public interface ConfigurationGetter {

    /** 获取系统级的通道配置 */
    List<ChannelConfig> getGlobalChannelConfigs();

    /** 获取系统级的事件配置 */
    List<EventConfig> getGlobalEventConfigs();

    /** 通过用户标识、通道ID获取该用户在指定通道上的所需的标识 */
    String getUserIdInChannel(String userId,String channelId);

    /** 获取某个用户在某个事件上配置的通道 */
    Set<String> getUserChannelIdInEvent(String userId, String eventId);

}
