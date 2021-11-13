package com.zhining.nettyclient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ipfilter.RuleBasedIpFilter;

/**
 * 客户端ChannelPipeline的配置
 */
public class ClientInitializer extends ChannelInitializer<NioDatagramChannel> {

    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();

    private static final UDPClientHandler CLIENT_HANDLER = new UDPClientHandler();

    @Override
    public void initChannel(NioDatagramChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        RuleBasedIpFilter ruleBasedIpFilter =new RuleBasedIpFilter(new ClientIpFilter());
        pipeline.addFirst(ruleBasedIpFilter);
        // 添加行分割器
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        // 添加客户端处理器
        pipeline.addLast(CLIENT_HANDLER);
    }
}
