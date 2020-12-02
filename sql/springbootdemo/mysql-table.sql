------------------------------------
---表名：`demo_user`（用户信息）
------------------------------------
CREATE TABLE `demo_user`
(
	`us_id`         		    int(11) AUTO_INCREMENT NOT NULL COMMENT '用户编号',
	`us_name`                   varchar(64) NOT NULL COMMENT '用户名称',
	`us_account`       	        varchar(64) NOT NULL COMMENT '账号',
	`us_password`       	    varchar(64) NOT NULL COMMENT '密码',
	`us_deleted`                smallint(6) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	`us_create_time`            datetime COMMENT '创建时间',
	`us_update_time`            datetime COMMENT '修改时间',
	PRIMARY KEY (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';


------------------------------------
---表名：`demo_role`（角色信息）
------------------------------------
CREATE TABLE `demo_role`
(
	`ro_id`         		   int(11) AUTO_INCREMENT NOT NULL COMMENT '角色编号',
	`ro_name`                  varchar(64) NOT NULL COMMENT '角色名称',
	`ro_name_ch`               varchar(64) NOT NULL COMMENT '角色中文名称',
	`ro_desc`                  varchar(1024) COMMENT '角色描述',
	`ro_deleted`               smallint(6) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	`ro_create_time`           datetime COMMENT '创建时间',
	`ro_update_time`           datetime COMMENT '修改时间',
	PRIMARY KEY (`ro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';


------------------------------------
---表名：`demo_user`（用户-角色中间表）
------------------------------------
CREATE TABLE `demo_person_role`
(
	`pr_id`         	    int(11) AUTO_INCREMENT NOT NULL COMMENT '主键',
	`us_id`                 int(11) NOT NULL COMMENT '用户编号',
	`ro_id`                 int(11) NOT NULL COMMENT '角色编号',
	PRIMARY KEY (`pr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色中间表';


------------------------------------
---表名：`demo_resource`（权限信息）
------------------------------------
CREATE TABLE `demo_resource`
(
	`rs_id`         		    int(11) AUTO_INCREMENT NOT NULL COMMENT '权限编号',
	`rs_url`                    varchar(64) NOT NULL COMMENT '地址',
	`rs_name`                   varchar(64) NOT NULL COMMENT '权限名称',
	`rs_desc`                   varchar(1024) COMMENT '权限描述',
	`rs_deleted`                smallint(6) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	`rs_create_time`            datetime COMMENT '创建时间',
	`rs_update_time`            datetime COMMENT '修改时间',
	PRIMARY KEY (`rs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息';


------------------------------------
---表名：`demo_role_resource`（角色-权限中间表）
------------------------------------
CREATE TABLE `demo_role_resource`
(
	`ros_id`         		    int(11) AUTO_INCREMENT NOT NULL COMMENT '主键',
	`ro_id`                     int(11) NOT NULL COMMENT '角色编号',
	`rs_id`                     int(11) NOT NULL COMMENT '权限编号',
	PRIMARY KEY (`ros_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限中间表';


------------------------------------
---表名：`demo_person`（人员信息）
------------------------------------
CREATE TABLE `demo_person`
(
	`ps_id`         		    int(11) AUTO_INCREMENT NOT NULL COMMENT '人员编号',
	`ps_name`       		    varchar(32) COMMENT '姓名',
	`ps_account`       	        varchar(64) NOT NULL COMMENT '账号',
	`ps_password`       	    varchar(64) NOT NULL COMMENT '密码',
	`ps_age`        		    int(4) COMMENT '年龄',
	`ps_sex`       	 	        varchar(1) COMMENT '性别（0：女，1：男）',
	`ps_tel`        		    varchar(20) COMMENT '电话',
	`ps_email`        		    varchar(35) COMMENT '邮箱',
	`ps_address`    		    varchar(20) COMMENT '地址',
	`ps_deleted`                smallint(6) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	`ps_create_time`            datetime COMMENT '创建时间',
	`ps_update_time`            datetime COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息';


------------------------------------
---表名：`demo_department`（部门信息）
------------------------------------
CREATE TABLE `demo_department`
(
	`id`         		    int(11) AUTO_INCREMENT NOT NULL COMMENT '部门编号',
	`parent_id`             int(11) NOT NULL COMMENT '父级编号',
	`name`       		    varchar(10) COMMENT '部门名称',
	`deleted`               smallint(6) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	`create_time`           datetime COMMENT '创建时间',
	`update_time`           datetime COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息';


------------------------------------
---表名：`demo_category`（类别信息）
------------------------------------
CREATE TABLE `demo_category`
(
	`id`         		    int(11) AUTO_INCREMENT NOT NULL COMMENT '类别编号',
	`parent_id`             int(11) NOT NULL COMMENT '父级编号',
	`name`       		    varchar(10) COMMENT '类别名称',
	`deleted`               smallint(6) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	`create_time`           datetime COMMENT '创建时间',
	`update_time`           datetime COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类别信息';


