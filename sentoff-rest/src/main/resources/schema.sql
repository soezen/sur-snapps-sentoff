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

