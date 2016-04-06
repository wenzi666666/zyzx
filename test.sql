SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zhl_country`
-- ----------------------------
DROP TABLE IF EXISTS `zhl_country`;
CREATE TABLE `zhl_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '���',
  `ccode` varchar(56) NOT NULL DEFAULT ' ' COMMENT '���',
  `cname` varchar(32) NOT NULL DEFAULT ' ' COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for `j_rolegroup`
-- ----------------------------
DROP TABLE IF EXISTS `j_rolegroup`;
CREATE TABLE `j_rolegroup` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '���',
  `group_id` bigint(15) NOT NULL DEFAULT '0' COMMENT '�û�����',
  `role_id` bigint(15) NOT NULL DEFAULT '0' COMMENT '�û����',
  PRIMARY KEY (`id`),
  KEY `fk_j_usergroup_0` (`group_id`),
  KEY `fk_j_usergroup_1` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_rolegroup
-- ----------------------------
ALTER TABLE `j_group`
ADD COLUMN `model`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ' ' COMMENT 'ϵͳģ��',
ADD COLUMN `tag`  bit(1) NOT NULL DEFAULT b'0' COMMENT '0-����,1-��ϵͳ�Զ���';

ALTER TABLE `j_role`
ADD COLUMN `model`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ' ' COMMENT 'ϵͳģ��',
ADD COLUMN `tag`  bit(1) NOT NULL DEFAULT b'0' COMMENT '0-����,1-��ϵͳ�Զ���';

ALTER TABLE `j_funclist`
ADD COLUMN `model`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ' ' COMMENT 'ϵͳģ��',
ADD COLUMN `tag`  bit(1) NOT NULL DEFAULT b'0' COMMENT '0-����,1-��ϵͳ�Զ���';

-- ----------------------------
-- Records of j_usergroup
-- ----------------------------
