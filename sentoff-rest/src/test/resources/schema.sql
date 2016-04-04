CREATE TABLE h_purchased_products (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	purchase_id VARCHAR(40),
	product_id VARCHAR(40),
	unit_price DOUBLE,
	amount DOUBLE,
	PRIMARY KEY (id,rev)
);

CREATE TABLE authorities (
	user_id VARCHAR(40) NOT NULL,
	authority VARCHAR(25) NOT NULL,
	PRIMARY KEY (user_id,authority)
);

CREATE TABLE h_purchases (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	DATE DATE,
	store_location_id VARCHAR(40),
	PRIMARY KEY (id,rev)
);

CREATE TABLE revinfo (
	REV INT NOT NULL,
	REVTSTMP BIGINT,
	PRIMARY KEY (REV)
);

CREATE TABLE product_types (
	id VARCHAR(40) NOT NULL,
	name VARCHAR(200) NOT NULL,
	category_id VARCHAR(40) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE entities (
	id VARCHAR(40) NOT NULL,
	name VARCHAR(100) NOT NULL,
	owned BIT NOT NULL,
	shared BIT NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_store_locations (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	name VARCHAR(200),
	store_id VARCHAR(40),
	street VARCHAR(100),
	number VARCHAR(10),
	zip_code VARCHAR(5),
	city VARCHAR(100),
	country VARCHAR(100),
	PRIMARY KEY (id,rev)
);

CREATE TABLE purchases (
	id VARCHAR(40) NOT NULL,
	DATE DATE NOT NULL,
	store_location_id VARCHAR(40) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_purchase_payment (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	purchase_id VARCHAR(40) NOT NULL,
	PRIMARY KEY (rev,id,purchase_id)
);

CREATE TABLE purchased_products (
	id VARCHAR(40) NOT NULL,
	purchase_id VARCHAR(40),
	product_id VARCHAR(40) NOT NULL,
	unit_price DOUBLE NOT NULL,
	amount DOUBLE NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE store_locations (
	id VARCHAR(40) NOT NULL,
	name VARCHAR(200) NOT NULL,
	store_id VARCHAR(40) NOT NULL,
	street VARCHAR(100) NOT NULL,
	number VARCHAR(10) NOT NULL,
	zip_code VARCHAR(5) NOT NULL,
	city VARCHAR(100) NOT NULL,
	country VARCHAR(100) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE events (
	id VARCHAR(40) NOT NULL,
	type VARCHAR(20) NOT NULL,
	tms DATETIME NOT NULL,
	user_id VARCHAR(40) NOT NULL,
	subject_id VARCHAR(40),
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_stores (
	rev INT NOT NULL,
	revtype TINYINT,
	ID VARCHAR(40) NOT NULL,
	name VARCHAR(100),
	type VARCHAR(50),
	PRIMARY KEY (ID,rev)
);

CREATE TABLE categories (
	id VARCHAR(40) NOT NULL,
	name VARCHAR(200) NOT NULL,
	parent VARCHAR(40),
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_entities (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	name VARCHAR(100) NOT NULL,
	owned BIT NOT NULL,
	shared BIT NOT NULL,
	PRIMARY KEY (id,rev)
);

CREATE TABLE store_products (
	id VARCHAR(40) NOT NULL,
	code VARCHAR(20) NOT NULL,
	product_id VARCHAR(40) NOT NULL,
	store_id VARCHAR(40) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_users (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(250) NOT NULL,
	name VARCHAR(100) NOT NULL,
	enabled BIT NOT NULL,
	entity_id VARCHAR(40) NOT NULL,
	PRIMARY KEY (id,rev)
);

CREATE TABLE h_authorities (
	rev INT NOT NULL,
	revtype TINYINT,
	user_id VARCHAR(40) NOT NULL,
	authority VARCHAR(25) NOT NULL,
	PRIMARY KEY (user_id,authority,rev)
);

CREATE TABLE payments (
	id VARCHAR(40) NOT NULL,
	purchase_id VARCHAR(40),
	account_id VARCHAR(40) NOT NULL,
	amount DOUBLE NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE tokens (
	id VARCHAR(40) NOT NULL,
	value VARCHAR(40) NOT NULL,
	entity_id VARCHAR(40) NOT NULL,
	email VARCHAR(250) NOT NULL,
	expiration_date TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	status VARCHAR(20) NOT NULL,
	type VARCHAR(20) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_accounts (
	rev INT NOT NULL,
	revtype TINYINT,
	ID VARCHAR(40) NOT NULL,
	name VARCHAR(100),
	type VARCHAR(50),
	balance DOUBLE NOT NULL,
	user_id VARCHAR(40),
	PRIMARY KEY (ID,rev)
);

CREATE TABLE h_product_types (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	name VARCHAR(200),
	category_id VARCHAR(40),
	PRIMARY KEY (id,rev)
);

CREATE TABLE users (
	id VARCHAR(40) NOT NULL,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(250) NOT NULL,
	name VARCHAR(100) NOT NULL,
	enabled BIT NOT NULL,
	entity_id VARCHAR(40) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_store_products (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	code VARCHAR(20),
	product_id VARCHAR(40),
	store_id VARCHAR(40),
	PRIMARY KEY (id,rev)
);

CREATE TABLE products (
	id VARCHAR(40) NOT NULL,
	name VARCHAR(200) NOT NULL,
	type VARCHAR(40) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_categories (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	name VARCHAR(200),
	parent VARCHAR(40),
	PRIMARY KEY (id,rev)
);

CREATE TABLE h_payments (
	rev INT NOT NULL,
	revtype TINYINT,
	id VARCHAR(40) NOT NULL,
	purchase_id VARCHAR(40),
	account_id VARCHAR(40),
	amount DOUBLE,
	PRIMARY KEY (id,rev)
);

CREATE TABLE accounts (
	id VARCHAR(40) NOT NULL,
	name VARCHAR(100) NOT NULL,
	type VARCHAR(50) NOT NULL,
	balance DOUBLE NOT NULL,
	user_id VARCHAR(40) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE h_products (
	rev INT NOT NULL,
	retype TINYINT,
	ID VARCHAR(40) NOT NULL,
	name VARCHAR(200),
	type VARCHAR(40),
	PRIMARY KEY (ID,rev)
);

CREATE TABLE h_purchase_purchasedproduct (
	rev INT NOT NULL,
	revtype TINYINT,
	ID VARCHAR(40) NOT NULL,
	purchase_id VARCHAR(40) NOT NULL,
	PRIMARY KEY (rev,ID,purchase_id)
);

CREATE TABLE stores (
	id VARCHAR(40) NOT NULL,
	name VARCHAR(100) NOT NULL,
	type VARCHAR(50) NOT NULL,
	version INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE INDEX FK_TOKENS_ENTITY_ID ON tokens (entity_id ASC);

CREATE UNIQUE INDEX UQ_STORE_PRODUCTS_CODE_PER_STORE ON store_products (store_id ASC);

CREATE INDEX FK_EVENTS_USER_ID ON events (user_id ASC);

CREATE INDEX FK_PRODUCTS_TYPE ON products (type ASC);

CREATE UNIQUE INDEX UQ_STORE_NAME ON stores (name ASC);

CREATE INDEX FK_PAYMENTS_ACCOUNT ON payments (account_id ASC);

CREATE INDEX FK_STORE_PRODUCTS_PRODUCT ON store_products (product_id ASC);

CREATE INDEX FK_PURCHASED_PRODUCTS_PRODUCT ON purchased_products (product_id ASC);

CREATE UNIQUE INDEX UQ_STORE_PRODUCTS_CODE_PER_STORE ON store_products (code ASC);

CREATE INDEX FK_PURCHASED_PRODUCTS_PURCHASE ON purchased_products (purchase_id ASC);

CREATE INDEX FK_ACCOUNTS_ENTITY_ID ON accounts (user_id ASC);

CREATE INDEX FK_STORES_STORE_LOCATION ON store_locations (store_id ASC);

CREATE UNIQUE INDEX UQ_TOKEN_EMAIL ON tokens (email ASC);

CREATE INDEX FK_PURCHASES ON purchases (store_location_id ASC);

CREATE INDEX FK_PRODUCT_TYPES_CATEGORY ON product_types (category_id ASC);

CREATE INDEX FK_PAYMENTS_PURCHASE ON payments (purchase_id ASC);

CREATE INDEX FK_CATEGORIES_PARENT ON categories (parent ASC);

CREATE INDEX FK_USERS_ENTITY_ID ON users (entity_id ASC);

