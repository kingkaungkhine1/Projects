
  DROP DATABASE if exists CarDealership;
  
  CREATE DATABASE CarDealership;
  
  Use CarDealership;
  
  CREATE TABLE `Vehicle` (
	`VehicleId` INT NOT NULL AUTO_INCREMENT,
	`VIN` varchar(40) NOT NULL,
	`MakeId` INT NOT NULL,
	`ModelId` INT NOT NULL,
	`Color` varchar(30) NOT NULL,
	`Type` varchar(10) NOT NULL,
	`BodyStyle` varchar(20) NOT NULL,
	`Transmission` varchar(30) NOT NULL,
	`Interior` varchar(30) NOT NULL,
	`Year` INT NOT NULL,
	`MSRP` INT NOT NULL,
	`SalePrice` INT NOT NULL,
	`Mileage` INT(40) NOT NULL,
	`Description` varchar(300) NOT NULL,
	`Photo` varchar(100) NOT NULL,
	`Featured` BOOLEAN NOT NULL,
    `Sold` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`VehicleId`)
);

CREATE TABLE `Make` (
	`Make` varchar(30) NOT NULL,
	`MakeId` INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`MakeId`)
);

CREATE TABLE `Special` (
	`SpecialId` INT NOT NULL AUTO_INCREMENT,
	`Title` varchar(100) NOT NULL,
	`Description` varchar(1000),
	PRIMARY KEY (`SpecialId`)
);

CREATE TABLE `Model` (
	`ModelId` INT NOT NULL AUTO_INCREMENT,
	`MakeId` INT NOT NULL,
    `Model` VARCHAR(40),
	PRIMARY KEY (`ModelId`)
);

CREATE TABLE `ContactMessage` (
	`MessageId` INT NOT NULL AUTO_INCREMENT,
	`Name` varchar(50) NOT NULL,
	`Email` varchar(100),
	`Phone` varchar(20),
	`Message` varchar(500) NOT NULL,
	PRIMARY KEY (`MessageId`)
);

CREATE TABLE `User` (
	`UserId` INT NOT NULL AUTO_INCREMENT,
	`FirstName` varchar(40) NOT NULL,
	`LastName` varchar(40) NOT NULL,
	`Email` varchar(100) NOT NULL,
	`Password` varchar(100) NOT NULL,
	`Role` varchar(20) NOT NULL,
	PRIMARY KEY (`UserId`)
);

CREATE TABLE `Purchase` (
	`PurchaseId` INT NOT NULL AUTO_INCREMENT,
	`Name` varchar(50) NOT NULL,
	`Phone` varchar(20),
	`Email` varchar(100),
	`Street1` varchar(100) NOT NULL,
	`Street2` varchar(100),
	`State` varchar(5) NOT NULL,
	`City` varchar(20) NOT NULL,
	`ZipCode` varchar(20) NOT NULL,
	`PurchaseDate` DATE NOT NULL,
	`PurchaseType` varchar(20) NOT NULL,
	`UserId` INT NOT NULL,
	`VehicleId` INT NOT NULL,
	`PurchasePrice` INT NOT NULL,
	PRIMARY KEY (`PurchaseId`)
);

-- ALTER TABLE Purchase
-- ALTER PurchaseDate SET DEFAULT GETDATE();
--  ALTER TABLE `Purchase` MODIFY COLUMN PURCHASEDATE DATE NOT NULL DEFAULT DATE;
 
ALTER TABLE `Vehicle` ADD FOREIGN KEY fk_Vehicle_Make(MakeId) REFERENCES Make(MakeId);

ALTER TABLE `Vehicle` ADD  FOREIGN KEY fk_Vehicle_Model(ModelId) REFERENCES Model(ModelId);

ALTER TABLE `Model` ADD FOREIGN KEY fk_Model_Make(MakeId) REFERENCES Make(MakeId);

ALTER TABLE `Purchase` ADD FOREIGN KEY  fk_Purchase_User(UserId) REFERENCES User(UserId);

ALTER TABLE `Purchase` ADD FOREIGN KEY fk_Purchase_Vehicle(VehicleId) REFERENCES Vehicle(VehicleId);

insert into MAKE (`Make`, makeId) VALUES
("TEMP make", 1);

insert into MODEL(modelId, makeId, `Model`) VALUES
(1, 1, "TEMP model");

insert into contactmessage(messageId, `Name`, `Email`, `Phone`, `Message`) VALUES
(1, "Temp Name Msg", "temp email msg", "temp phone msg", "temp msg msg");

insert into user(userId, `FirstName`, `LastName`, `Email`, `Password`, `Role`) values
(1, "F temp name", "L temp name", "email temp", "password temp", "role temp");

