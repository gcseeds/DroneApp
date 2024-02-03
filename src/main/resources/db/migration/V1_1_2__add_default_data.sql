INSERT INTO model (name, weight_kg)
VALUES ('Trumpeter Swan', 12.7),
       ('Wandering Albatross', 11.9),
       ('Mute Swan', 11.87),
       ('Mallard', 1.0),
       ('Least Grebe', 0.11);

INSERT INTO drone (registration, model_id, status, latitude, longitude, weight_kg)
    VALUES ('Trumpeter1', 1, 'ready', 33.753746, -84.386330, 12.7),
    ('Trumpeter2', 1, 'ready', 33.753757, -84.385330, 12.7),
    ('Albatross1', 2, 'repairing', 33.753757, -84.385330, 11.9),
    ('Albatross2', 2, 'ready', 33.753757, -84.385330, 11.9),
    ('Mute1', 3, 'ready', 33.753757, -84.385330, 11),
    ('Mute2', 3, 'ready', 33.753757, -84.385330, 11),
    ('Mallard1', 4, 'ready', 33.753757, -84.385330, 1.1),
    ('Mallard12', 4, 'on_mission', 33.9, -85.0, 1.0),
    ('Grebe1', 5, 'on_mission', 34.7, -85.0, 0.11),
    ('Grebe2', 5, 'ready', 33.753746, -84.386330, 0.11);

INSERT INTO sensor (name, sensor_type, drone_id)
VALUES ('TrumpeterVision', 'visible_light', 1),
       ('TrumpeterVision', 'visible_light', 2),
       ('AbatrossRadar', 'radar', 3),
       ('AlbatrossVision', 'visible_light', 3),
       ('AbatrossRadar', 'radar', 4),
       ('AlbatrossVision', 'visible_light', 4),
       ('MuteSwanAcoustic', 'acoustic', 5),
       ('MuteSwanLidar', 'lidar', 5),
       ('MuteSwanAcoustic', 'acoustic', 6),
       ('MuteSwanLidar', 'lidar', 6),
       ('MallardVision', 'visible_light', 7),
       ('MallardNight', 'infared', 7),
       ('MallardVision', 'visible_light', 8),
       ('MallardNight', 'infared', 8),
       ('GrebeRadar', 'radar', 9),
       ('GrebeRadar', 'radar', 10);