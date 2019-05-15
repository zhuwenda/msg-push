package oldpig.msg.push.transport.impl.mail;



import oldpig.msg.push.transport.core.ChannelConnection;
import oldpig.msg.push.transport.core.ChannelConnectionConfig;
import oldpig.msg.push.transport.core.Msg;
import oldpig.msg.push.transport.core.PushResult;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailChannelConnection implements ChannelConnection {

    private Session session;
    private ChannelConnectionConfig config;

    public MailChannelConnection(){

    }

    public MailChannelConnection(ChannelConnectionConfig config){
        this.config = config;
    }


    public MailChannelConnection(Session session, ChannelConnectionConfig config){
        this.session = session;
        this.config = config;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public PushResult push(Msg msgObj) {
        boolean isSuccess = false;
        try {

            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //指明邮件的发件人
            String sendFrom = (config.getExtInfo() != null && config.getExtInfo().get("sendFrom") != null) ? config.getExtInfo().get("sendFrom") : config.getUser();
            message.setFrom(new InternetAddress(sendFrom));
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(msgObj.getTo()));

            //邮件的标题
            message.setSubject(msgObj.getTitle());
            //邮件的文本内容
            message.setContent(msgObj.getContent(), "text/html;charset=UTF-8");
            //返回创建好的邮件对象



            Transport.send(message);
            isSuccess = true;

        } catch (Exception e) {
            e.printStackTrace();
            return PushResult.fail(e.getMessage());
        }
        return isSuccess ? PushResult.success() : PushResult.fail();
    }



    public boolean isActive() {
        return true;
    }
}
