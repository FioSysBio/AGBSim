INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (1, 0.1, 4, 1, 1.29, 'iJO1366TRFBA', 'E. coli', 0.61, 500, 3500, 'BACILLI', 8000, 360);
INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (2, 0.1, 4, 1.25, 2.5, 'iYO844', 'B. subtilis', 0.76, 500, 3500, 'BACILLI', 8000, 360);
INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (3, 0.1, 4, 3.1, 93, 'iMM904', 'S. cervisiae', 2.92, 500, 3500, 'BACILLI', 8000, 360);
INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (4, 0.1, 4, 2.4, 48.4, 'iBB814', 'S. stipitis', 2.4, 500, 3500, 'BACILLI', 8000, 360);
INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (5, 0.1, 4, 4.75, 13.3, 'iBif452', 'B. adolescentis', 0.9, 500, 3500, 'BACILLI', 8000, 360);
INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (6, 0.1, 4, 5.5, 8, 'iFap484', 'F. prausnitzii', 0.65, 500, 3500, 'BACILLI', 8000, 360);
INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (7, 0.1, 4, 1.7, 3.4, 'iJL432', 'C. beijerinckii', 0.75, 500, 3500, 'BACILLI', 8000, 360);
INSERT INTO `acbm-service`.cell (id, amount, eat_radius, length, mass, mathlab_file, name, radius, scale, search_radius, shape, speed, survive_time) VALUES (8, 0.1, 4, 1.25, 2.5, 'iYO844', 'Custon 111', 0.76, 500, 3500, 'COCCI', 8000, 360);

INSERT INTO `acbm-service`.metabolite (id, amount, molar_mass, name, speed, uptake_upper_bound) VALUES (1, 10, 180, 'Glucose', 8000, 1000);
INSERT INTO `acbm-service`.metabolite (id, amount, molar_mass, name, speed, uptake_upper_bound) VALUES (2, 10, 60, 'Acetate', 8000, 1000);
INSERT INTO `acbm-service`.metabolite (id, amount, molar_mass, name, speed, uptake_upper_bound) VALUES (3, 10, 88, 'Butyrate', 8000, 1000);
INSERT INTO `acbm-service`.metabolite (id, amount, molar_mass, name, speed, uptake_upper_bound) VALUES (4, 10, 46, 'Formate', 8000, 1000);
INSERT INTO `acbm-service`.metabolite (id, amount, molar_mass, name, speed, uptake_upper_bound) VALUES (5, 10, 46, 'Ethanol', 8000, 1000);
INSERT INTO `acbm-service`.metabolite (id, amount, molar_mass, name, speed, uptake_upper_bound) VALUES (6, 10, 92, 'Glycerol', 8000, 1000);
INSERT INTO `acbm-service`.metabolite (id, amount, molar_mass, name, speed, uptake_upper_bound) VALUES (7, 10, 300, 'Custom', 8000, 1000);

INSERT INTO `acbm-service`.simulation (id, description, environment_depth, environment_length, environment_width, is_local_feed_simulation, metabolite_scale, time_limit, time_step, metabolite_scale_mult) VALUES (1, 'Default', 400, 1000, 1, false, 10, 1, 400, 5);
INSERT INTO `acbm-service`.simulation (id, description, environment_depth, environment_length, environment_width, is_local_feed_simulation, metabolite_scale, time_limit, time_step, metabolite_scale_mult) VALUES (2, 'Testes', 400, 1000, 1, true, 1200, 1, 400, null);
