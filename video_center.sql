/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Schema         : video_center

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 25/10/2023 22:01:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `video_name` varchar(550) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频名称',
  `video_path` varchar(550) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频的地址',
  `video_img_path` varchar(550) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频图片地址',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 617 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_video_category
-- ----------------------------
DROP TABLE IF EXISTS `t_video_category`;
CREATE TABLE `t_video_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `video_id` int NULL DEFAULT NULL COMMENT '视频id',
  `category_id` int NULL DEFAULT NULL COMMENT '分类id',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
