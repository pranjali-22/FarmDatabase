
INSERT INTO Monitored_Farms VALUES('FARM001', '1050 West 14th Ave', 100.0, 'SUP001', '555-0101', 'John Doe', 70000);
INSERT INTO Monitored_Farms VALUES('FARM002', '2020 East 7th Ave', 150.0, 'SUP002', '555-0102', 'Jane Smith', 75000);
INSERT INTO Monitored_Farms VALUES('FARM003', '3080 North 3rd St', 120.2, 'SUP003', '555-0103', 'Emily White', 72000);
INSERT INTO Monitored_Farms VALUES('FARM004', '4500 South 12th St', 200.50, 'SUP004', '555-0104', 'Michael Brown', 68000);
INSERT INTO Monitored_Farms VALUES('FARM005', '5190 West 20th Ave', 180.88, 'SUP005', '555-0105', 'Alex Johnson', 71000);


INSERT INTO Monitored_Farms2 (salary, bonus) VALUES (70000, 5000);
INSERT INTO Monitored_Farms2 (salary, bonus) VALUES (75000, 5500);
INSERT INTO Monitored_Farms2 (salary, bonus) VALUES (72000, 5200);
INSERT INTO Monitored_Farms2 (salary, bonus) VALUES (68000, 4800);
INSERT INTO Monitored_Farms2 (salary, bonus) VALUES (71000, 5100);


INSERT INTO Monitored_Farms3 (area, budget) VALUES ('100', 100000);
INSERT INTO Monitored_Farms3 (area, budget) VALUES ('150', 150000);
INSERT INTO Monitored_Farms3 (area, budget) VALUES ('120', 120000);
INSERT INTO Monitored_Farms3 (area, budget) VALUES ('200', 200000);
INSERT INTO Monitored_Farms3 (area, budget) VALUES ('180', 180000);

INSERT INTO Farming_Equipment1 VALUES ('TOOL001', 'Tractor', 'John Deere', 'FARM001');
INSERT INTO Farming_Equipment1 VALUES ('TOOL002', 'Plough', 'Caterpillar', 'FARM002');
INSERT INTO Farming_Equipment1 VALUES ('TOOL003', 'Harvester', 'Kubota', 'FARM003');
INSERT INTO Farming_Equipment1 VALUES ('TOOL004', 'Sprayer', 'Case IH', 'FARM004');
INSERT INTO Farming_Equipment1 VALUES ('TOOL005', 'Seeder', 'New Holland', 'FARM005');


INSERT INTO Farming_Equipment2 VALUES ('Tilling', 'John Deere', 25000.00);
INSERT INTO Farming_Equipment2 VALUES  ('Ploughing', 'Caterpillar', 15000.00);
INSERT INTO Farming_Equipment2 VALUES ('Harvesting', 'Kubota', 35000.00);
INSERT INTO Farming_Equipment2 VALUES ('Spraying', 'Case IH', 20000.00);
INSERT INTO Farming_Equipment2 VALUES ('Seeding', 'New Holland', 18000.00);


INSERT INTO Farming_Equipment3 (tool_name, tool_usage) VALUES ('Tractor', 'Tilling');
INSERT INTO Farming_Equipment3 (tool_name, tool_usage) VALUES ('Plough', 'Ploughing');
INSERT INTO Farming_Equipment3 (tool_name, tool_usage) VALUES ('Harvester', 'Harvesting');
INSERT INTO Farming_Equipment3 (tool_name, tool_usage) VALUES ('Sprayer', 'Spraying');
INSERT INTO Farming_Equipment3 (tool_name, tool_usage) VALUES ('Seeder', 'Seeding');

INSERT INTO Working_Farmers VALUES ('FARMER001', 'Tom Hardy', '555-0201', 'Junior Farmer', 'FARM001', 40.2);
INSERT INTO Working_Farmers VALUES ('FARMER002', 'Natalie Portman', '555-0202', 'Senior Farmer', 'FARM002', 45);
INSERT INTO Working_Farmers VALUES('FARMER003', 'Chris Evans', '555-0203', 'Junior Farmer', 'FARM003', 38);
INSERT INTO Working_Farmers VALUES('FARMER004', 'Scarlett Johansson', '555-0204', 'Supervisor', 'FARM004', 50.5);
INSERT INTO Working_Farmers VALUES ('FARMER005', 'Mark Ruffalo', '555-0205', 'Senior Farmer', 'FARM005', 42);

INSERT INTO Working_Farmers2  VALUES ('Junior Farmer', 'FARM001', 20);
INSERT INTO Working_Farmers2 VALUES ('Senior Farmer', 'FARM002', 25);
INSERT INTO Working_Farmers2 VALUES ('Junior Farmer', 'FARM003', 20);
INSERT INTO Working_Farmers2  VALUES ('Supervisor', 'FARM004', 30);
INSERT INTO Working_Farmers2 VALUES ('Senior Farmer', 'FARM005', 25);

INSERT INTO FruitBasket VALUES ('Apples', 'FARM001', 500);
INSERT INTO FruitBasket VALUES ('Oranges', 'FARM002', 300);
INSERT INTO FruitBasket VALUES ('Grapes', 'FARM003', 450);
INSERT INTO FruitBasket VALUES ('Bananas', 'FARM004', 600);
INSERT INTO FruitBasket VALUES ('Strawberries', 'FARM005', 400);


INSERT INTO Crop VALUES ('Wheat', 120);
INSERT INTO Crop VALUES ('Corn', 96);
INSERT INTO Crop VALUES ('Soybeans', 110);
INSERT INTO Crop VALUES ( 'Rice', 78);
INSERT INTO Crop VALUES ('Barley', 62);


