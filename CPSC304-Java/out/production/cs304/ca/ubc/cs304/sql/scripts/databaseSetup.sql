CREATE TABLE FarmingEquipments(
                                    tool_name varchar2(20),
                                    tool_usage varchar2(50),
                                    manufacturer varchar2(20) ,
                                    tool_id integer PRIMARY KEY,
                                    price integer,
                                    farm_id integer not null,
                                    FOREIGN KEY (farm_id)
                                        REFERENCES FarmAndSupervisorModel(farm_id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE
);
CREATE TABLE FarmAndSupervisor(
                                    area varchar2(20),
                                    supervisor_name varchar2(50),
                                    location varchar2(20),
                                    farm_id integer PRIMARY KEY,
                                    phone_number integer unique ,
                                    salary integer,
                                    bonus integer,
                                    supervisor_id integer not null unique
);



INSERT INTO FarmAndSupervisor VALUES ("NA", "Jack", "Surrey",  "1231231234", 1,11,10000,10);
INSERT INTO FarmAndSupervisor VALUES ("Asia", "Tom", "Beijing", "3213214321",2,22,10000,10);
INSERT INTO FarmingEquipments VALUES ("Rake", "unknown", "Structron", 2, 23, 1);
INSERT INTO FarmingEquipments VALUES ("Shovel", "dig", "Structron", 1, 45, 2);

