create database debs1;
show tables from debs1;
use debs1;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `business_name` varchar(255) DEFAULT NULL,
  `business_address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `about_me` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`user_id`) references `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` bigint NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `office_name` varchar(255) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vendor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` bigint NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `office_name` varchar(255) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
  
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `expense` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `liability` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `asset` (
   `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `partner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `purchase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `vendor_name` varchar(255) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`account_id`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `account` varchar(255) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  `debit` int NOT NULL,
  `credit` int NOT NULL,
  -- `maturity_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_transaction` (
  `account_id` int NOT NULL,
  `transaction_id` int NOT NULL,
  PRIMARY KEY (`account_id`, `transaction_id`),
  foreign key (`account_id`) references `account`(`id`),
  foreign key (`transaction_id`) references `transaction`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bill` ( 
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL, -- sales bill (customer details) OR purchase bill (vendor details)
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `bill_number` long NOT NULL,
  `date` datetime NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` bigint NOT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `total` bigint DEFAULT NULL, -- discount for each product will be given manually and calculated at runtime, it will not be saved in db
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TRIGGER IF EXISTS `debs1`.`user_AFTER_INSERT`;
DELIMITER $$
USE `debs1`$$
CREATE DEFINER = CURRENT_USER TRIGGER `debs1`.`user_AFTER_INSERT` AFTER INSERT ON `user` FOR EACH ROW
BEGIN
insert into account (`type`) values (`partner`);
insert into partner (`name`, `amount`) values (`debs1`.`user`.`username`,0);
END$$
DELIMITER ;


DELETE FROM `account_transaction` WHERE `transaction_id`=63;
DELETE FROM `transaction` WHERE `id`=63;
DELETE FROM `account` WHERE `id`>30;
alter table `partner` add column `description` varchar(1023) DEFAULT NULL;
-- PROCEDURES --

-- drop procedure UserLoginProc
DELIMITER $$
CREATE PROCEDURE `UserLoginProc` (in `uname` varchar(255), in `pswd` varchar(255), out `output` int)
BEGIN
if exists (select * from `user` where `username`=`uname` and `password`=`pswd`) then
	begin
		set `output`=1; -- login success
	end;
    
	else
    begin
		set `output`=0; -- login failed
	end;
END if;
END$$
DELIMITER ;

CALL `UserLoginProc`('aaah', 'aaash', @output);
select 'Returned value:' , @output;
select * from user;

-- drop procedure UserSignupProc
DELIMITER $$
CREATE PROCEDURE `UserSignupProc` (in `uname` varchar(255), in `pswd` varchar(255), out `output` int)
BEGIN
if exists (select * from `user` where `username`=`uname`) then-- only one username can be used per account
	begin
		set `output`=0; -- User already exists
	end;
    
	else
    begin
		insert into `user` (`username`, `password`, `name`) values(`uname`, `pswd`, `uname`);
        insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "partner" 
				from `user` 
					where `username`=`uname`;
		CALL AddPartner (`uname`, '', (select `user`.`id` from `user` where `user`.`id`=(select max(`user`.`id`) from `user`)), @output);
        if exists (select @output) then
        begin
			set `output`=1; -- signup success
		end;
        
        else
        begin
			set `output`=-1; -- signup success, failure while adding partner
        end;
        END if;
	end;
END if;
END$$
DELIMITER ;

CALL `UserSignupProc`('aa', 'aa', @output);
select 'Returned value:' , @output;
select * from `36 partner aa`;

-- this procedure will add the partner by populating account and partner tables. It will also create a new {"partner " + "name provided"} table.
-- drop procedure AddPartner
DELIMITER $$
CREATE PROCEDURE `AddPartner` (in `aname` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
if exists (select * from `partner` where `name`=`aname` and `description`=`desc`) then
	begin
		set `output`=0; -- partner already exists
	end;
    
	else
    begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "partner" 
				from `user`
					where `id`=`uid`;
		insert into `partner` (`account_id`, `name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `aname`, `desc`);
		-- nap -> name of partner
		set @nap=concat('create table `', uid, ' partner ', aname, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`partner_id` int NOT NULL,
			`name` varchar(255) NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`partner_id`) references `partner`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nap;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
END if;
END$$
DELIMITER ;

CALL AddPartner ('aa2', 'a a 2', 36, @output);
select 'Returned value:' , @output;
select * from `36 partner aa2`;

-- this procedure will add the asset by populating account and asset tables. It will also create a new {"uid" + " asset " + " nameProvided"} table.
-- drop procedure AddAsset
DELIMITER $$
CREATE PROCEDURE `AddAsset` (in `aname` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
if exists (select * from `asset` where `name`=`aname` and `description`=`desc`) then
	begin
		set `output`=0; -- failure
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "asset" 
				from `user`
					where `id`=`uid`;
		insert into `asset` (`account_id`, `name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `aname`, `desc`);
		-- nao -> name of asset
		set @nao=concat('create table `', uid, ' asset ', aname, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`asset_id` int NOT NULL,
			`name` varchar(255) NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`asset_id`) references `asset`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nao;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
END if;
END$$
DELIMITER ;

CALL AddAsset ('furniture', 'for office', 36, @output);
select 'Returned value:' , @output;
select * from `36 asset furniture`;

-- server side will get the account id for each transaction through this procedure
-- drop procedure GetAccountId
DELIMITER $$
CREATE PROCEDURE `GetAccountId` (in `acc` varchar(255), in `atype` varchar(16), in `uid` int)
BEGIN
	if not exists (select `id` from `user` where `id`=`uid`) then
    begin
		select 0; -- user does not exist
	end;
    
    else begin
		-- toa -> type of account
		set @toa=concat('select `account_id` from `', atype, '` join `account` on `', atype,'`.`account_id` = `account`.`id` where `account`.`user_id`="', uid, '" and `name`="', acc, '"');
		PREPARE stmt FROM @toa;
		EXECUTE stmt; -- nothing will be displayed if either account name `acc` or account type `atype` are wrong/not present.
		DEALLOCATE PREPARE stmt;
    end; 
	END IF;
END$$
DELIMITER ;

CALL GetAccountId ('aa', 'partner', 36);
CALL GetAccountId ('cash in hand', 'asset', 36);
CALL GetAccountId ('Marker', 'product', 36);
CALL GetAccountId ('vx', 'vendor', 36);

-- then provide that account id with paramters to this procedure in order to save transaction
-- drop procedure AddTransaction
-- DELIMITER $$
-- CREATE PROCEDURE `AddTransaction` (in `uid` int, in `atype` varchar(16), in `acc` varchar(255), in `dsc` varchar(1023), in `dr` int, in `cr` int, in `aid` int, out `output` int) -- it would be called after checking: total debit = total credit
-- BEGIN
-- 	if not exists (select `id` from `user` where `id`=`uid`) then
--     begin
-- 		set `output`=0; -- user does not exist
-- 	end;
--     
--     else begin
-- 		insert into `transaction` (`date`, `account`, `description`, `debit`, `credit`)
-- 			select now(), `acc`, `dsc`, `dr`, `cr`;
-- 		insert into `account_transaction` values(
-- 			(select `aid`),
-- 			(select `transaction`.`id` from `transaction` where `transaction`.`id` = (select max(`transaction`.`id`) from `transaction`)));-- gets latest/maximum transaction id from transaction table
-- 		-- toa -> type of account
-- 		set @toa=concat('insert into `', uid, ' ', atype, ' ', acc, '` (`', atype, '_id`, `name`, `description`, `debit`, `credit`, `date`) values (
-- 			(select `id` from `', atype, '` where `account_id`=', aid, '), "', acc, '", "', dsc, '", "', dr, '", "', cr, '" , (select now()))');
-- 		PREPARE stmt FROM @toa;
-- 		EXECUTE stmt; -- nothing will be displayed if either account name `acc` or account type `atype` are wrong/not present.
-- 		DEALLOCATE PREPARE stmt;
--         set `output`=1; -- success
-- 	end;
-- 	END IF;
-- END$$
-- DELIMITER ;

-- CALL AddTransaction (36, 'asset', 'furniture', 'sofa set', 2000, 0, 24, @output);select 'Returned value:' , @output;
-- CALL AddTransaction (36, 'asset', 'cash in bank', 'c i b 1k', 0, 1000, 20, @output);select 'Returned value:' , @output;
-- CALL AddTransaction (36, 'asset', 'cash in hand', 'c i h 1k', 0, 1000, 21, @output);select 'Returned value:' , @output;
-- select 'Returned value:' , @output;

-- this procedure will add the product by populating account and product tables.
-- drop procedure AddProduct
DELIMITER $$
CREATE PROCEDURE `AddProduct` (in `pname` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
if exists (select * from `product` where `name`=`pname` and `description`=`desc`) then
	begin
		set `output`=0; -- failure
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "product" 
				from `user`
					where `id`=`uid`;
		insert into `product` (`account_id`, `name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `pname`, `desc`);
		set `output`=1; -- success
	end;
END if;
END$$
DELIMITER ;

CALL AddProduct ('Pen', 'ink black', 36, @output);
select 'Returned value:' , @output;

-- this procedure will add the vendor by populating account and vendor tables. It will also create a new {"uid" + " vendor" + " nameProvided"} table.
-- drop procedure AddVendor
DELIMITER $$
CREATE PROCEDURE `AddVendor` (in `vname` varchar(255), in `address` varchar(255), in `email` varchar(255), in `phone` bigint, in `website` varchar(255), in `officeName` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
if exists (select * from `vendor` where `name`=`vname` and `description`=`desc`) then
	begin
		set `output`=0; -- vendor already exists
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "vendor" 
				from `user`
					where `id`=`uid`;
		insert into `vendor` (`account_id`, `name`, `address`, `email`, `phone`, `website`, `office_name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `vname`, `address`, `email`, `phone`, `website`, `officeName`, `desc`);
		-- nav -> name of vendor
		set @nav=concat('create table `', uid, ' vendor ', vname, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`vendor_id` int NOT NULL,
			`name` varchar(255) NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`vendor_id`) references `vendor`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nav;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
END if;
END$$
DELIMITER ;

CALL AddVendor('vx', 'defence lhr', '', 12345, '', 'vx office', 'vx descriptionn', 36, @output); select 'Returned value:' , @output;

-- drop procedure AddPurchase
DELIMITER $$
CREATE PROCEDURE `AddPurchase` (in `vendorName` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
	if not exists (select `id` from `user` where `id`=`uid`) then
    begin
		set `output`=0; -- user does not exist
	end;
    
	elseif exists (select * from `purchase` where `vendor_name`=`vendorName` and `description`=`desc`) then
	begin
		set `output`=-1; -- purchase account of this vendor already exists
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "purchase" 
				from `user`
					where `id`=`uid`;
		insert into `purchase` (`account_id`, `vendor_name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `vendorName`, `desc`);
		-- nap -> name of purchase
		set @nap=concat('create table `', uid, ' purchase ', vendorName, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`purchase_id` int NOT NULL,
			`product_name` varchar(255) NOT NULL,
            `product_id` int NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
            `quantity` int NOT NULL,
            `cost_price` int NOT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`purchase_id`) references `purchase`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nap;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
	END if;
END$$
DELIMITER ;

CALL AddPurchase ('vx', 'neighbor', 36, @output); select 'Returned value:' , @output;

-- extra parameters like productName, quantity, costPrice are only for "purchase" and "sale", they would be null/-1 in case of normal transaction.
-- drop procedure AddTransaction
DELIMITER $$
CREATE PROCEDURE `AddTransaction` (in `uid` int, in `atype` varchar(16), in `atype_name` varchar(255), in `dsc` varchar(1023), in `dr` int, in `cr` int, in `productName` varchar(255), in `product_id` int, in `quantity` int, in `price` int, in `aid` int, out `output` int)
BEGIN
	if not exists (select `id` from `user` where `id`=`uid`) then
    begin
		set `output`=0; -- user does not exist
	end;
    
	-- elseif exists (select * from `purchase` where `vendor_name`=`atype_name` and `description`=`dsc`) then
-- 	begin
-- 		set `output`=-1; -- purchase account of this vendor already exists
-- 	end;
    
    else begin
		insert into `transaction` (`date`, `account`, `description`, `debit`, `credit`)
			select now(), `atype_name`, `dsc`, `dr`, `cr`;
		insert into `account_transaction` values(
			(select `aid`),
			(select `transaction`.`id` from `transaction` where `transaction`.`id` = (select max(`transaction`.`id`) from `transaction`)));-- gets latest/maximum transaction id from transaction table
		
		if `quantity`>0 and `price`>0 then
		begin
			if `atype`='purchase' then
            begin
				-- toa -> type of account
				set @toa=concat('insert into `', uid, ' ', atype, ' ', atype_name, '` (`', atype, '_id`, `product_name`, `product_id`, `description`, `quantity`, `cost_price`, `debit`, `credit`, `date`) values (
					(select `id` from `', atype, '` where `account_id`=', aid, '), "', productName, '", "', product_id, '", "', dsc, '", "', quantity, '", "', price, '", "', dr, '", "', cr, '" , (select now()))');
				PREPARE stmt FROM @toa;
				EXECUTE stmt; -- nothing will be displayed if either account name `acc` or account type `atype` are wrong/not present.
				DEALLOCATE PREPARE stmt;
				set `output`=2; -- success adding transaction of purchase of product from vendor
			end;
            
            elseif `atype`='sale' then
            begin
				-- toa -> type of account
				set @toa=concat('insert into `', uid, ' ', atype, ' ', atype_name, '` (`', atype, '_id`, `product_name`, `product_id`, `description`, `quantity`, `sale_price`, `debit`, `credit`, `date`) values (
					(select `id` from `', atype, '` where `account_id`=', aid, '), "', productName, '", "', product_id, '", "', dsc, '", "', quantity, '", "', price, '", "', dr, '", "', cr, '" , (select now()))');
				PREPARE stmt FROM @toa;
				EXECUTE stmt; -- nothing will be displayed if either account name `acc` or account type `atype` are wrong/not present.
				DEALLOCATE PREPARE stmt;
				set `output`=3; -- success adding transaction of sale of product to customer
			end; 
            end if;
		end;
        
        elseif `quantity`=-1 and `price`=-1 then
        begin
			-- toa -> type of account
			set @toa=concat('insert into `', uid, ' ', atype, ' ', atype_name, '` (`', atype, '_id`, `name`, `description`, `debit`, `credit`, `date`) values (
				(select `id` from `', atype, '` where `account_id`=', aid, '), "', atype_name, '", "', dsc, '", "', dr, '", "', cr, '" , (select now()))');
			PREPARE stmt FROM @toa;
			EXECUTE stmt; -- nothing will be displayed if either account name `acc` or account type `atype` are wrong/not present.
			DEALLOCATE PREPARE stmt;
            set `output`=1; -- success adding transaction of account
        end;
        end if;
	end;
	END if;
END$$
DELIMITER ;

-- transaction 3
CALL AddTransaction (36, 'purchase', 'vx', '', 200, 0, 'Marker', 1, 10, 20, 36, @output); select 'Returned value:' , @output;
CALL AddTransaction (36, 'purchase', 'vx', '', 300, 0, 'Pen', 2, 20, 15, 36, @output); select 'Returned value:' , @output;
CALL AddTransaction (36, 'vendor', 'vx', '', 0, 500, '', -1, -1, -1, 35, @output); select 'Returned value:' , @output;
-- transaction 4
CALL AddTransaction (36, 'sale', 'cx', '', 0, 150, 'Marker', 1, 5, 30, 38, @output); select 'Returned value:' , @output;
CALL AddTransaction (36, 'sale', 'cx', '', 0, 200, 'Pen', 2, 5, 20, 38, @output); select 'Returned value:' , @output;
CALL AddTransaction (36, 'customer', 'cx', '', 350, 0, '', -1, -1, -1, 37, @output); select 'Returned value:' , @output;
-- transaction 5
CALL AddTransaction (36, 'liability', 'consultant ali', 'c a', 0, 1000, '', -1, -1, -1, 40, @output); select 'Returned value:' , @output;
CALL AddTransaction (36, 'expense', 'business name registeration', 'b n r', 1000, 0, '', -1, -1, -1, 41, @output); select 'Returned value:' , @output;
-- transaction 6
CALL AddTransaction (36, 'vendor', 'vx', '', 400, 0, '', -1, -1, -1, 35, @output); select 'Returned value:' , @output;
CALL AddTransaction (36, 'asset', 'cash in bank', '', 0, 400, '', -1, -1, -1, 20, @output); select 'Returned value:' , @output;
-- transaction 7
CALL AddTransaction (36, 'partner', 'aa', '', 1000, 0, '', -1, -1, -1, 18, @output); select 'Returned value:' , @output;
CALL AddTransaction (36, 'asset', 'cash in hand', '', 0, 1000, '', -1, -1, -1, 21, @output); select 'Returned value:' , @output;


-- this procedure will add the customer by populating account and customer tables. It will also create a new {"uid" + " customer" + " nameProvided"} table.
-- drop procedure AddCustomer
DELIMITER $$
CREATE PROCEDURE `AddCustomer` (in `cname` varchar(255), in `address` varchar(255), in `email` varchar(255), in `phone` bigint, in `website` varchar(255), in `officeName` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
	if exists (select * from `customer` where `name`=`cname` and `description`=`desc`) then
	begin
		set `output`=0; -- customer already exists
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "customer" 
				from `user`
					where `id`=`uid`;
		insert into `customer` (`account_id`, `name`, `address`, `email`, `phone`, `website`, `office_name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `cname`, `address`, `email`, `phone`, `website`, `officeName`, `desc`);
		-- nac -> name of customer
		set @nac=concat('create table `', uid, ' customer ', cname, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`customer_id` int NOT NULL,
			`name` varchar(255) NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`customer_id`) references `customer`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nac;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
END if;
END$$
DELIMITER ;

CALL AddCustomer('cx', 'johar town lhr', '', 3452, '', 'cx office', 'cx descriptionn', 36, @output); select 'Returned value:' , @output;

-- drop procedure AddSale
DELIMITER $$
CREATE PROCEDURE `AddSale` (in `customerName` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
	if not exists (select `id` from `user` where `id`=`uid`) then
    begin
		set `output`=0; -- user does not exist
	end;
    
	elseif exists (select * from `sale` where `customer_name`=`customerName` and `description`=`desc`) then
	begin
		set `output`=-1; -- sale account of this customer already exists
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "sale" 
				from `user`
					where `id`=`uid`;
		insert into `sale` (`account_id`, `customer_name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `customerName`, `desc`);
		-- nap -> name of purchase
		set @nap=concat('create table `', uid, ' sale ', customerName, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`sale_id` int NOT NULL,
			`product_name` varchar(255) NOT NULL,
            `product_id` int NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
            `quantity` int NOT NULL,
            `sale_price` int NOT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`sale_id`) references `sale`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nap;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
	END if;
END$$
DELIMITER ;

CALL AddSale ('cx', 'pese wapis milna mushkil', 36, @output); select 'Returned value:' , @output;

-- this procedure will add the liability by populating account and liability tables. It will also create a new {"uid" + " liability " + " nameProvided"} table.
-- drop procedure AddLiability
DELIMITER $$
CREATE PROCEDURE `AddLiability` (in `lname` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
if exists (select * from `asset` where `name`=`lname` and `description`=`desc`) then
	begin
		set `output`=0; -- failure
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "liability" 
				from `user`
					where `id`=`uid`;
		insert into `liability` (`account_id`, `name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `lname`, `desc`);
		-- nal -> name of liability
		set @nal=concat('create table `', uid, ' liability ', lname, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`liability_id` int NOT NULL,
			`name` varchar(255) NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`liability_id`) references `liability`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nal;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
END if;
END$$
DELIMITER ;

CALL AddLiability ('consultant ali', 'for business name registeration in tax department', 36, @output); select 'Returned value:' , @output;

-- this procedure will add the expense by populating account and expense tables. It will also create a new {"uid" + " expense " + " nameProvided"} table.
-- drop procedure AddExpense
DELIMITER $$
CREATE PROCEDURE `AddExpense` (in `ename` varchar(255), in `desc` varchar(1023), in `uid` int, out `output` int)
BEGIN
if exists (select * from `expense` where `name`=`ename` and `description`=`desc`) then
	begin
		set `output`=0; -- failure
	end;
    
	else begin
		insert into `account` (`user_id`, `type`) 
			select `user`.`id`, "expense" 
				from `user`
					where `id`=`uid`;
		insert into `expense` (`account_id`, `name`, `description`) values(
			(select `account`.`id` from `account` where `account`.`id`=(select max(`account`.`id`) from `account`)), -- gets latest/maximum account id from account table
            `ename`, `desc`);
		-- nae -> name of expense
		set @nae=concat('create table `', uid, ' expense ', ename, '`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`expense_id` int NOT NULL,
			`name` varchar(255) NOT NULL,
			`description` varchar(1023) DEFAULT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
			`date` datetime NOT NULL,
            PRIMARY KEY (`id`),
            foreign key (`expense_id`) references `expense`(`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci');
		PREPARE stmt FROM @nae;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		set `output`=1; -- success
	end;
END if;
END$$
DELIMITER ;

CALL AddExpense ('business name registeration', 'consultant ali', 36, @output); select 'Returned value:' , @output;

-- drop procedure GetAccountBalance
DELIMITER $$
CREATE PROCEDURE `GetAccountBalance` (`acc_name` varchar(255), out `cr_dr` varchar(16), out `balance` int)
BEGIN
	set @drs=concat("set @dr_sum=(select sum(debit) from `", acc_name, "`)");
    PREPARE stmt FROM @drs;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
    set @crs=concat("set @cr_sum=(select sum(credit) from `", acc_name, "`)");
	PREPARE stmt FROM @crs;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
    if(@dr_sum > @cr_sum) then
	begin
		set `balance`=@dr_sum-@cr_sum;
        set `cr_dr`="debit";
    end;
	elseif(@dr_sum < @cr_sum) then begin
		set `balance`=@cr_sum-@dr_sum;
        set `cr_dr`="credit";
    end;
    else begin -- if neither debit nor credit record found
		set `balance`=0;
        set `cr_dr`="credit";
    end;
	END if;
END$$
DELIMITER ;

CALL GetAccountBalance ('36 partner aa2', @cr_dr, @balance); select @cr_dr, @balance;

-- drop procedure ShowTrialBalance
DELIMITER $$
CREATE PROCEDURE `ShowTrialBalance` (in `uid` int, out `output` int)
BEGIN
	if not exists (select `id` from `user` where `id`=`uid`) then
    begin
		set `output`=0; -- user does not exist
	end;    
    
	else begin
		create table `trial_balance`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`acc_name` varchar(255) NOT NULL,
			`debit` int NOT NULL,
			`credit` int NOT NULL,
            PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
        
        create table `t1`( 
			`id` int NOT NULL AUTO_INCREMENT,
			`table_name` varchar(255) NOT NULL,
            PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
        
        insert into t1 (`table_name`) (SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES where TABLE_NAME like concat(uid, ' %'));
		
        set @i=1;
        set @n=(select max(id) from t1);
        
		WHILE @i<=@n DO
	      CALL GetAccountBalance ((select `table_name` from t1 where `id`=@i), @cr_dr, @balance);
          if @cr_dr="credit" then begin
			insert into `trial_balance` (`acc_name`, `debit`, `credit`) value((select `table_name` from t1 where `id`=@i), 0, @balance);
          end;
          elseif @cr_dr="debit" then begin
			insert into `trial_balance` (`acc_name`, `debit`, `credit`) value((select `table_name` from t1 where `id`=@i), @balance, 0);
          end;
          end if;
          set @i = @i + 1;
		END WHILE;
        
        select * from `trial_balance`;
		set `output`=1; -- success
        
        drop table `trial_balance`;
        drop table `t1`;
	end;
	END if;
END$$
DELIMITER ;

CALL ShowTrialBalance (36 , @output);