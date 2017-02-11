drop table if exists accounts;

create table accounts (
	id integer not null auto_increment,
	name varchar(100) not null,
	owner_id integer not null,
	balance numeric(12,2) not null default 0,
	primary key (id)
);

-- STORES
CREATE TABLE stores (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  type VARCHAR(50),
  PRIMARY KEY (id)
);

-- STORE LOCATIONS
CREATE TABLE store_locations (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(200) NOT NULL,
	store_id INTEGER NOT NULL,
	street VARCHAR(100),
	number VARCHAR(10),
	zip_code VARCHAR(5),
	city VARCHAR(100) NOT NULL,
	country VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

alter TABLE store_locations
    add CONSTRAINT store_locations_fk_store
    FOREIGN KEY (store_id) REFERENCES stores (id);

-- PURCHASES
CREATE TABLE purchases (
	id INTEGER NOT NULL AUTO_INCREMENT,
	DATE DATE NOT NULL,
	amount NUMERIC(12,2) NOT NULL,
	store_location_id INTEGER,
	PRIMARY KEY (id)
);

ALTER TABLE purchases
    ADD CONSTRAINT purchases_fk_store_location
    FOREIGN KEY (store_location_id) REFERENCES store_locations (id);
    
drop table if exists messages;
    
-- MESSAGE_LOGS
CREATE TABLE messages (
	id INTEGER NOT NULL AUTO_INCREMENT,
	date timestamp NOT NULL,
	method VARCHAR(15) not null,
	uri varchar(100) not null,
	payload text not null,
	primary key (id)
);