/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50718
Source Host           : 127.0.0.1:3306
Source Database       : vue_admin_db

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-06-27 19:54:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `perms` varchar(200) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '-1', '系统管理', 'sys', '0', '1', '2018-06-23 11:26:33', '2018-06-23 11:26:33');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户列表', 'sys:user:list', '1', '1', '2018-06-23 11:30:25', '2018-06-23 11:30:25');
INSERT INTO `sys_menu` VALUES ('3', '1', '菜单列表', 'sys:menu:list', '1', '2', '2018-06-23 11:32:32', '2018-06-23 11:32:32');
INSERT INTO `sys_menu` VALUES ('4', '2', '添加', 'sys:user:add', '2', '1', '2018-06-23 11:36:52', '2018-06-23 11:36:52');
INSERT INTO `sys_menu` VALUES ('5', '2', '编辑', 'sys:user:edit', '2', '2', '2018-06-23 11:37:30', '2018-06-23 11:37:30');
INSERT INTO `sys_menu` VALUES ('6', '2', '删除', 'sys:user:delete', '2', '3', '2018-06-23 11:38:24', '2018-06-23 11:38:24');
INSERT INTO `sys_menu` VALUES ('7', '2', '修改状态', 'sys:user:modify', '2', '4', '2018-06-23 11:39:42', '2018-06-23 11:39:42');
INSERT INTO `sys_menu` VALUES ('8', '2', '重置密码', 'sys:user:resetPwd', '2', '5', '2018-06-23 11:40:15', '2018-06-23 11:40:15');
INSERT INTO `sys_menu` VALUES ('9', '3', '添加', 'sys:menu:add', '2', '1', '2018-06-23 11:36:52', '2018-06-23 11:36:52');
INSERT INTO `sys_menu` VALUES ('10', '3', '编辑', 'sys:menu:edit', '2', '2', '2018-06-23 11:37:30', '2018-06-23 11:37:30');
INSERT INTO `sys_menu` VALUES ('11', '3', '删除', 'sys:menu:delete', '2', '3', '2018-06-23 11:38:24', '2018-06-23 11:38:24');
INSERT INTO `sys_menu` VALUES ('12', '1', '角色列表', 'sys:role:list', '1', '3', '2018-06-23 11:32:32', '2018-06-23 11:32:32');
INSERT INTO `sys_menu` VALUES ('13', '12', '添加', 'sys:role:add', '2', '1', '2018-06-23 11:36:52', '2018-06-23 11:36:52');
INSERT INTO `sys_menu` VALUES ('14', '12', '编辑', 'sys:role:edit', '2', '2', '2018-06-23 11:37:30', '2018-06-23 11:37:30');
INSERT INTO `sys_menu` VALUES ('15', '12', '删除', 'sys:role:delete', '2', '3', '2018-06-23 11:38:24', '2018-06-23 11:38:24');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_key` varchar(32) NOT NULL COMMENT '角色标识',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `idx_roleKey` (`role_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'Admin', '管理员权限', '系统管理员', '2018-06-25 17:17:12', '2018-06-27 15:43:07');
INSERT INTO `sys_role` VALUES ('2', 'Test', '测试权限', '系统测试', '2018-06-25 13:38:06', '2018-06-27 18:25:40');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('122', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('123', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('124', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('125', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('126', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('127', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('128', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('129', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('130', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('131', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('132', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('133', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('134', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('135', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('136', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('148', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('149', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('150', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('151', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('152', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('153', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('154', '2', '11');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `role_key` varchar(32) DEFAULT NULL COMMENT '角色标识',
  `desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', '/avatar/458954_458552.gif', '1', 'Admin', '高级管理员', '2018-06-20 15:27:07', '2018-06-22 17:34:40');
INSERT INTO `sys_user` VALUES ('3', 'wubing', 'WB', 'adcf1f5688c2802a8b19dee44f89588f', '/avatar/315756_982576.jpg', '1', 'Test', '高级管理员', '2018-06-21 16:44:56', '2018-06-22 17:18:59');
INSERT INTO `sys_user` VALUES ('4', 'test', 'test', 'e10adc3949ba59abbe56e057f20f883e', '/avatar/242834_798224.gif', '1', 'Test', '测试用户', '2018-06-22 17:39:19', '2018-06-27 18:21:05');
INSERT INTO `sys_user` VALUES ('5', 'Wbing', 'Wbing', 'e10adc3949ba59abbe56e057f20f883e', '/avatar/542217_127085.png', '1', 'Admin', 'Wbing 测试', '2018-06-27 15:45:16', '2018-06-27 15:45:34');
