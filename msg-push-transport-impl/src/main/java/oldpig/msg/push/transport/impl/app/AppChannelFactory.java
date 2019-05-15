package oldpig.msg.push.transport.impl.app;

import oldpig.msg.push.transport.core.ChannelConnection;
import oldpig.msg.push.transport.core.ChannelConnectionConfig;
import oldpig.msg.push.transport.core.ChannelFactory;

public class AppChannelFactory implements ChannelFactory {

    @Override
    public void init() {

    }

    @Override
    public ChannelConnection getConnection(ChannelConnectionConfig config) {
        try {

            String callbackClass = config.getExtInfo().get("callbackClass");
            AppMsgCallback callback = (AppMsgCallback)Class.forName(callbackClass).newInstance();
            AppChannelConnection appChannelConnection = new AppChannelConnection(callback);
            return appChannelConnection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
