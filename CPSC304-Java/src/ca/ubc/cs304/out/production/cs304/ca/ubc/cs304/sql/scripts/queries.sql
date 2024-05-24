-- INSERT QUERY
-- DatabaseConnectionHandler
-- public void insertProduceUser(ProduceModel model)
INSERT INTO Produce VALUES (?,?,?,?,?)

-- DELETE QUERY
-- public void deleteCrop(ProduceModel model)
DELETE FROM Produce WHERE crop_name = ? AND start_month = ? AND start_year = ?

-- UPDATE QUERY
-- public void updateFarmer(WorkingFarmersModel model)
UPDATE Working_Farmers SET farmer_name = ?, farmer_phone_number = ?,
                           farmer_type = ?, farmer_hours = ?
                       WHERE farmer_id =?

-- SELECTION QUERY
SELECT crop_name, start_month, start_year from ProduceStatus WHERE status = ? AND farm_id =?

-- PROJECTION QUERY
SELECT distinct crop_name AS crop_name, quantity AS quantity from Produce where farm_id = ?
-- JOIN QUERY
SELECT distinct p.crop_name, p.quantity from Produce p, ProduceStatus ps WHERE ps.status = ? AND p.crop_name = ps.crop_name

-- AGGREGATION WITH GROUP BY QUERY
SELECT crop_name AS crop_name, sum(quantity) AS quantity from Produce where farm_id = ? group by crop_name


-- AGGREGATION WITH GROUP BY HAVING QUERY
SELECT crop_name AS crop_name, sum(quantity) AS
    quantity from Produce where farm_id = ?
                          group by crop_name having sum(quantity) >= ?

-- NESTED AGGREGATION GROUP BY QUERY
select crop_name, sum(quantity)/count(distinct start_year)
    as average_quantity from produce WHERE farm_id = ? group by crop_name

-- DIVISION
select location,area from Monitored_Farms m where not exists
    (select crop_name from Crop c
                      minus select crop_name from Produce p where p.farm_id = m.farm_id)