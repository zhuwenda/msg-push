package oldpig.msg.push.app;

import oldpig.msg.push.app.confg.ChannelConfig;
import oldpig.msg.push.app.confg.ConfigurationGetter;
import oldpig.msg.push.app.confg.EventConfig;
import oldpig.msg.push.transport.core.ChannelConnection;
import oldpig.msg.push.transport.core.ChannelConnectionConfig;
import oldpig.msg.push.transport.core.ChannelFactory;
import oldpig.msg.push.transport.core.Msg;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MsgPushCenterImpl implements MsgPushCenter {

    private ConfigurationGetter configurationGetter;

    public void setConfigurationGetter(ConfigurationGetter configurationGetter) {
        this.configurationGetter = configurationGetter;
    }

    public EventPushResult push(String eventId, String userId, Map<String,Object> tplVars) {

        //获取用户在某个事件上配置的通道
        Set<String> userChannelIds = configurationGetter.getUserChannelIdInEvent(userId,eventId);

        //获取全局事件配置
        List<EventConfig> eventConfigs = configurationGetter.getGlobalEventConfigs();
        Optional<EventConfig> optional = eventConfigs.stream().filter(e->e.getEventId().equals(eventId)).findAny();
        if(!optional.isPresent()){
            throw new IllegalStateException("找不到事件配置信息，eventId："+eventId);
        }

        EventConfig eventConfig = optional.get();;

        //---- 基础校验 -----
        if(eventConfig == null){
            System.out.println("找不到此事件配置");
            return null;
        }

        if(!"Y".equals(eventConfig.getStatus())){
            System.out.println("事件通知没开启");
            return null;
        }

        if(eventConfig.getSupportedChannelIds() == null || eventConfig.getSupportedChannelIds().isEmpty()){
            System.out.println("事件没有配置支持的通道");
            return null;
        }
        //----

        //---- 有效通道计算 -----
        Set<String> okChannels = new HashSet<>();
        if(eventConfig.getMustChannelIds() != null){//必选通道
            okChannels.addAll(eventConfig.getMustChannelIds());
        }

        if(userChannelIds != null && !userChannelIds.isEmpty()){//用户配置了自定义事件通道
            okChannels.addAll(userChannelIds);
        }else if(eventConfig.getDefaultChannelIds() != null){//事件配置了默认通道
            okChannels.addAll(eventConfig.getDefaultChannelIds());
        }

        //通道必须是被支持的通道
        Set<String> toBeSendChannels = okChannels.stream().filter(e->eventConfig.getSupportedChannelIds().contains(e)).collect(Collectors.toSet());
        //----

        if(toBeSendChannels.isEmpty()){
            System.out.println("最终无有效的通道可以发送");
            return null;
        }

        List<ChannelConfig> channelConfigs = configurationGetter.getGlobalChannelConfigs();

        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        }catch (IOException e){
            e.printStackTrace();
        }
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        final Map<String,Object> globalVars = new HashMap<>();
        globalVars.put("_date",new Date());
        gt.setSharedVars(globalVars);


        toBeSendChannels.parallelStream().forEach(e->{
            try {
                ChannelConfig c = channelConfigs.stream().filter(item->item.getChannelId().equals(e)).findAny().get();
                ChannelFactory channelFactory = (ChannelFactory)Class.forName(c.getDriverClass()).newInstance();
                ChannelConnectionConfig connectionConfig = new ChannelConnectionConfig();
                connectionConfig.setHost(c.getHost());
                connectionConfig.setPort(c.getPort());
                connectionConfig.setUser(c.getUser());
                connectionConfig.setPassword(c.getPassword());
                connectionConfig.setExtInfo(c.getExtInfo());
                ChannelConnection channelConnection = channelFactory.getConnection(connectionConfig);


                //TODO null
                Template t = gt.getTemplate(eventConfig.getDefaultTitleTpl());
                t.binding(tplVars);
                String title = t.render();
                System.out.println(title);

                Template t2 = gt.getTemplate(eventConfig.getDefaultContentTpl());
                t2.binding(tplVars);
                String content = t2.render();
                System.out.println(content);



                Msg msg = new Msg();
                msg.setTitle(title);
                msg.setContent(content);
                Map<String,Object> header = new HashMap<>();
                header.put("meta_event_id",eventConfig.getEventId());
                header.put("meta_event_name",eventConfig.getEventName());
                msg.setHeader(header);

                String to = configurationGetter.getUserIdInChannel(userId,c.getChannelId());
                msg.setTo(to);

                channelConnection.push(msg);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        System.out.println("成功啦");
        return new EventPushResult();
    }
}
