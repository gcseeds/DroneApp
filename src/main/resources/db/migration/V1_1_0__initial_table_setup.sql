CREATE TABLE IF NOT EXISTS DRONE (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    registration varchar(255) NOT NULL UNIQUE,
    model_id int,
    status enum ('ready','repairing','on_mission') NOT NULL,
    created_date DATETIME,
    modified_date DATETIME
);

CREATE TABLE IF NOT EXISTS MODEL (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE,
    manufacturer varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS SENSOR (
   id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name varchar(255) NOT NULL,
   sensor_type enum ('visible_light',
                    'infared',
                    'lidar',
                    'radar',
                    'millimeter',
                    'acoustic') NOT NULL,
   drone_id int NOT NULL
);

ALTER TABLE drone
    ADD FOREIGN KEY fk_drone_model (model_id) references model(id) ON DELETE RESTRICT;

ALTER TABLE sensor
    ADD FOREIGN KEY fk_sensor_drone (drone_id) references drone(id) ON DELETE CASCADE;

ALTER TABLE sensor
    ADD UNIQUE uidx_sensor_name_drone_id (name, drone_id);

CREATE INDEX idx2_sensor_type ON sensor(sensor_type);