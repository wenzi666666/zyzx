/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : j2ee_demo

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-03-21 14:48:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `url` varchar(120) NOT NULL DEFAULT ' ' COMMENT '资源url',
  `description` varchar(60) NOT NULL DEFAULT ' ' COMMENT '权限说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', ' /user/add', ' ');
INSERT INTO `sys_resource` VALUES ('2', ' /user/list', ' ');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT ' ' COMMENT '角色名',
  `description` varchar(50) NOT NULL DEFAULT ' ' COMMENT '角色说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', ' admin', ' ');
INSERT INTO `sys_role` VALUES ('2', ' teacher', ' ');
INSERT INTO `sys_role` VALUES ('3', ' student', ' ');

-- ----------------------------
-- Table structure for `sys_role_permisson`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permisson`;
CREATE TABLE `sys_role_permisson` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(15) NOT NULL DEFAULT '0' COMMENT '角色编号',
  `resource_id` int(15) NOT NULL COMMENT '资源编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_role_permission_0` (`role_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permisson
-- ----------------------------
INSERT INTO `sys_role_permisson` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permisson` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(50) NOT NULL DEFAULT ' ' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT ' ' COMMENT '密码',
  `salt` varchar(50) NOT NULL DEFAULT ' ' COMMENT '加密盐',
  `create_date` datetime NOT NULL DEFAULT '2000-10-01 08:08:08' COMMENT '创建日期',
  `isDelete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_name_index_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'aaa', '000000', ' ', '2000-10-01 08:08:08', '0');
INSERT INTO `sys_user` VALUES ('2', 'bruce', '000000', ' ', '2000-10-01 08:08:08', '0');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `user_id` int(15) NOT NULL DEFAULT '0',
  `role_id` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_user_role_1` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1', '3');
INSERT INTO `sys_user_role` VALUES ('4', '2', '3');

-- ----------------------------
-- Table structure for `zhl_country`
-- ----------------------------
DROP TABLE IF EXISTS `zhl_country`;
CREATE TABLE `zhl_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ccode` varchar(56) NOT NULL DEFAULT ' ' COMMENT '编号',
  `cname` varchar(32) NOT NULL DEFAULT ' ' COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhl_country
-- ----------------------------
INSERT INTO `zhl_country` VALUES ('1', '1000', 'china');
INSERT INTO `zhl_country` VALUES ('2', '1000', 'china');
INSERT INTO `zhl_country` VALUES ('3', '1000', 'china');
INSERT INTO `zhl_country` VALUES ('4', '1000', 'china');
INSERT INTO `zhl_country` VALUES ('5', '1000', 'china');
INSERT INTO `zhl_country` VALUES ('6', '1000', 'china');
INSERT INTO `zhl_country` VALUES ('7', '1000', 'china');
INSERT INTO `zhl_country` VALUES ('8', '1000', 'china');
