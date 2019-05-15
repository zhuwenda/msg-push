package oldpig.msg.push.transport.core;


public interface ChannelFactory {

    public void init();

    public ChannelConnection getConnection(ChannelConnectionConfig config);

}
