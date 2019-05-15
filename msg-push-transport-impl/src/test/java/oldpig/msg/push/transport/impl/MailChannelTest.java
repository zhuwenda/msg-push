package oldpig.msg.push.transport.impl;


import io.github.biezhi.ome.OhMyEmail;
import oldpig.msg.push.transport.core.*;
import oldpig.msg.push.transport.impl.mail.MailChannelFactory;
import oldpig.msg.push.transport.core.*;
import org.junit.Test;

public class MailChannelTest {

    @Test
    public void test1() throws Exception{
        ChannelFactory channelFactory = new MailChannelFactory();

        //配置

        ChannelConnectionConfig config = new ChannelConnectionConfig();
        config.setHost("smtp.exmail.qq.com");
        config.setPort("465");
        config.setUser("zwd@legion-tech.net");
        config.setPassword("Ab07550755");

        //建立连接
        ChannelConnection conn = channelFactory.getConnection(config);

        //创建消息对象
        Msg msg = new Msg();
        msg.setTo("zwd@legion-tech.net");
        msg.setTitle("测试邮件");
        msg.setContent("你好，测试");


        //发送消息
        PushResult result  = conn.push(msg);


        System.out.println(result);

    }

    @Test
    public void test2() throws Exception{
        OhMyEmail.config(OhMyEmail.SMTP_ENT_QQ(false),"zwd@legion-tech.net", "Ab07550755");
        OhMyEmail.subject("这是一封测试TEXT邮件")
                .from("小姐姐的邮箱")
                .to("zwd@legion-tech.net")
                .text("信件内容")
                .send();
    }
}
