package com.cczyWyc.rpcfx_core.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * rpc custom decoder
 * bytes---->rpcProtocol
 *
 * @author wangyc
 */
@Slf4j
public class RpcDecoder extends ByteToMessageDecoder {
    /** data len */
    private int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("netty rpc decoder run......");
        if (in.readableBytes() >= 4) {
            if (length == 0) {
                length = in.readInt();
            }
            if (in.readableBytes() < length) {
                log.info("Readable data is less, wait......");
                return;
            }
            byte[] content = new byte[length];
            if (in.readableBytes() >= length) {
                in.readBytes(content);
                RpcProtocol protocol = new RpcProtocol();
                protocol.setLength(length);
                protocol.setContent(content);
                out.add(protocol);
            }
            length = 0;
        }
    }
}
