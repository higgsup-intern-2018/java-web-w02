/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : higgsup_intern_training

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 03/07/2018 14:00:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `instructor_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES (1, 'Java Technology', 'Java', 1);
INSERT INTO `classroom` VALUES (5, 'WEB Technology', 'WEB', 2);
INSERT INTO `classroom` VALUES (6, 'Project Management', 'PJM', 3);
INSERT INTO `classroom` VALUES (7, 'Special Subject', 'SS2', 5);

-- ----------------------------
-- Table structure for enrollment
-- ----------------------------
DROP TABLE IF EXISTS `enrollment`;
CREATE TABLE `enrollment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `classroom_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id_idx`(`student_id`) USING BTREE,
  INDEX `classroom_id_idx`(`classroom_id`) USING BTREE,
  CONSTRAINT `classroom_id` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enrollment
-- ----------------------------
INSERT INTO `enrollment` VALUES (1, 1, 1);
INSERT INTO `enrollment` VALUES (2, 1, 5);
INSERT INTO `enrollment` VALUES (3, 1, 6);
INSERT INTO `enrollment` VALUES (5, 6, 7);
INSERT INTO `enrollment` VALUES (6, 3, 5);
INSERT INTO `enrollment` VALUES (8, 4, 6);
INSERT INTO `enrollment` VALUES (9, 5, 7);
INSERT INTO `enrollment` VALUES (10, 8, 1);
INSERT INTO `enrollment` VALUES (11, 9, 5);
INSERT INTO `enrollment` VALUES (13, 7, 6);
INSERT INTO `enrollment` VALUES (22, 4, 5);
INSERT INTO `enrollment` VALUES (25, 1, 7);
INSERT INTO `enrollment` VALUES (26, 4, 7);

-- ----------------------------
-- Table structure for instructor
-- ----------------------------
DROP TABLE IF EXISTS `instructor`;
CREATE TABLE `instructor`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_of_birth` int(11) NULL DEFAULT NULL,
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of instructor
-- ----------------------------
INSERT INTO `instructor` VALUES (1, 'Nguyen Van Cong', 1992, 'Ha Noi');
INSERT INTO `instructor` VALUES (2, 'Nguyen Dinh Tran Long', 1990, 'Nghe An');
INSERT INTO `instructor` VALUES (3, 'Ta Quang Hung', 1978, 'Ha Noi');
INSERT INTO `instructor` VALUES (4, 'Vu Minh Tuan', 1976, 'Ha Noi');
INSERT INTO `instructor` VALUES (5, 'Le Minh Duc', 1974, 'Hai Duong');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_of_birth` int(11) NULL DEFAULT NULL,
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'Hoang', 1997, 'Ha Noi');
INSERT INTO `student` VALUES (2, 'Binh', 1996, 'Ha Nam');
INSERT INTO `student` VALUES (3, 'An', 1997, 'Bac Giang');
INSERT INTO `student` VALUES (4, 'Kien', 1997, 'Ha Noi');
INSERT INTO `student` VALUES (5, 'Bao', 1997, 'Hung Yen');
INSERT INTO `student` VALUES (6, 'Du', 1997, 'Vinh Phuc');
INSERT INTO `student` VALUES (7, 'Hanh', 1997, 'Phu Tho');
INSERT INTO `student` VALUES (8, 'Cuc', 1997, 'Vinh Phuc');
INSERT INTO `student` VALUES (9, 'Huong', 1996, 'Ha Giang');
INSERT INTO `student` VALUES (10, 'Luu', 1997, 'Phu Tho');
INSERT INTO `student` VALUES (11, 'Cuc', 1997, 'Vinh Phuc');
INSERT INTO `student` VALUES (27, 'Phuc', 1996, 'Hai Phong');
INSERT INTO `student` VALUES (28, 'Phong', 1997, 'Ha Noi');

SET FOREIGN_KEY_CHECKS = 1;
