/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.zhining.nettyclient;

import android.util.Log;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;


public class UDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final String TAG = UDPClientHandler.class.getSimpleName();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // channel活跃
        super.channelActive(ctx);
        Log.d(TAG, "channelActive");
    }


    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        Log.d(TAG, "hostName = " + msg.sender().getHostName());
        Log.d(TAG, "hostString = " + msg.sender().getHostString());
        Log.d(TAG, "getAddress().getHostAddress = " + msg.sender().getAddress().getHostAddress());
        String response = msg.content().toString(CharsetUtil.UTF_8);
        Log.d(TAG, "客户端收到消息:{}" + msg + response);
      /*  if (response.startsWith("广播: ")) {
            Log.d(TAG, "收到广播: {}" + response.substring(4));
            ctx.close();
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
