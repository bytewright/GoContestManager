DROP TABLE IF EXISTS gcm.users;

CREATE TABLE gcm.users
(
    id           bigint(20)   NOT NULL AUTO_INCREMENT,
    display_name varchar(255) NOT NULL,
    first_name   varchar(255) DEFAULT NULL,
    last_name    varchar(255) DEFAULT NULL,
    pw           varchar(255) NOT NULL,
    roles        varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (display_name)
);

INSERT INTO gcm.users
VALUES (0, 'admin', null, null, 'secret', 'admin');
INSERT INTO gcm.users
VALUES (1, 'admin2', null, null, 'secret', 'admin');
INSERT INTO gcm.users
VALUES (2, 'guest', null, null, 'guest', null);
