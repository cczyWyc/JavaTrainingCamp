-- # 创建数据库geektime_ec
CREATE DATABASE IF NOT EXISTS `geektime_ec`;
use `geektime_ec`;

CREATE TABLE IF NOT EXISTS `t_user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `union_id` varchar(64) NOT NULL COMMENT '用户唯一id，随机生成',
    `account` varchar(45) NOT NULL COMMENT '注册的账号',
    `password` varchar(45) NOT NULL COMMENT '密码',
    `mobile` varchar(15) NOT NULL COMMENT '手机号',
    `mail` varchar(64) NOT NULL COMMENT '邮箱',
    `user_name` varchar(45) NOT NULL COMMENT '用户名（昵称）',
    `user_phone` varchar(255) COMMENT '头像',
    `gender` tinyint(4) NOT NULL DEFAULT 2 COMMENT '性别0男1女2未知',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `t_user_address` (
    `id` int(11) NOT NULL  AUTO_INCREMENT COMMENT '自增主键',
    `user_id` int(11) NOT NULL COMMENT '用户id',
    `name` varchar(45) NOT NULL COMMENT '收货人姓名',
    `mobile` varchar(15) NOT NULL COMMENT '收货人手机号',
    `post_code` varchar(20) DEFAULT NULL COMMENT '邮编',
    `is_default` tinyint(4) DEFAULT 1 COMMENT '0默认地址 1非默认地址',
    `province_code` varchar(20) NOT NULL COMMENT '省编号',
    `city_code` varchar(20) NOT NULL COMMENT '市编号',
    `area_code` varchar(20) NOT NULL COMMENT '区编号',
    `street_code` varchar(20) NOT NULL COMMENT '街道编号',
    `address` varchar(255) NOT NULL COMMENT '详细地址',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户收获地址表';

CREATE TABLE IF NOT EXISTS `t_order` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `order_no` varchar(64) NOT NULL COMMENT '订单编号',
    `user_id` int(11) NOT NULL COMMENT '用户id',
    `status` tinyint(4) DEFAULT 1 NOT NULL COMMENT '订单状态：1.待付款，2.已付款，3.待发货，4已发货，5.已收货，6.订单已完成',
    `logistics_no` varchar(64) NOT NULL COMMENT '物流单号',
    `total_no` int(11) NOT NULL COMMENT '商品总数量',
    `amount_pay` decimal(10, 2) NOT NULL COMMENT '应付金额',
    `amount_real_pay` decimal(10, 2) NOT NULL COMMENT '实付金额',
    `freight_amount` decimal(10, 2) NULL COMMENT '运费',
    `visit_reject_reason` varchar(255) NOT NULL COMMENT '支付平台流水号',
    `deleted` tinyint(4) DEFAULT 0 COMMENT '0未删除，1已删除',
    `pay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '订单表';

CREATE TABLE IF NOT EXISTS `t_order_goods` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `order` int(11) NOT NULL COMMENT '订单id',
    `user_id` int(11) NOT NULL COMMENT '用户id',
    `goods_id` int(11) NOT NULL COMMENT '商品id',
    `goods_name` varchar(45) NOT NULL COMMENT '商品名称',
    `goods_no` int(11) NOT NULL DEFAULT 1 COMMENT '商品数量',
    `goods_price` decimal(10, 2) NOT NULL COMMENT '商品单价',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '订单商品表';

CREATE TABLE IF NOT EXISTS `t_goods` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `goods_name` varchar(45) NOT NULL COMMENT '商品名称',
    `catalog_id` bigint(20) NOT NULL COMMENT '商品类目id',
    `goods_type` tinyint(4) NOT NULL COMMENT '商品类别',
    `price` decimal(10, 2) NOT NULL COMMENT '在售价',
    `supplier` varchar(64) NOT NULL COMMENT '供应商',
    `store_house` tinyint(4) NOT NULL COMMENT '所属仓库',
    `place` tinyint(4) NOT NULL COMMENT '生产地',
    `stock` int(11) NOT NULL COMMENT '库存',
    `goods_date` timestamp NOT NULL COMMENT '生产日期',
    `Shelf_life` tinyint(4) NOT NULL COMMENT '保质期',
    `status` tinyint(4) NOT NULL COMMENT '0:在售，1:促销，2:下架',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '商品表';

CREATE TABLE IF NOT EXISTS `t_catalog` (
    `id` bigint(20) NOT NULL COMMENT '自增主键',
    `item_name` varchar(45) NOT NULL COMMENT '商品类目名称',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '商品类目表';