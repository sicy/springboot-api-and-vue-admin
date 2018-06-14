/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - txcp_db_business_dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_sys_group` */

DROP TABLE IF EXISTS `t_sys_group`;

CREATE TABLE `t_sys_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `group_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `group_desc` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '分组描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

/*Data for the table `t_sys_group` */

insert  into `t_sys_group`(`id`,`group_name`,`group_desc`) values (6,'管理员','管理员');

/*Table structure for table `t_sys_group_auth` */

DROP TABLE IF EXISTS `t_sys_group_auth`;

CREATE TABLE `t_sys_group_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(10) NOT NULL COMMENT '分组ID',
  `menu_id` int(10) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

/*Data for the table `t_sys_group_auth` */


/*Table structure for table `t_sys_menu` */

DROP TABLE IF EXISTS `t_sys_menu`;

CREATE TABLE `t_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `icon_class` varchar(100) DEFAULT NULL COMMENT '样式类',
  `level` int(4) DEFAULT NULL COMMENT '级别',
  `fid` int(11) DEFAULT NULL COMMENT '父id',
  `order_code` int(5) DEFAULT NULL COMMENT '排序值',
  `state` int(3) DEFAULT NULL COMMENT '状态,1允许，0禁止',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_menu` */

insert  into `t_sys_menu`(`id`,`menu_name`,`url`,`icon_class`,`level`,`fid`,`order_code`,`state`) values (1,'系统管理',NULL,'el-icon-setting',0,NULL,1,1);
insert  into `t_sys_menu`(`id`,`menu_name`,`url`,`icon_class`,`level`,`fid`,`order_code`,`state`) values (2,'菜单管理','/menu','fa fa-list',1,1,1,1);
insert  into `t_sys_menu`(`id`,`menu_name`,`url`,`icon_class`,`level`,`fid`,`order_code`,`state`) values (3,'用户管理','/user','fa fa-user',1,1,2,1);
insert  into `t_sys_menu`(`id`,`menu_name`,`url`,`icon_class`,`level`,`fid`,`order_code`,`state`) values (4,'分组管理','/group','fa fa-group',1,1,3,1);

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `email` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `real_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '注册人/企业联系人姓名',
  `mobilephone_number` char(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号码',
  `group_id` int(10) DEFAULT NULL COMMENT '分组id',
  `status` int(3) unsigned DEFAULT NULL COMMENT '状态(0表示激活,1表示禁用,2表示锁定)',
  `role_id` tinyint(1) DEFAULT NULL COMMENT '角色(暂时没有用上)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `mobilephone_number` (`mobilephone_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`user_name`,`password`,`email`,`real_name`,`mobilephone_number`,`group_id`,`status`,`role_id`,`create_time`,`expire_time`) values (-999,'sysadmin','c12e01f2a13ff5587e1e9e4aedb8242d',NULL,'超级管理员',NULL,0,0,NULL,NULL,NULL);

/*Table structure for table `t_sys_user_auth` */

DROP TABLE IF EXISTS `t_sys_user_auth`;

CREATE TABLE `t_sys_user_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `menu_id` int(10) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
