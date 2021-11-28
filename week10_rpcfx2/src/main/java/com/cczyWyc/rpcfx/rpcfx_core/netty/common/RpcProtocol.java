package com.cczyWyc.rpcfx.rpcfx_core.netty.common;

import java.util.Arrays;

/**
 * Data format of netty
 *
 * @author wangyc
 */
public class RpcProtocol {
    /** data length */
    private int length;
    /** data content */
    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RpcProtocol{" +
                "length=" + length +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
