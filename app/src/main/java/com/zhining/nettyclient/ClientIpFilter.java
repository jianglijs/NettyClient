package com.zhining.nettyclient;

import java.net.InetSocketAddress;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

/**
 * @author：Jiangli
 * @date：2021/11/13 21:22
 */
public class ClientIpFilter implements IpFilterRule {
    @Override
    public boolean matches(InetSocketAddress remoteAddress) {
       // return remoteAddress.getAddress().getHostAddress().contains("192.168.3.27");
        return remoteAddress.getHostString().contains("192.168.3.27");
    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.ACCEPT;
    }
}
