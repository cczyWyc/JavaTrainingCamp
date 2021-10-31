-- # 创建数据库geektime_ec
CREATE DATABASE IF NOT EXISTS `geektime_ec`;
use `geektime_ec`;

-- # 用户表[用户id、用户昵称、用户密码、手机号、邮箱、身份证号]
CREATE TABLE IF NOT EXISTS `tbl_user` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `username` varchar(16) NOT NULL,
    `password` varchar(16) NOT NULL,
    `phoneNumber` varchar(15) NOT NULL,
    `mail` varchar(30) NOT NULL,
    `id_card` varchar(16) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- # 订单表[订单id、订单状态、订单商品列表、订单价格、订单生成时间、订单所属用户id]
CREATE TABLE IF NOT EXISTS `tbl_order` (
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