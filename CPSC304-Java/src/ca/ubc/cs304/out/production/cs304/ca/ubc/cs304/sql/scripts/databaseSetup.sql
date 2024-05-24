drop table Monitored_Farms;
drop table Monitored_Farms2;
drop table Monitored_Farms3;
drop table Farming_Equipment1;
drop table Farming_Equipment2;
drop table Farming_Equipment3;
drop table Working_Farmers;
drop table Working_Farmers2;
drop table FruitBasket;
drop table Crop;
drop table Produce;
drop table ProduceStatus;
drop table Soil_Type1;
drop table Soil_Type2;
drop table Farm_Soil;
drop table Crop_Soil;
drop table Sold1;
drop table Sold_Fruits;
drop table Distribution_Channel;
drop table Online;
drop table Offline;

CREATE TABLE Monitored_Farms (farm_id VARCHAR(20) PRIMARY KEY,
                              location VARCHAR(40) UNIQUE,
                              area FLOAT, supervisior_id VARCHAR(20) NOT NULL UNIQUE,
                              phone_number VARCHAR(20)  UNIQUE, supervisior_name VARCHAR(20),
                              salary INTEGER);

CREATE TABLE Working_Farmers (farmer_id VARCHAR(20) PRIMARY KEY,
                              farmer_name VARCHAR(20),
                              farmer_phone_number VARCHAR(20) NOT NULL,
                              farmer_type VARCHAR(20),farm_id VARCHAR(20) NOT NULL,
                              farmer_hours FLOAT,
                              FOREIGN KEY (farm_id) REFERENCES Monitored_Farms(farm_id)
                                  ON DELETE CASCADE );

CREATE TABLE Crop (crop_name VARCHAR(20) PRIMARY KEY ,
                   growth_duration_days INTEGER NOT NULL);

CREATE TABLE Produce (crop_name varchar(20),
                      farm_id varchar(20),
                      start_month varchar(10),
                      start_year integer,
                      quantity integer,
                      primary key (crop_name,farm_id,start_month,start_year),
                      foreign key (crop_name) references Crop(crop_name) on delete cascade,
                      foreign key (farm_id) references Monitored_Farms(farm_id) on delete cascade);

CREATE TABLE ProduceStatus (crop_name varchar(20), farm_id varchar(20),
                            start_month varchar(10),
                            start_year integer,
                            status varchar(20) NOT NULL ,
                            primary key (crop_name,farm_id,start_month,start_year),
                            foreign key (crop_name,farm_id,start_month,start_year)
                                references Produce (crop_name,farm_id,start_month,start_year)
                                on delete cascade);



CREATE TABLE Monitored_Farms2 (
                                  salary INTEGER PRIMARY KEY,
                                  bonus INTEGER
);
CREATE TABLE Monitored_Farms3 (
                                  area FLOAT PRIMARY KEY,
                                  budget INTEGER
);
CREATE TABLE Farming_Equipment1 (
                                    tool_id varchar(20) PRIMARY KEY,
                                    tool_name varchar(20),
                                    manufacturer varchar(30),
                                    farm_id varchar(20) NOT NULL,
                                    FOREIGN KEY (farm_id) REFERENCES Monitored_Farms(farm_id)
                                        ON DELETE CASCADE
);
CREATE TABLE Farming_Equipment2 (
                                    tool_name varchar(20),
                                    manufacturer varchar(30),
                                    price FLOAT,
                                    PRIMARY KEY (tool_name, manufacturer)
);
CREATE TABLE Farming_Equipment3 (
                                    tool_name varchar(20),
                                    tool_usage varchar(20),
                                    PRIMARY KEY (tool_name)
);
CREATE TABLE Working_Farmers2 (
                                  farmer_type varchar(20),
                                  farm_id varchar(20),
                                  hourly_wage FLOAT,
                                  PRIMARY KEY (farmer_type, farm_id),
                                  FOREIGN KEY (farm_id) REFERENCES Monitored_Farms(farm_id)
                              ON delete cascade );
CREATE TABLE FruitBasket (
                             fruit_name varchar(20),
                             farm_id varchar(20),
                             quantity INTEGER,
                             PRIMARY KEY (fruit_name, farm_id),
                             FOREIGN KEY (farm_id) REFERENCES Monitored_Farms(farm_id)
                                 ON DELETE CASCADE);


CREATE TABLE Soil_Type1 (
                            soil_id varchar(20) PRIMARY KEY,
                            soil_name varchar(20) UNIQUE,
                            texture CHAR(20)
);
CREATE TABLE Soil_Type2 (
                            texture CHAR(20) PRIMARY KEY,
                            pH DECIMAL(10,2)
);
CREATE TABLE Farm_Soil (
                           farm_id varchar(20),
                           soil_id varchar(20),
                           PRIMARY KEY (farm_id, soil_id),
                           FOREIGN KEY (farm_id) REFERENCES Monitored_Farms(farm_id)
                               ON DELETE CASCADE,
                           FOREIGN KEY (soil_id) REFERENCES Soil_Type1(soil_id)
                               ON DELETE CASCADE
);
CREATE TABLE Crop_Soil (
                           crop_name varchar(20),
                           soil_id varchar(20),
                           PRIMARY KEY (crop_name, soil_id),
                           FOREIGN KEY (crop_name) REFERENCES Crop (crop_name)
                               ON DELETE CASCADE,
                           FOREIGN KEY (soil_id) REFERENCES Soil_Type1(soil_id)
                               ON delete CASCADE
);

CREATE TABLE Sold1 (
                       channel_id varchar(20),
                       crop_name varchar(20),
                       quantity INTEGER,
                       price FLOAT,
                       PRIMARY KEY (channel_id, crop_name),
                       FOREIGN KEY (channel_id) REFERENCES Distribution_Channel(channel_id)
                           ON DELETE CASCADE
);
CREATE TABLE Sold_fruits (
                             channel_id varchar(20),
                             fruit_name varchar(20),
                             farm_id varchar(20),
                             quantity INTEGER,
                             price FLOAT,
                             PRIMARY KEY (channel_id, fruit_name, farm_id),
                             FOREIGN KEY (channel_id) REFERENCES Distribution_Channel(channel_id)
                                 ON DELETE CASCADE,
                                 FOREIGN KEY (fruit_name,farm_id) REFERENCES FruitBasket (fruit_name,farm_id)
                                 ON DELETE CASCADE
);


CREATE TABLE Distribution_Channel (
                                      channel_id varchar(20) PRIMARY KEY,
                                      channel_name varchar(20)
);

CREATE TABLE Online (
                        channel_id varchar(20) PRIMARY KEY,
                        url VARCHAR(20) UNIQUE,
                        FOREIGN KEY (channel_id) REFERENCES Distribution_Channel(channel_id)
                            ON DELETE CASCADE
);
CREATE TABLE Offline (
                         channel_id varchar(20) PRIMARY KEY,
                         address varchar(40) UNIQUE,
                         FOREIGN KEY (channel_id) REFERENCES Distribution_Channel(channel_id)
                             ON DELETE CASCADE);

