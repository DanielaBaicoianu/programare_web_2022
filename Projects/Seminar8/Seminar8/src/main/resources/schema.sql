CREATE TABLE if not exists `laborator8_db`.`users`(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `username`        VARCHAR(45) NOT NULL,
    `full_name`       VARCHAR(45) NULL,
    `user_type`       VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
    );

CREATE TABLE if not exists `laborator8_db`.`user_details`(
    `id`                BIGINT      NOT NULL AUTO_INCREMENT,
    `user_id`        BIGINT NOT NULL,
    `cnp`               VARCHAR(45) NOT NULL,
    `age`               INT NULL,
    `other_information` VARCHAR(255) NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `laborator8_db`.`users` (`id`)
    );
