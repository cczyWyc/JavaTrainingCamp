package com.cczyWyc.rpcfx_core.netty.common;

import lombok.Data;

/**
 * Data format of netty
 *
 * @author wangyc
 */
@Data
public class RpcProtocol {
    /** data length */
    private int length;
    /** data content */
    private byte[] content;
}
