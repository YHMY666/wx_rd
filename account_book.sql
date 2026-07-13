/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80045 (8.0.45)
 Source Host           : localhost:3306
 Source Schema         : account_book

 Target Server Type    : MySQL
 Target Server Version : 80045 (8.0.45)
 File Encoding         : 65001

 Date: 08/07/2026 15:37:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `type` tinyint(1) NOT NULL COMMENT '类型 0-支出 1-收入',
  `category` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类(餐饮/交通/购物/工资等)',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `record_date` date NOT NULL COMMENT '记账日期',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_date`(`user_id` ASC, `record_date` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '记账记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 1, 0, '住房', 1000.00, '出租', '2026-07-01', '2026-07-01 16:47:05', '2026-07-01 16:47:05');
INSERT INTO `record` VALUES (2, 1, 0, '购物', 99.00, '', '2026-07-01', '2026-07-01 16:47:27', '2026-07-01 16:47:27');
INSERT INTO `record` VALUES (3, 1, 1, '工资', 10000.00, '得到奖金', '2026-07-01', '2026-07-01 16:55:43', '2026-07-01 16:55:43');
INSERT INTO `record` VALUES (4, 1, 0, '娱乐', 100.00, '', '2026-07-08', '2026-07-08 10:10:14', '2026-07-08 10:10:14');
INSERT INTO `record` VALUES (5, 1, 0, '医疗', 1000000.00, '', '2026-07-08', '2026-07-08 10:14:14', '2026-07-08 10:14:14');
INSERT INTO `record` VALUES (6, 1, 1, '工资', 100000.00, '', '2026-07-08', '2026-07-08 10:14:47', '2026-07-08 10:14:47');
INSERT INTO `record` VALUES (7, 1, 1, '工资', 99999999.00, '', '2026-07-08', '2026-07-08 10:15:53', '2026-07-08 10:15:53');
INSERT INTO `record` VALUES (8, 1, 0, '餐饮', 1111.00, '', '2026-07-08', '2026-07-08 10:16:09', '2026-07-08 10:16:09');
INSERT INTO `record` VALUES (9, 1, 0, '交通', 111.00, '', '2026-07-08', '2026-07-08 10:17:33', '2026-07-08 10:17:33');
INSERT INTO `record` VALUES (10, 1, 0, '购物', 1111.00, '', '2026-07-08', '2026-07-08 10:17:41', '2026-07-08 10:17:41');
INSERT INTO `record` VALUES (11, 1, 0, '购物', 1111.00, '', '2026-07-08', '2026-07-08 10:22:53', '2026-07-08 10:22:53');
INSERT INTO `record` VALUES (18, 1, 0, '教育', 19198.00, '学校缴费', '2026-07-08', '2026-07-08 14:09:01', '2026-07-08 14:09:01');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信唯一标识',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '昵称',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_openid`(`openid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'oR-0z3e3xBJIqyQq011hcRH1EEtE', '微信用户', '', '2026-07-01 16:19:50');

SET FOREIGN_KEY_CHECKS = 1;
