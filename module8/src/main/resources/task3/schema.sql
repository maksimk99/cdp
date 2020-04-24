DROP TABLE IF EXISTS `like`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `friendship`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`         bigint(11)  NOT NULL AUTO_INCREMENT,
    `name`       varchar(45) NOT NULL,
    `surname`    varchar(45) NOT NULL,
    `birth_date` date        NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `friendship`
(
    `user_id1`   bigint(11)   NOT NULL,
    `user_id2`   bigint(11)   NOT NULL,
    `start_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`user_id1`, `user_id2`),
    CONSTRAINT `fk_friendship_1` FOREIGN KEY (`user_id1`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_friendship_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `post`
(
    `id`               bigint(11)   NOT NULL AUTO_INCREMENT,
    `user_id`          bigint(11)   NOT NULL,
    `text`             mediumtext   NOT NULL,
    `publication_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`, `user_id`),
    CONSTRAINT `fk_post_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `like`
(
    `post_id` bigint(11)   NOT NULL,
    `user_id` bigint(11)   NOT NULL,
    `date`    timestamp(6) NULL DEFAULT NULL,
    PRIMARY KEY (`post_id`, `user_id`),
    CONSTRAINT `fk_like_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_like_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);