CREATE TABLE `jhahn`.`board` (
 `board_id` BIGINT NOT NULL AUTO_INCREMENT,
 `board_category` TINYINT(1) NOT NULL,
 `board_title` VARCHAR(300) NOT NULL,
 `board_content` TEXT NULL,
 `view_count` BIGINT NULL DEFAULT 0,
 `reply_count` BIGINT NULL DEFAULT 0,
 `writer_id` BIGINT NULL,
 `write_datetime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
 `update_datetime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 `delete_yn` VARCHAR(1) NOT NULL DEFAULT 'N',
 PRIMARY KEY (`board_id`));
