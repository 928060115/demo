/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50159
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50159
File Encoding         : 65001

Date: 2017-12-06 13:40:30
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;

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
INSERT INTO `user` VALUES ('11', 'admin', '123456', 'admintwo', '2', '2017-12-05 13:55:26', '123123123');
