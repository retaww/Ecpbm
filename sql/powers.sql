/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : ecpbm

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 11/02/2020 14:51:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for powers
-- ----------------------------
DROP TABLE IF EXISTS `powers`;
CREATE TABLE `powers`  (
  `aid` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `fid` int(0) NOT NULL COMMENT '系统功能id',
  PRIMARY KEY (`aid`, `fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
