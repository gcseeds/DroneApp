ALTER TABLE drone
    ADD FOREIGN KEY (model_id) references model(id);

ALTER TABLE sensor
    ADD FOREIGN KEY (drone_id) references drone(id);

CREATE INDEX idx_sensor_type ON sensor(sensor_type);