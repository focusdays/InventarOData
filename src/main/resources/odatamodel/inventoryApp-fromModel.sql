SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `inventorydb` ;
CREATE SCHEMA IF NOT EXISTS `inventorydb` DEFAULT CHARACTER SET utf8 ;
USE `inventorydb` ;

-- -----------------------------------------------------
-- Table `inventorydb`.`commodity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventorydb`.`commodity` ;

CREATE TABLE IF NOT EXISTS `inventorydb`.`commodity` (
  `commodityID` INT(11) NOT NULL AUTO_INCREMENT,
  `commodityTitle` VARCHAR(255) NULL,
  `commodityType` INT(2) NULL DEFAULT NULL,
  `roomType` INT(2) NULL DEFAULT NULL,
  `commodityPrice` DECIMAL(10,2) NULL DEFAULT NULL,
  `commodityPicture` VARCHAR(255) NOT NULL,
  `mutationTimestamp` DATETIME NOT NULL,
  `commodity_commodityID` INT(11) NULL,
  PRIMARY KEY (`commodityID`),
  INDEX `fk_commodity_commodity1_idx` (`commodity_commodityID` ASC),
  UNIQUE INDEX `commodityID_UNIQUE` (`commodityID` ASC),
  CONSTRAINT `fk_commodity_commodity1`
    FOREIGN KEY (`commodity_commodityID`)
    REFERENCES `inventorydb`.`commodity` (`commodityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventorydb`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventorydb`.`person` ;

CREATE TABLE IF NOT EXISTS `inventorydb`.`person` (
  `personID` VARCHAR(200) NOT NULL,
  `totalPriceInventories` DECIMAL(10,2) NOT NULL,
  `personName` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`personID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventorydb`.`device`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventorydb`.`device` ;

CREATE TABLE IF NOT EXISTS `inventorydb`.`device` (
  `deviceID` INT(11) NOT NULL,
  `deviceName` VARCHAR(255) NULL,
  `deviceType` VARCHAR(255) NULL DEFAULT NULL,
  `person_personID` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`deviceID`, `person_personID`),
  INDEX `fk_device_person_idx` (`person_personID` ASC),
  UNIQUE INDEX `deviceID_UNIQUE` (`deviceID` ASC),
  CONSTRAINT `fk_device_person`
    FOREIGN KEY (`person_personID`)
    REFERENCES `inventorydb`.`person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventorydb`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventorydb`.`location` ;

CREATE TABLE IF NOT EXISTS `inventorydb`.`location` (
  `locationID` INT(11) NOT NULL,
  `locationType` INT(2) NOT NULL,
  `locationTitle` VARCHAR(255) NULL DEFAULT NULL,
  `geoLocation_latitude` DECIMAL(10,6) NOT NULL,
  `geoLocation_longitude` DECIMAL(10,6) NOT NULL,
  `geoLocation_accuracy` DECIMAL(6,2) NULL,
  `locationAddress_land` VARCHAR(255) NULL DEFAULT NULL,
  `locationAddress_postalCodeCity` VARCHAR(5) NULL DEFAULT NULL,
  `locationAddress_streetHouseNr` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`locationID`),
  UNIQUE INDEX `locationID_UNIQUE` (`locationID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventorydb`.`inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventorydb`.`inventory` ;

CREATE TABLE IF NOT EXISTS `inventorydb`.`inventory` (
  `inventoryID` INT(11) NOT NULL AUTO_INCREMENT,
  `inventoryTitle` VARCHAR(255) NOT NULL,
  `inventoryTotalPrice` DECIMAL(10,2) NOT NULL,
  `inventoryType` INT(2) NOT NULL,
  `language` INT(2) NOT NULL,
  `mutationTimestamp` DATETIME NOT NULL,
  `currency` INT(2) NOT NULL,
  `person_personID` VARCHAR(200) NOT NULL,
  `location_locationID` INT(11) NOT NULL,
  PRIMARY KEY (`inventoryID`, `person_personID`, `location_locationID`),
  INDEX `fk_inventory_person1_idx` (`person_personID` ASC),
  INDEX `fk_inventory_location1_idx` (`location_locationID` ASC),
  UNIQUE INDEX `inventoryID_UNIQUE` (`inventoryID` ASC),
  CONSTRAINT `fk_inventory_person1`
    FOREIGN KEY (`person_personID`)
    REFERENCES `inventorydb`.`person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_location1`
    FOREIGN KEY (`location_locationID`)
    REFERENCES `inventorydb`.`location` (`locationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventorydb`.`person_has_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventorydb`.`person_has_location` ;

CREATE TABLE IF NOT EXISTS `inventorydb`.`person_has_location` (
  `person_personID` VARCHAR(200) NOT NULL,
  `location_locationID` INT(11) NOT NULL,
  INDEX `fk_person_has_location_location1_idx` (`location_locationID` ASC),
  INDEX `fk_person_has_location_person1_idx` (`person_personID` ASC),
  CONSTRAINT `fk_person_has_location_person1`
    FOREIGN KEY (`person_personID`)
    REFERENCES `inventorydb`.`person` (`personID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_location_location1`
    FOREIGN KEY (`location_locationID`)
    REFERENCES `inventorydb`.`location` (`locationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventorydb`.`inventory_has_commodity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventorydb`.`inventory_has_commodity` ;

CREATE TABLE IF NOT EXISTS `inventorydb`.`inventory_has_commodity` (
  `inventory_inventoryID` INT(11) NOT NULL,
  `inventory_person_personID` VARCHAR(200) NOT NULL,
  `inventory_location_locationID` INT(11) NOT NULL,
  `commodity_commodityID` INT(11) NOT NULL,
  INDEX `fk_inventory_has_commodity_commodity1_idx` (`commodity_commodityID` ASC),
  INDEX `fk_inventory_has_commodity_inventory1_idx` (`inventory_inventoryID` ASC, `inventory_person_personID` ASC, `inventory_location_locationID` ASC),
  CONSTRAINT `fk_inventory_has_commodity_inventory1`
    FOREIGN KEY (`inventory_inventoryID` , `inventory_person_personID` , `inventory_location_locationID`)
    REFERENCES `inventorydb`.`inventory` (`inventoryID` , `person_personID` , `location_locationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_has_commodity_commodity1`
    FOREIGN KEY (`commodity_commodityID`)
    REFERENCES `inventorydb`.`commodity` (`commodityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
