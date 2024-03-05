CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(45) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `auth_type` varchar(100) DEFAULT NULL,
  `auth_role` varchar(100) DEFAULT NULL,
  `password_wrong_count` tinyint(1) DEFAULT 0 NULL,
  `member_level` bigint DEFAULT 1 NULL,
  `member_exp` bigint DEFAULT 1 NULL,
  `content_count` bigint DEFAULT 0 NULL,
  `reply_count` bigint DEFAULT 0 NULL,
  `join_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_login_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;