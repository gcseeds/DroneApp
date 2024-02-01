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
   name varchar(255) NOT NULL UNIQUE,
   sensor_type enum ('visible_light',
                    'infared',
                    'lidar',
                    'radar',
                    'millimeter',
                    'acoustic') NOT NULL,
   model_id int NOT NULL
);