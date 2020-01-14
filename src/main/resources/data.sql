DROP TABLE IF EXISTS gcm.users;
CREATE TABLE gcm.users
(
    id           bigint(20)   NOT NULL AUTO_INCREMENT,
    display_name varchar(255) NOT NULL,
    first_name   varchar(255) DEFAULT NULL,
    last_name    varchar(255) DEFAULT NULL,
    pw           varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (display_name)
);
DROP TABLE IF EXISTS gcm.roles;
CREATE TABLE gcm.roles
(
    id   bigint(20)   NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);
DROP TABLE IF EXISTS gcm.permissions;
CREATE TABLE gcm.permissions
(
    id   bigint(20)   NOT NULL AUTO_INCREMENT,
    perm varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (perm)
);
DROP TABLE IF EXISTS gcm.role_permissions;
CREATE TABLE gcm.role_permissions
(
    role_id       bigint(20) NOT NULL,
    permission_id bigint(20) NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES gcm.roles (id),
    FOREIGN KEY (permission_id) REFERENCES gcm.permissions (id)
);
DROP TABLE IF EXISTS gcm.user_permissions;
CREATE TABLE gcm.user_permissions
(
    user_id       bigint(20) NOT NULL,
    permission_id bigint(20) NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    FOREIGN KEY (user_id) REFERENCES gcm.users (id),
    FOREIGN KEY (permission_id) REFERENCES gcm.permissions (id)
);
DROP TABLE IF EXISTS gcm.user_roles;
CREATE TABLE gcm.user_roles
(
    role_id bigint(20) NOT NULL,
    user_id bigint(20) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES gcm.users (id),
    FOREIGN KEY (role_id) REFERENCES gcm.roles (id)
);

INSERT INTO gcm.roles (id, name)
VALUES (1, 'admin');
INSERT INTO gcm.roles (id, name)
VALUES (2, 'contestmanager');
INSERT INTO gcm.roles (id, name)
VALUES (3, 'player');

INSERT INTO gcm.permissions (id, perm)
VALUES (1, '*:*');
INSERT INTO gcm.permissions (id, perm)
VALUES (2, 'contest:create');
INSERT INTO gcm.permissions (id, perm)
VALUES (3, 'contest:view');

INSERT INTO gcm.users
VALUES (1, 'admin', null, null, 'secret');
INSERT INTO gcm.users
VALUES (2, 'orga1', null, null, 'secret');
INSERT INTO gcm.users
VALUES (3, 'guest', null, null, 'guest');


INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (1, 1);
INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (2, 2);
INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (2, 3);
INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (3, 3);

INSERT INTO gcm.user_roles (role_id, user_id)
VALUES (1, 1);
INSERT INTO gcm.user_roles (role_id, user_id)
VALUES (2, 2);
INSERT INTO gcm.user_roles (role_id, user_id)
VALUES (3, 3);