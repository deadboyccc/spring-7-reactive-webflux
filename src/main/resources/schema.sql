CREATE TABLE IF NOT EXISTS beer
(
    id
    SERIAL
    PRIMARY
    KEY,
    beer_name
    VARCHAR
(
    255
),
    beer_style VARCHAR
(
    255
),
    upc VARCHAR
(
    25
),
    quantity_on_hand INTEGER,
    price NUMERIC
(
    10,
    2
),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS customer
(
    id
    SERIAL
    PRIMARY
    KEY,
    customer_name
    VARCHAR
(
    255
),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
    );