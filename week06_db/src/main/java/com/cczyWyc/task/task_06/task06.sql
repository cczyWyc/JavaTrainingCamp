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
    `id` int(11) NOT NULL  AUTO_INCREMENT,
    `user_union_id` int(11) NOT NULL,
    `name` varchar(45) NOT NULL COMMENT '收货人姓名',
    `mobile` varchar(15) NOT NULL COMMENT '收货人手机号',
    `post_code` varchar(20) DEFAULT NULL COMMENT '邮编',
    `is_defaulr` tinyint(4) DEFAULT 1 COMMENT '0默认地址 1非默认地址',
    `province_code` varchar(20) NOT NULL DEFAULT NULL COMMENT '省编号',
    'city_code' varchar(20) NOT NULL DEFAULT NULL COMMENT '市编号',
    `area_code` varchar(20) NOT NULL DEFAULT NULL COMMENT '区编号',
    `street_code` varchar(20) NOT NULL DEFAULT NULL COMMENT '街道编号',
    `address` varchar(255) NOT NULL DEFAULT NULL COMMENT '详细地址',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (user_union_id) references t_user(union_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户收获地址表';

CREATE TABLE IF NOT EXISTS `t_order` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `status` int(1) NOT NULL,
    `goods_list` varchar(10024) NOT NULL,
    `price` DOUBLE NOT NULL,
    `create_time` int(11) NOT NULL,
    `user_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    foreign key (user_id) references tbl_user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- # 店铺表[店铺id、店铺名称、店铺备注]
CREATE TABLE IF NOT EXISTS `tbl_stores` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `info` varchar(10024),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- # 商品表[商品id、商品名称、商品信息、价格、商品状态、所属店铺id]
CREATE TABLE IF NOT EXISTS `tbl_goods` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `goods_info` varchar(10024),
    `goods_price` DOUBLE NOT NULL,
    `goods_status` int(1) NOT NULL,
    `store_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    foreign key (store_id) references tbl_stores(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;