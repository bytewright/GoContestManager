CREATE SCHEMA IF NOT EXISTS gcm;
DROP TABLE IF EXISTS gcm.role_permissions;
DROP TABLE IF EXISTS gcm.user_permissions;
DROP TABLE IF EXISTS gcm.user_roles;
DROP TABLE IF EXISTS gcm.pages;
DROP TABLE IF EXISTS gcm.permissions;
DROP TABLE IF EXISTS gcm.roles;
DROP TABLE IF EXISTS gcm.users;

DROP TABLE IF EXISTS gcm.organizers;
DROP TABLE IF EXISTS gcm.players;
DROP TABLE IF EXISTS gcm.helpers;
DROP TABLE IF EXISTS gcm.contests;
CREATE TABLE gcm.users
(
    id           bigint(20)   NOT NULL AUTO_INCREMENT,
    display_name varchar(255) NOT NULL,
    first_name   varchar(50)  NOT NULL,
    last_name    varchar(50)  NOT NULL,
    email_addr   varchar(200) NOT NULL,
    pw           varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (display_name)
);
CREATE TABLE gcm.roles
(
    id   bigint(20)   NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);
CREATE TABLE gcm.permissions
(
    id   bigint(20)   NOT NULL AUTO_INCREMENT,
    perm varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (perm)
);
CREATE TABLE gcm.role_permissions
(
    role_id       bigint(20) NOT NULL,
    permission_id bigint(20) NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES gcm.roles (id),
    FOREIGN KEY (permission_id) REFERENCES gcm.permissions (id)
);
CREATE TABLE gcm.user_permissions
(
    user_id       bigint(20) NOT NULL,
    permission_id bigint(20) NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    FOREIGN KEY (user_id) REFERENCES gcm.users (id),
    FOREIGN KEY (permission_id) REFERENCES gcm.permissions (id)
);
CREATE TABLE gcm.user_roles
(
    role_id bigint(20) NOT NULL,
    user_id bigint(20) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES gcm.users (id),
    FOREIGN KEY (role_id) REFERENCES gcm.roles (id)
);
CREATE TABLE gcm.contests
(
    id                       bigint(20)   NOT NULL AUTO_INCREMENT,
    last_modified            DATETIME     DEFAULT NOW(),
    short_identifier         varchar(30)  NOT NULL,
    contest_name             varchar(100) NOT NULL,
    currency_unit_code       varchar(4)   NOT NULL,
    discount_general_amount  double       DEFAULT 0,
    discount_club_amount     double       DEFAULT 0,
    discount_pre_reg_amount  double       DEFAULT 0,
    fee_start_amount         double       DEFAULT 10,
    fee_breakfast_amount     double       DEFAULT 0,
    round_count              integer      DEFAULT 5,
    time_zone                varchar(255) NOT NULL,
    start_utc_time           DATETIME     DEFAULT NOW(),
    end_utc_time             DATETIME     DEFAULT NOW(),
    starting_fee_freed_ranks varchar(255) DEFAULT '',
    street                   varchar(255) DEFAULT NULL,
    street_num               varchar(255) DEFAULT NULL,
    city                     varchar(255) DEFAULT NULL,
    name                     varchar(255) DEFAULT NULL,
    extra_data               text         DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (short_identifier)
);

CREATE TABLE gcm.organizers
(
    id            bigint(20)   NOT NULL AUTO_INCREMENT,
    last_modified DATETIME     DEFAULT NOW(),
    email         varchar(100) DEFAULT NULL,
    first_name    varchar(100) NOT NULL,
    last_name     varchar(100) NOT NULL,
    phone_number  varchar(255) NOT NULL,
    street        varchar(255) DEFAULT NULL,
    street_num    varchar(255) DEFAULT NULL,
    city          varchar(255) DEFAULT NULL,
    loc_name      varchar(255) DEFAULT NULL,
    contest_id    bigint(20)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_OrganizersToContest FOREIGN KEY (contest_id) REFERENCES gcm.contests (id)
);
CREATE TABLE gcm.players
(
    id                        bigint(20)   NOT NULL AUTO_INCREMENT,
    last_modified             DATETIME     DEFAULT NOW(),
    email                     varchar(100) DEFAULT NULL,
    first_name                varchar(100) NOT NULL,
    last_name                 varchar(100) NOT NULL,
    age                       integer      DEFAULT 10,
    city                      varchar(255) NOT NULL,
    go_club                   varchar(255) NOT NULL,
    country                   varchar(255) NOT NULL,
    go_rank                   varchar(10)  NOT NULL,
    payment_status            varchar(20)  NOT NULL,
    registration_form_message text         DEFAULT NULL,
    needs_sleep_over          boolean      DEFAULT false,
    attends_breakfast         boolean      DEFAULT false,
    go_club_member            boolean      DEFAULT false,
    discounted                boolean      DEFAULT false,
    female                    boolean      DEFAULT false,
    senior                    boolean      DEFAULT false,
    student                   boolean      DEFAULT false,
    u_10                      boolean      DEFAULT false,
    first_contest             boolean      DEFAULT false,
    seminar_member            boolean      DEFAULT false,
    contest_id                bigint(20)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_PlayersToContest FOREIGN KEY (contest_id) REFERENCES gcm.contests (id)
);
CREATE TABLE gcm.helpers
(
    id            bigint(20)   NOT NULL AUTO_INCREMENT,
    last_modified DATETIME     DEFAULT NOW(),
    email         varchar(100) DEFAULT NULL,
    first_name    varchar(100) NOT NULL,
    last_name     varchar(100) NOT NULL,
    phone_number  varchar(255) NOT NULL,
    street        varchar(255) DEFAULT NULL,
    street_num    varchar(255) DEFAULT NULL,
    city          varchar(255) DEFAULT NULL,
    loc_name      varchar(255) DEFAULT NULL,
    contest_id    bigint(20)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_HelperToContest FOREIGN KEY (contest_id) REFERENCES gcm.contests (id)
);

CREATE TABLE gcm.pages
(
    id                  bigint(20)   NOT NULL AUTO_INCREMENT,
    last_modified       DATETIME     DEFAULT NOW(),
    page_url_path       varchar(200) NOT NULL,
    display_in_navi     boolean      default false,
    anchor_name         varchar(200) DEFAULT NULL,
    page_class          varchar(200) DEFAULT NULL,
    required_permission bigint(20)   DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_PageToPermission FOREIGN KEY (required_permission) REFERENCES gcm.permissions (id)
);