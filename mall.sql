
DROP TABLE IF EXISTS `mall_category`;
CREATE TABLE `mall_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `parent_id` int(11) DEFAULT '0' COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT '1' COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `mall_category` (`id`, `parent_id`, `name`, `status`, `sort_order`, `create_time`, `update_time`)
VALUES
# 	(100001,0,'脏腑调理',1,1,'2000-03-25 16:46:00','2000-03-25 16:46:00'),
# 	(100002,0,'疾病调理',1,1,'2000-03-25 16:46:21','2000-03-25 16:46:21'),
# 	(100003,0,'功能性调理',1,1,'2000-03-25 16:49:53','2000-03-25 16:49:53'),
# 	(100004,0,'食品生鲜',1,1,'2000-03-25 16:50:19','2000-03-25 16:50:19'),
# 	(100005,0,'酒水饮料',1,1,'2000-03-25 16:50:29','2000-03-25 16:50:29'),
	(100006,0,'补心',1,1,'2000-03-25 16:52:15','2000-03-25 16:52:15'),
	(100007,0,'养肝',1,1,'2000-03-25 16:52:26','2000-03-25 16:52:26'),
	(100008,0,'补脾',1,1,'2000-03-25 16:52:39','2000-03-25 16:52:39'),
	(100009,0,'养肺',1,1,'2000-03-25 16:52:45','2000-03-25 16:52:45'),
	(100010,0,'补肾',1,1,'2000-03-25 16:52:54','2000-03-25 16:52:54'),
	(100011,0,'补气',1,1,'2000-03-25 16:53:18','2000-03-25 16:53:18'),
	(100012,0,'补血',1,1,'2000-03-25 16:53:27','2000-03-25 16:53:27'),
	(100013,0,'气血双补',1,1,'2000-03-25 16:53:35','2000-03-25 16:53:35'),
	(100014,0,'哮喘',1,1,'2000-03-25 16:53:56','2000-03-25 16:53:56'),
	(100015,0,'感冒',1,1,'2000-03-25 16:54:07','2000-03-25 16:54:07'),
	(100016,0,'腹泻',1,1,'2000-03-25 16:54:44','2000-03-25 16:54:44'),
	(100017,0,'癫痫',1,1,'2000-03-25 16:54:51','2000-03-25 16:54:51'),
	(100018,0,'水肿',1,1,'2000-03-25 16:55:02','2000-03-25 16:55:02'),
	(100019,0,'便秘',1,1,'2000-03-25 16:55:09','2000-03-25 16:55:09'),
	(100020,0,'失眠',1,1,'2000-03-25 16:55:18','2000-03-25 16:55:18'),
	(100021,0,'健忘',1,1,'2000-03-25 16:55:30','2000-03-25 16:55:30'),
	(100022,0,'利尿',1,1,'2000-03-25 16:55:37','2000-03-25 16:55:37'),
	(100023,0,'活血化瘀',1,1,'2000-03-25 16:55:47','2000-03-25 16:55:47'),
	(100024,0,'止血调理',1,1,'2000-03-25 16:55:56','2000-03-25 16:55:56'),
	(100025,0,'疏肝通气',1,1,'2000-03-25 16:56:06','2000-03-25 16:56:06'),
	(100026,0,'心悸',1,1,'2000-03-25 16:56:22','2000-03-25 16:56:22'),
	(100027,0,'痢疾',1,1,'2000-03-25 16:56:30','2000-03-25 16:56:30'),
	(100028,0,'呕吐',1,1,'2000-03-25 16:56:37','2000-03-25 16:56:37'),
	(100029,0,'自汗盗汗',1,1,'2000-03-25 16:56:45','2000-03-25 16:56:45'),
	(100030,0,'胃调养',1,1,'2000-03-25 16:57:05','2000-03-25 16:57:05');


DROP TABLE IF EXISTS `mall_order`;
CREATE TABLE `mall_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shipping_id` int(11) DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mall_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mall_pay_info`;
CREATE TABLE `mall_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '支付宝支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT '支付宝支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `mall_product`;
CREATE TABLE `mall_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int(11) NOT NULL COMMENT '分类id,对应mall_category表的主键',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text COMMENT '图片地址,json格式,扩展用',
  `detail` text COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mall_product` (`id`, `category_id`, `name`, `subtitle`, `main_image`, `sub_images`, `detail`, `price`, `stock`, `status`, `create_time`, `update_time`)
VALUES
    (26,100006,'凉拌牛心','主料：牛心','https://st-cn.meishij.net/r/175/213/490925/s490925_142021040462101.jpg','','',999.00,999,1,'2022-04-13 19:07:47','2022-04-13 21:45:41'),
    (27,100007,'养肝护肝粥','主料：胚芽米','https://st-cn.meishij.net/r/169/94/8586169/s8586169_155694928259338.jpg','','',999.00,999,1,'2022-04-13 19:07:47','2022-04-13 21:45:41'),
    (28,100008,'健脾补虚粥','主料：莲子、芡实、薏米、淮山药、桂圆、白扁豆、红枣、粳米','https://st-cn.meishij.net/r/184/229/5557434/s5557434_147123299221965.jpg','','',999.00,999,1,'2022-04-13 19:07:47','2022-04-13 21:45:41'),
    (29,100009,'秋季养肺黄金粥','主料：黄金粥、红枣、黑玉米','https://st-cn.meishij.net/r/80/238/5684580/s5684580_150675018356347.jpg','','',999.00,999,1,'2022-04-13 19:07:47','2022-04-13 21:45:41'),
    (30,100011,'黄芪红枣补气汤','主料：油鸡、黄芪、红枣','https://st-cn.meishij.net/rs/105/225/1306355/n1306355_155858047996597.jpg','','',999.00,999,1, '2022-04-13 19:07:47','2022-04-13 21:45:41');

DROP TABLE IF EXISTS `mall_shipping`;
CREATE TABLE `mall_shipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mall_shipping` (`id`, `user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `receiver_zip`, `create_time`, `update_time`)
VALUES
	(4,1,'廖师兄','010','18688888888','北京','北京市','海淀区','中关村慕课网大楼','100000','2000-01-22 14:26:25','2000-01-22 14:26:25');

DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mall_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `create_time`, `update_time`)
VALUES
	(1,'admin','21232F297A57A5A743894A0E4A801FC3','admin@qq.com',NULL,NULL,NULL,0,'2000-08-06 15:12:00','2000-08-06 15:12:00');

-- 新增“支付金额”字段 xxxx年xx月xx日
alter table mall_pay_info add pay_amount decimal(20,2) NOT NULL COMMENT '支付金额' after platform_status;

-- 视频7-13修改mall_pay_info，修改后如下
DROP TABLE IF EXISTS `mall_pay_info`;
CREATE TABLE `mall_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) NOT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT '支付状态',
  `pay_amount` decimal(20,2) NOT NULL COMMENT '支付金额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uqe_order_no` (`order_no`),
  UNIQUE KEY `uqe_platform_number` (`platform_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 视频8-3修改mall_user表字段结构
alter table mall_user modify create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
alter table mall_user modify update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间';

DROP TABLE IF EXISTS `mall_product`;
CREATE TABLE `mall_product` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id 从1开始',
`category_id` int(11) NOT NULL COMMENT '分类id,对应mall_category表的主键 从100006开始',
`name` varchar(100) NOT NULL COMMENT '商品名称',
`subtitle` varchar(200) DEFAULT NULL COMMENT '主料；辅料',
`process` varchar(100) DEFAULT NULL COMMENT '工艺',
`main_image` varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
`comment` text COMMENT '评论',
`detail` text COMMENT '做法',
`analyse` text COMMENT '营养分析',
`tips` text COMMENT '技巧',
`favor` int(6) DEFAULT NULL COMMENT '收藏',
`status` int(6) DEFAULT '1' COMMENT '状态',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
