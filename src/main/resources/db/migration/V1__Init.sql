--DROP DATABASE `bank`;
--CREATE DATABASE IF NOT EXISTS `bank` CHARACTER SET utf8 COLLATE utf8_general_ci;

create TABLE IF NOT EXISTS `persona` (
  `id_persona` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `birth_date` DATETIME NULL DEFAULT NULL,
  `cpf` VARCHAR(255) NULL DEFAULT NULL,
  `date_created` DATETIME NULL DEFAULT NULL,
  `date_updated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

create TABLE IF NOT EXISTS `sign_up_step` (
  `id_sign_up_step` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `step` INT(5) NOT NULL,
  `id_persona` BIGINT(20) NOT NULL,
  `date_created` DATETIME NULL DEFAULT NULL,
  `date_updated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_sign_up_step`),
  KEY `key_sign_up_step_persona` (`id_persona`),
  CONSTRAINT `fk_sign_up_step_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