INSERT INTO Produce VALUES ('Wheat', 'FARM001', 'May', '2023', 200);
INSERT INTO Produce VALUES('Rice', 'FARM002', 'April', '2024', 170);
INSERT INTO Produce VALUES('Corn', 'FARM003', 'December', '2023', 93);
INSERT INTO Produce VALUES('Soybeans', 'FARM004', 'April', '2022', 72);
INSERT INTO Produce VALUES('Barley', 'FARM005', 'September', '2023', 220);

INSERT INTO ProduceStatus VALUES ('Wheat', 'FARM001', 'May', '2023', 'harvested');
INSERT INTO ProduceStatus VALUES('Rice', 'FARM002', 'April', '2024', 'sowing');
INSERT INTO ProduceStatus VALUES('Corn', 'FARM003', 'December', '2023', 'irrigation');
INSERT INTO ProduceStatus VALUES('Soybeans', 'FARM004', 'April', '2022', 'sowing');
INSERT INTO ProduceStatus VALUES('Barley', 'FARM005', 'September', '2023', 'fertilizer');


INSERT INTO Soil_Type1 (soil_id, soil_name, texture) VALUES ('SOIL001', 'Loamy', 'Smooth');
INSERT INTO Soil_Type1 (soil_id, soil_name, texture) VALUES ('SOIL002', 'Clay', 'Sticky');
INSERT INTO Soil_Type1 (soil_id, soil_name, texture) VALUES ('SOIL003', 'Sandy', 'Grainy');
INSERT INTO Soil_Type1 (soil_id, soil_name, texture) VALUES ('SOIL004', 'Peaty', 'Spongy');
INSERT INTO Soil_Type1 (soil_id, soil_name, texture) VALUES ('SOIL005', 'Silty', 'Silky');



INSERT INTO Soil_Type2 (texture, pH) VALUES ('Smooth', 6.5);
INSERT INTO Soil_Type2 (texture, pH) VALUES ('Sticky', 7.0);
INSERT INTO Soil_Type2 (texture, pH) VALUES ('Grainy', 5.5);
INSERT INTO Soil_Type2 (texture, pH) VALUES ('Spongy', 5.8);
INSERT INTO Soil_Type2 (texture, pH) VALUES ('Silky', 6.2);


INSERT INTO Farm_Soil (farm_id, soil_id) VALUES ('FARM001', 'SOIL001');
INSERT INTO Farm_Soil (farm_id, soil_id) VALUES ('FARM002', 'SOIL002');
INSERT INTO Farm_Soil (farm_id, soil_id) VALUES ('FARM004', 'SOIL004');
INSERT INTO Farm_Soil (farm_id, soil_id) VALUES ('FARM003', 'SOIL003');
INSERT INTO Farm_Soil (farm_id, soil_id) VALUES ('FARM005', 'SOIL005');


INSERT INTO Crop_Soil  VALUES ('Soybeans', 'SOIL001');
INSERT INTO Crop_Soil  VALUES ('Barley', 'SOIL001');
INSERT INTO Crop_Soil VALUES ('Wheat', 'SOIL002');
INSERT INTO Crop_Soil  VALUES ('Rice', 'SOIL001');
INSERT INTO Crop_Soil VALUES ('Corn', 'SOIL002');


INSERT INTO Distribution_Channel (channel_id, channel_name) VALUES ('CH001', 'Local Market');
INSERT INTO Distribution_Channel (channel_id, channel_name) VALUES ('CH002', 'Export');
INSERT INTO Distribution_Channel (channel_id, channel_name) VALUES ('CH003', 'Farmers Market');
INSERT INTO Distribution_Channel (channel_id, channel_name) VALUES ('CH004', 'Online Sales');
INSERT INTO Distribution_Channel (channel_id, channel_name) VALUES ('CH005', 'Direct to Consumer');

INSERT INTO Sold1 VALUES ('CH001', 'Wheat', 200, 10.50);
INSERT INTO Sold1 VALUES ('CH002', 'Corn', 150, 7.25);
INSERT INTO Sold1 VALUES ('CH003', 'Soybeans', 100, 8.75);
INSERT INTO Sold1 VALUES ('CH004', 'Rice', 250, 9.00);
INSERT INTO Sold1 VALUES ('CH005', 'Barley', 180, 6.50);

INSERT INTO Online (channel_id, url) VALUES ('CH004', 'www.onlineshoes.com');
INSERT INTO Online (channel_id, url) VALUES ('CH005', 'www.directtoconsumer.com');
INSERT INTO Online (channel_id, url) VALUES ('CH001', 'www.agriweb.com');
INSERT INTO Online (channel_id, url) VALUES ('CH002', 'www.farmgoods.org');
INSERT INTO Online (channel_id, url) VALUES ('CH003', 'www.cropsonline.net');



INSERT INTO Offline (channel_id, address) VALUES ('CH001', '123 Local Market St');
INSERT INTO Offline (channel_id, address) VALUES ('CH002', '456 Export Blvd');
INSERT INTO Offline (channel_id, address) VALUES ('CH003', '789 Farmers Market Ave');
INSERT INTO Offline (channel_id, address) VALUES ('CH004', '404 Harvest Home Rd');
INSERT INTO Offline (channel_id, address) VALUES ('CH005', '505 Tractor Trail');

INSERT INTO Sold_fruits VALUES ('CH002', 'Apples', 'FARM001',20, 6.50);
INSERT INTO Sold_fruits VALUES ('CH005', 'Oranges', 'FARM002',30, 9.0);
INSERT INTO Sold_fruits VALUES ('CH001', 'Grapes', 'FARM001',40, 8.50);
INSERT INTO Sold_fruits VALUES ('CH003', 'Apples', 'FARM002',60, 12.50);
INSERT INTO Sold_fruits VALUES ('CH005', 'Strawberries', 'FARM003',70, 7.50);


