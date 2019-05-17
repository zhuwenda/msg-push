package oldpig.msg.push.transport.impl.mail;



import oldpig.msg.push.transport.core.ChannelConnection;
import oldpig.msg.push.transport.core.ChannelConnectionConfig;
import oldpig.msg.push.transport.core.ChannelFactory;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Map;
import java.util.Properties;

public class MailChannelFactory implements ChannelFactory {


    public void init() {

    }

    public ChannelConnection getConnection(ChannelConnectionConfig config) {


        Properties prop = new Properties();

        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.ssl.enable", "true");
        prop.setProperty("mail.smtp.timeout", "15000");
        prop.setProperty("mail.debug", "false");


        prop.setProperty("mail.host", config.getHost());
        prop.setProperty("mail.smtp.port",config.getPort());


        if(config.getExtInfo() != null){
            Map<String,String> ext = config.getExtInfo();
            if(ext.get("useSSL") != null ){
                prop.setProperty("mail.smtp.ssl.enable",ext.get("useSSL"));
            }
        }



        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getUser(), config.getPassword());
            }
        });
        MailChannelConnection mailChannelConnection = new MailChannelConnection(session,config);

        return mailChannelConnection;


    }
}
