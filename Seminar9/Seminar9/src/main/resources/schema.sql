CREATE TABLE if not exists `laborator8_db`.`users`(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `username`        VARCHAR(45) NOT NULL,
    `full_name`       VARCHAR(45) NULL,
    `user_type`       VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
    );