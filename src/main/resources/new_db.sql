CREATE DATABASE `library_users`;
CREATE TABLE `library_users`.`user` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL DEFAULT '',
  `email` VARCHAR(100) NOT NULL DEFAULT '',
  `password` VARCHAR(100) NOT NULL DEFAULT '',
  `active` BOOLEAN NOT NULL DEFAULT 1,
  `role_id` INTEGER NOT NULL,
  PRIMARY KEY(`id`)
)
ENGINE = InnoDB;

CREATE TABLE `library_users`.`roles` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY(`id`)
)
ENGINE = InnoDB;

ALTER TABLE `library_users`.`user` 
DROP COLUMN `password`,
ADD COLUMN `add_timestamp` TIMESTAMP NOT NULL AFTER `role_id`,
ADD COLUMN `update_timestamp` TIMESTAMP NULL AFTER `add_timestamp`,
ADD COLUMN `deleted` TINYINT(1) NOT NULL DEFAULT 0 AFTER `update_timestamp`;

CREATE TABLE `library_users`.`password` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `time_stamp` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));
  
ALTER TABLE `library_users`.`password` 
ADD COLUMN `active` TINYINT NOT NULL DEFAULT 1 AFTER `time_stamp`;

ALTER TABLE `library_users`.`user` 
CHANGE COLUMN `add_timestamp` `add_timestamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ;

ALTER TABLE `library_users`.`user` 
DROP COLUMN `add_timestamp`;

ALTER TABLE `library_users`.`password` 
CHANGE COLUMN `time_stamp` `time_stamp` TIMESTAMP NULL ;

INSERT INTO `library_users`.`roles` (`id`, `role`) VALUES ('1', 'User');
INSERT INTO `library_users`.`roles` (`id`, `role`) VALUES ('2', 'Admin');