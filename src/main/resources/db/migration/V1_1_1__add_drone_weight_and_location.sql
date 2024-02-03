ALTER TABLE model
ADD COLUMN weight_kg DECIMAL(7,2) unsigned,
DROP column manufacturer;

ALTER TABLE drone
ADD COLUMN latitude DECIMAL(8,6),
Add COLUMN longitude DECIMAL(9,6),
ADD COLUMN weight_kg DECIMAL(7,2) unsigned;