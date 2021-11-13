package com.zhining.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SocketUtils;

/**
 * @author：Jiangli
 * @date：2021/11/13 20:05
 */
public class UDPClient {
    private static final String TAG = UDPClient.class.getSimpleName();
    static final int PORT = 8000;

    public static void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ClientInitializer());
                    //.handler(new UDPClientHandler());

            Channel ch = b.bind(PORT).sync().channel();
        /*    ChannelPipeline p = ch.pipeline();
            RuleBasedIpFilter ruleBasedIpFilter =new RuleBasedIpFilter(new ClientIpFilter());
            p.addFirst(ruleBasedIpFilter);*/

            // 将消息广播给UDP服务器
            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("开始广播"+"我来自Android", CharsetUtil.UTF_8),
                    SocketUtils.socketAddress("255.255.255.255", PORT))).sync();

            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
