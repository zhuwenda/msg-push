package oldpig.msg.push.transport.core;



public interface ChannelConnection {


    public PushResult push(Msg msg);

    public boolean isActive();

}
