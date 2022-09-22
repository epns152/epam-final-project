

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';



CREATE SCHEMA IF NOT EXISTS `demo` DEFAULT CHARACTER SET utf8mb3 ;
USE `demo` ;

-- -----------------------------------------------------
-- Table `demo`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`users` ;

CREATE TABLE IF NOT EXISTS `demo`.`users` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `login` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `firstname` VARCHAR(45) NOT NULL,
    `lastname` VARCHAR(45) NOT NULL,
    `user_status` VARCHAR(10) NOT NULL DEFAULT 'unblocked',
    `user_role` VARCHAR(10) NOT NULL DEFAULT 'customer',
    `registration_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    FULLTEXT INDEX `idx_user_login` (`login`) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `demo`.`accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`accounts` ;

CREATE TABLE IF NOT EXISTS `demo`.`accounts` (
                                                 `id` INT NOT NULL,
                                                 `account_name` VARCHAR(45) NOT NULL,
    `balance_amount` DECIMAL(10,2) UNSIGNED NOT NULL,
    `Users_id` INT NOT NULL,
    `unblock_request` INT NOT NULL DEFAULT '0',
    `account_status` VARCHAR(15) NOT NULL DEFAULT 'unblocked',
    `card_id` BIGINT GENERATED ALWAYS AS ((53531100 + `id`)) VIRTUAL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `card_id_UNIQUE` (`card_id` ASC) VISIBLE,
    INDEX `fk_Accounts_Users_idx` (`Users_id` ASC) VISIBLE,
    CONSTRAINT `fk_Accounts_Users`
    FOREIGN KEY (`Users_id`)
    REFERENCES `demo`.`users` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `demo`.`payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`payments` ;

CREATE TABLE IF NOT EXISTS `demo`.`payments` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `price` DECIMAL(9,2) UNSIGNED NOT NULL,
    `payment_name` VARCHAR(45) NOT NULL,
    `creation_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `payment_status` INT NOT NULL DEFAULT '1',
    `users_id` INT NOT NULL,
    `account_number_received` BIGINT UNSIGNED NOT NULL,
    `account_number_sent` BIGINT UNSIGNED NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    INDEX `fk_payments_users1_idx` (`users_id` ASC) VISIBLE,
    CONSTRAINT `fk_payments_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `demo`.`users` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `users` VALUES (3,'admin','21232f297a57a5a743894a0e4a801fc3','admin','admin','unblocked','admin','2022-08-22 00:19:10');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
