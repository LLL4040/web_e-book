drop database ebook;
create database if not exists ebook;
use ebook;

/* users包括普通用户和管理员 */
/* status: 0->被禁，1->未被禁 */
/* identity: 0->普通用户，1->管理员 */
CREATE TABLE IF NOT EXISTS users (
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `email` VARCHAR(20) NOT NULL,
    `status` INT DEFAULT 1,
    `identity` INT DEFAULT 0,
    PRIMARY KEY (`username`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/* 每本书的信息 */
CREATE TABLE IF NOT EXISTS books (
    `bookname` VARCHAR(50) NOT NULL,
    `author` VARCHAR(50) NOT NULL,
    `cover` VARCHAR(100) NOT NULL,
    `ISBN` VARCHAR(13) NOT NULL,
    `amount` INT UNSIGNED NOT NULL,
    `price` DECIMAL(10 , 2 ) NOT NULL,
    PRIMARY KEY (`ISBN`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/* 将用户、订单、单品联系起来 */
CREATE TABLE IF NOT EXISTS orders (
    `order_id` INT UNSIGNED AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `time` DATETIME NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`username`)
        REFERENCES users (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/* 存放订单里的一个条目详细信息 */
CREATE TABLE IF NOT EXISTS orderitems (
    `order_id` INT UNSIGNED,
    `item_id` INT UNSIGNED AUTO_INCREMENT,
    `ISBN` VARCHAR(13) NOT NULL,
    `number` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`item_id`),
    FOREIGN KEY (`order_id`)
        REFERENCES orders (`order_id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`ISBN`)
        REFERENCES books (`ISBN`)
        ON DELETE CASCADE ON UPDATE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
