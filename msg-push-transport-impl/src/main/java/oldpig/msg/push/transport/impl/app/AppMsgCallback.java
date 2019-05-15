package oldpig.msg.push.transport.impl.app;

import oldpig.msg.push.transport.core.Msg;
import oldpig.msg.push.transport.core.PushResult;

public interface AppMsgCallback {

    PushResult callback(Msg msg);

}
