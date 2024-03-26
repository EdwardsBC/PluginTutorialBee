/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : bee_db

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 26/03/2024 13:08:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dif_bee_settings
-- ----------------------------
DROP TABLE IF EXISTS `dif_bee_settings`;
CREATE TABLE `dif_bee_settings`  (
  `ID` int NOT NULL,
  `Angry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Strength` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dif_bee_settings
-- ----------------------------
INSERT INTO `dif_bee_settings` VALUES (1, '0', '1');

SET FOREIGN_KEY_CHECKS = 1;
