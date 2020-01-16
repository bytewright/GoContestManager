DROP TABLE IF EXISTS gcm.contests;
CREATE TABLE gcm.contests
(
    id                       bigint(20)   NOT NULL AUTO_INCREMENT,
    last_modified            DATETIME     DEFAULT NOW(),
    short_identifier         varchar(10)  NOT NULL,
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
    PRIMARY KEY (id),
    UNIQUE (short_identifier)
);
