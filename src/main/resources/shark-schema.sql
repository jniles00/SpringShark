DROP TABLE IF EXISTS `shark` CASCADE;
CREATE TABLE `shark` (
            `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
            `age` INTEGER CHECK (age<=20 AND age>=2),
            `name` VARCHAR(255) NOT NULL UNIQUE,
            `habitat` VARCHAR(255),
            `type` VARCHAR(255)
            );