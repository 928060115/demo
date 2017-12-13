/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50159
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50159
File Encoding         : 65001

Date: 2017-12-13 17:57:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accesstoken
-- ----------------------------
DROP TABLE IF EXISTS `accesstoken`;
CREATE TABLE `accesstoken` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accesstoken
-- ----------------------------

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------

-- ----------------------------
-- Table structure for good_infos
-- ----------------------------
DROP TABLE IF EXISTS `good_infos`;
CREATE TABLE `good_infos` (
  `tg_id` int(11) NOT NULL,
  `tg_title` varchar(50) DEFAULT NULL,
  `tg_price` decimal(10,2) DEFAULT NULL,
  `tg_unit` varchar(20) DEFAULT NULL,
  `tg_order` int(2) DEFAULT NULL,
  `tg_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_infos
-- ----------------------------
INSERT INTO `good_infos` VALUES ('1', '油菜', '3.60', '斤 ', '2', '1');

-- ----------------------------
-- Table structure for good_types
-- ----------------------------
DROP TABLE IF EXISTS `good_types`;
CREATE TABLE `good_types` (
  `tgt_id` int(11) NOT NULL,
  `tgt_name` varchar(30) DEFAULT NULL,
  `tgt_is_show` char(1) DEFAULT NULL,
  `tgt_order` int(2) DEFAULT NULL,
  PRIMARY KEY (`tgt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_types
-- ----------------------------
INSERT INTO `good_types` VALUES ('1', '有机蔬菜', '1', '1');

-- ----------------------------
-- Table structure for refreshtoken
-- ----------------------------
DROP TABLE IF EXISTS `refreshtoken`;
CREATE TABLE `refreshtoken` (
  `rtoken_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of refreshtoken
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `r_id` int(11) NOT NULL,
  `r_name` varchar(30) DEFAULT NULL,
  `r_flag` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '超级管理员', 'ROLE_ADMIN');
INSERT INTO `roles` VALUES ('2', '普通用户', 'ROLE_CUSTOM');

-- ----------------------------
-- Table structure for t_logger_infos
-- ----------------------------
DROP TABLE IF EXISTS `t_logger_infos`;
CREATE TABLE `t_logger_infos` (
  `ali_id` int(11) NOT NULL AUTO_INCREMENT,
  `ali_client_ip` varchar(30) DEFAULT NULL,
  `ali_uri` varchar(100) DEFAULT NULL,
  `ali_type` varchar(50) DEFAULT NULL,
  `ali_method` varchar(10) DEFAULT NULL,
  `ali_param_data` longtext,
  `ali_session_id` varchar(100) DEFAULT NULL,
  `ali_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `ali_return_time` varchar(50) DEFAULT NULL,
  `ali_return_data` longtext,
  `ali_http_status_code` varchar(10) DEFAULT NULL,
  `ali_time_consuming` int(8) DEFAULT NULL,
  PRIMARY KEY (`ali_id`)
) ENGINE=InnoDB AUTO_INCREMENT=961 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logger_infos
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mobile` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '2@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-2', '1', '2017-11-27 14:59:37', '18566668888');
INSERT INTO `user` VALUES ('3', '3@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-3', '2', '2017-12-05 13:55:33', '18566668888');
INSERT INTO `user` VALUES ('4', '4@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-4', '1', '2017-11-27 14:59:37', '18566668888');
INSERT INTO `user` VALUES ('5', '5@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-5', '2', '2017-12-05 13:55:31', '18566668888');
INSERT INTO `user` VALUES ('6', '6@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-6', '1', '2017-11-27 14:59:37', '18566668888');
INSERT INTO `user` VALUES ('7', '7@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-7', '2', '2017-12-05 13:55:36', '18566668888');
INSERT INTO `user` VALUES ('8', '8@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-8', '2', '2017-12-05 13:55:30', '18566668888');
INSERT INTO `user` VALUES ('9', '9@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '土豆-9', '1', '2017-11-27 14:59:37', '18566668888');
INSERT INTO `user` VALUES ('10', '10@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '', '1', '2017-11-30 10:48:33', '18566668888');
INSERT INTO `user` VALUES ('11', 'admin', '12345678', 'admintwo', '2', '2017-12-12 10:33:29', '123123123');

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `user_authority_idx_1` (`username`,`authority`),
  KEY `authority` (`authority`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('admin', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('rajith', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('user', 'ROLE_USER');

-- ----------------------------
-- Table structure for user_oauth
-- ----------------------------
DROP TABLE IF EXISTS `user_oauth`;
CREATE TABLE `user_oauth` (
  `username` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `activated` tinyint(1) DEFAULT NULL,
  `activationkey` varchar(50) DEFAULT NULL,
  `resetpasswordkey` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_oauth
-- ----------------------------
INSERT INTO `user_oauth` VALUES ('admin', 'admin@mail.me', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1', '1', null, null);
INSERT INTO `user_oauth` VALUES ('rajith', 'rajith@abc.com', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', '1', null, null);
INSERT INTO `user_oauth` VALUES ('user', 'user@mail.me', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', '1', null, null);

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `ur_id` int(11) NOT NULL,
  `ur_user_id` int(11) DEFAULT NULL,
  `ur_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '11', '1');
INSERT INTO `user_roles` VALUES ('2', '11', '2');
