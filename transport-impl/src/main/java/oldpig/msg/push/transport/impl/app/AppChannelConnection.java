package oldpig.msg.push.transport.impl.app;

import oldpig.msg.push.transport.core.ChannelConnection;
import oldpig.msg.push.transport.core.Msg;
import oldpig.msg.push.transport.core.PushResult;

public class AppChannelConnection implements ChannelConnection {

    private AppMsgCallback appMsgCallback;

    public AppChannelConnection(AppMsgCallback appMsgCallback){
        this.appMsgCallback = appMsgCallback;
    }

    @Override
    public PushResult push(Msg msg) {
        return appMsgCallback.callback(msg);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
