package com.cczyWyc.rpcfx.rpcfx_core.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * rpc custom encoder
 * rpcProtocol---->bytes
 *
 * @author wangyc
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol msg, ByteBuf out) throws Exception {
        System.out.println("Netty rpc encoder run......");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
