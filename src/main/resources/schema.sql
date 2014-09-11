DROP TABLE IF EXISTS `Pizza_Base`;
  
CREATE TABLE `Pizza_Base` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description MEDIUMTEXT NOT NULL,
  active bool NOT NULL,
  price DECIMAL(5,2) NOT NULL
);
CREATE INDEX `idx_Pizza_Base_name` ON `Pizza_Base`(`name`);


DROP TABLE IF EXISTS `Topping`;

CREATE TABLE `Topping` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description MEDIUMTEXT NOT NULL,
  active bool NOT NULL,
  price DECIMAL(5,2) NOT NULL
);
CREATE INDEX `idx_Topping_name` ON `Topping`(`name`);