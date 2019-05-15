package com;

import oldpig.msg.push.app.MsgPushCenter;
import oldpig.msg.push.app.MsgPushCenterImpl;
import oldpig.msg.push.app.confg.ChannelConfig;
import oldpig.msg.push.app.confg.ConfigurationGetter;
import oldpig.msg.push.app.confg.EventConfig;
import org.junit.Test;

import java.util.*;

public class Test1 {

    @Test
    public void test1() throws Exception{
        ConfigurationGetter configurationGetter = new ConfigurationGetter() {
            @Override
            public List<ChannelConfig> getGlobalChannelConfigs() {
                ChannelConfig config = new ChannelConfig();
                config.setChannelId("mail");
                config.setChannelName("邮件");
                config.setDriverClass("oldpig.msg.push.transport.impl.mail.MailChannelFactory");
                config.setHost("smtp.exmail.qq.com");
                config.setPort("465");
                config.setUser("zwd@legion-tech.net");
                config.setPassword("Ab07550755");
                config.setIsOpen("Y");


                return Arrays.asList(config);
            }

            @Override
            public List<EventConfig> getGlobalEventConfigs() {
                EventConfig eventConfig = new EventConfig();
                eventConfig.setEventId("test");
                eventConfig.setEventName("测试");
                eventConfig.setStatus("Y");
                Set<String> mail = new HashSet<>();
                mail.add("mail");
                eventConfig.setSupportedChannelIds(mail);
                eventConfig.setMustChannelIds(mail);
                eventConfig.setDefaultChannelIds(mail);

                eventConfig.setDefaultTitleTpl("这是测试标题");
                eventConfig.setDefaultContentTpl("这是测试内容:${name}");
                return Arrays.asList(eventConfig);
            }

            @Override
            public String getUserIdInChannel(String userId, String channelId) {
                return "zwd@legion-tech.net";
            }

            @Override
            public Set<String> getUserChannelIdInEvent(String userId, String eventId) {
                return new HashSet<>();
            }
        };


        MsgPushCenter pushCenter = new MsgPushCenterImpl();
        pushCenter.setConfigurationGetter(configurationGetter);


        Map<String,Object> param = new HashMap<>();
        param.put("name","hello");
        pushCenter.push("1","test",param);
    }


}
