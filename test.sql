/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-03-08 21:39:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zhl_country`
-- ----------------------------
DROP TABLE IF EXISTS `zhl_country`;
CREATE TABLE `zhl_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ccode` varchar(56) NOT NULL DEFAULT ' ' COMMENT '编号',
  `cname` varchar(32) NOT NULL DEFAULT ' ' COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for `zhl_user`
-- ----------------------------
DROP TABLE IF EXISTS `zhl_user`;
CREATE TABLE `zhl_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT ' ' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT ' ' COMMENT '密码',
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhl_user
-- ----------------------------
INSERT INTO `zhl_user` VALUES ('1', ' user', 'user', 'ser@tt.com');
INSERT INTO `zhl_user` VALUES ('4', '10000', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('5', '10001', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('6', '10002', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('7', '10003', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('8', '10004', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('9', '10005', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('10', '10006', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('11', '10007', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('12', '10008', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('13', '10009', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('14', '100010', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('15', '100011', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('16', '100012', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('17', '100013', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('18', '100014', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('19', '100015', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('20', '100016', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('21', '100017', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('22', '100018', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('23', '100019', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('24', '100020', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('25', '100021', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('26', '100022', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('27', '100023', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('28', '100024', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('29', '100025', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('30', '100026', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('31', '100027', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('32', '100028', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('33', '100029', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('34', '100030', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('35', '100031', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('36', '100032', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('37', '100033', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('38', '100034', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('39', '100035', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('40', '100036', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('41', '100037', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('42', '100038', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('43', '100039', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('44', '100040', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('45', '100041', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('46', '100042', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('47', '100043', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('48', '100044', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('49', '100045', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('50', '100046', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('51', '100047', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('52', '100048', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('53', '100049', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('54', '100050', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('55', '100051', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('56', '100052', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('57', '100053', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('58', '100054', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('59', '100055', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('60', '100056', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('61', '100057', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('62', '100058', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('63', '100059', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('64', '100060', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('65', '100061', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('66', '100062', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('67', '100063', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('68', '100064', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('69', '100065', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('70', '100066', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('71', '100067', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('72', '100068', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('73', '100069', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('74', '100070', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('75', '100071', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('76', '100072', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('77', '100073', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('78', '100074', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('79', '100075', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('80', '100076', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('81', '100077', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('82', '100078', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('83', '100079', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('84', '100080', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('85', '100081', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('86', '100082', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('87', '100083', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('88', '100084', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('89', '100085', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('90', '100086', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('91', '100087', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('92', '100088', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('93', '100089', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('94', '100090', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('95', '100091', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('96', '100092', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('97', '100093', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('98', '100094', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('99', '100095', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('100', '100096', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('101', '100097', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('102', '100098', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('103', '100099', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('104', '1100', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('105', '1100', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('106', '1100', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('107', '1100', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('108', '1100', 'ssss', 'kk@qq.com');
INSERT INTO `zhl_user` VALUES ('109', '1100', 'ssss', 'kk@qq.com');
