package com.cczyWyc.rpcfx_core.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * rpc custom encoder
 * rpcProtocol---->bytes
 *
 * @author wangyc
 */
@Slf4j
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol msg, ByteBuf out) throws Exception {
        log.info("Netty rpc encoder run......");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
