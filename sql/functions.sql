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

 Date: 11/02/2020 14:51:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for functions
-- ----------------------------
DROP TABLE IF EXISTS `functions`;
CREATE TABLE `functions`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '系统功能id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentid` int(0) NULL DEFAULT NULL COMMENT '父节点',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能页面',
  `isleaf` bit(1) NULL DEFAULT NULL COMMENT '是否叶节点',
  `nodeorder` int(0) NULL DEFAULT NULL COMMENT '节点顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of functions
-- ----------------------------
INSERT INTO `functions` VALUES (1, '电子商品管理后台', 0, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (2, '商品管理', 1, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (3, '商品列表', 2, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (4, '商品类型列表', 2, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (5, '订单管理', 1, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (6, '查询订单', 5, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (7, '创建订单', 5, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (8, '客户管理', 1, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (9, '客户列表', 8, NULL, NULL, NULL);
INSERT INTO `functions` VALUES (10, '退出系统', 0, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
