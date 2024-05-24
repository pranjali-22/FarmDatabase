package ca.ubc.cs304.database;

import java.sql.*;
import java.util.ArrayList;

import ca.ubc.cs304.Exception.InsertException;
import ca.ubc.cs304.model.*;

import ca.ubc.cs304.util.PrintablePreparedStatement;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void databaseSetup() {
        dropBranchTableIfExists();
        createMonitoredFarms();
        createWorkingFarmers();
        createCrop();
        createProduce();
        createProduceStatus();
    }
    private void dropBranchTableIfExists() {
        try {
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            ps.execute("DROP TABLE ProduceStatus");
            ps.execute("DROP TABLE Produce");
            ps.execute("DROP TABLE Crop");
            ps.execute("DROP TABLE Working_Farmers");
            ps.execute("DROP TABLE Monitored_Farms");
            rs.close();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertMonitoredFarms(MonitoredFarmsModel model) {
        try {
            String query = "INSERT INTO Monitored_Farms VALUES (?,?,?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, model.getFarm_id());
            ps.setString(2, model.getLocation());
            ps.setFloat(3, model.getArea_acre());
            ps.setString(4, model.getSupervisor_id());
            ps.setString(5, model.getPhone_number());
            ps.setString(6, model.getSupervisor_name());
            ps.setInt(7, model.getSalary());

            ps.executeUpdate();
            connection.commit();


            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }
    public void insertWorkingFarmers(WorkingFarmersModel model) {
        try {
            String query = "INSERT INTO Working_Farmers VALUES (?,?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, model.getFarmer_id());
            ps.setString(2, model.getFarmer_name());
            ps.setString(3, model.getFarmer_phone_number());
            ps.setString(4, model.getFarmer_type());
            ps.setString(5, model.getFarm_id());
            ps.setFloat(6, model.getFarmer_hours());

            ps.executeUpdate();
            connection.commit();


            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCrop(CropModel model) {
        try {
            String query = "INSERT INTO Crop VALUES (?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, model.getCrop_name());
            ps.setInt(2, model.getGrowth_duration_days());
            ps.executeUpdate();
            connection.commit();


            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertProduce(ProduceModel model) {
        boolean t = false;
        try {
            String query = "INSERT INTO Produce VALUES (?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, model.getCrop_name());
            ps.setString(2, model.getFarm_id());
            ps.setString(3, model.getStart_month());
            ps.setInt(4, model.getStart_year());
            ps.setInt(5, model.getQuantity());

            ps.executeUpdate();
            connection.commit();
            t = true;
//            JOptionPane.showMessageDialog(null, model.getCrop_name() + " Inserted", "Message Box", JOptionPane.PLAIN_MESSAGE);

            ps.close();
        } catch (SQLException e) {
            if(!t) {
                JOptionPane.showMessageDialog(null, model.getCrop_name() + " Cannot be inserted", "Message Box", JOptionPane.PLAIN_MESSAGE);
                System.out.println(EXCEPTION_TAG + " " + e.getMessage());
                rollbackConnection();
            }
        }
    }
    public void insertProduceUser(ProduceModel model) {
        boolean t = false;
        try {
            String query = "INSERT INTO Produce VALUES (?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, model.getCrop_name());
            ps.setString(2, model.getFarm_id());
            ps.setString(3, model.getStart_month());
            ps.setInt(4, model.getStart_year());
            ps.setInt(5, model.getQuantity());

            ps.executeUpdate();
            connection.commit();
            t = true;
            JOptionPane.showMessageDialog(null, model.getCrop_name() + " Inserted", "Message Box", JOptionPane.PLAIN_MESSAGE);

            ps.close();
        } catch (SQLException e) {
            if(!t) {
                JOptionPane.showMessageDialog(null, model.getCrop_name() + " Cannot be inserted", "Message Box", JOptionPane.PLAIN_MESSAGE);
                System.out.println(EXCEPTION_TAG + " " + e.getMessage());
                rollbackConnection();
            }
        }
    }

    public void insertProduceStatus(ProduceStatusModel model) {
        try {
            String query = "INSERT INTO ProduceStatus VALUES (?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, model.getCrop_name());
            ps.setString(2, model.getFarm_id());
            ps.setString(3, model.getStart_month());
            ps.setInt(4, model.getStart_year());
            ps.setString(5, model.getCrop_status());


            ps.executeUpdate();
            connection.commit();


            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void createMonitoredFarms() {
        try {
            String query = "CREATE TABLE Monitored_Farms (farm_id VARCHAR(20) PRIMARY KEY, location VARCHAR(40) UNIQUE, area FLOAT, supervisior_id VARCHAR(20) NOT NULL UNIQUE, phone_number VARCHAR(20)  UNIQUE, supervisior_name VARCHAR(20), salary INTEGER)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        MonitoredFarmsModel branch1 = new MonitoredFarmsModel("FARM001", "1050 West 14th Ave", 100.25F, "SUP001", "555-0101", "John Doe", 70000);
        MonitoredFarmsModel branch2 = new MonitoredFarmsModel("FARM002", "2020 East 7th Ave", 150.0F, "SUP002", "555-0102", "Jane Smith", 75000);
        MonitoredFarmsModel branch3 = new MonitoredFarmsModel("FARM003", "3842 West 13th Ave", 500.25F, "SUP003", "555-0999", "Jia Singh", 90000);

        insertMonitoredFarms(branch1);
        insertMonitoredFarms(branch2);
        insertMonitoredFarms(branch3);
    }
    public void createWorkingFarmers() {
        try {
            String query = "CREATE TABLE Working_Farmers (farmer_id VARCHAR(20) PRIMARY KEY,farmer_name VARCHAR(20),farmer_phone_number VARCHAR(20) NOT NULL, farmer_type VARCHAR(20),farm_id VARCHAR(20) NOT NULL, farmer_hours FLOAT, FOREIGN KEY (farm_id) REFERENCES Monitored_Farms(farm_id) ON DELETE CASCADE )";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        WorkingFarmersModel branch1 = new WorkingFarmersModel("FARMER002", "Natalie Portman", "555-0202", "Senior Farmer", "FARM002", 45);
        WorkingFarmersModel branch2 = new WorkingFarmersModel("FARMER001", "Tom Hardy", "555-0201", "Junior Farmer", "FARM001", 40);
        insertWorkingFarmers(branch1);
        insertWorkingFarmers(branch2);
    }

    public void createCrop() {
        try {
            String query = "CREATE TABLE Crop (crop_name VARCHAR(20) PRIMARY KEY ,growth_duration_days INTEGER)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        CropModel cm1 = new CropModel("Wheat", 325);
        CropModel cm2 = new CropModel("Rice", 200);
        CropModel cm3 = new CropModel("Corn", 145);
        insertCrop(cm1);
        insertCrop(cm2);
        insertCrop(cm3);

    }
    public void createProduce() {
        try {
            String query = "CREATE TABLE Produce (crop_name varchar(20), farm_id varchar(20), start_month varchar(10),start_year integer, quantity integer, primary key (crop_name,farm_id,start_month,start_year), foreign key (crop_name) references Crop(crop_name) on delete cascade,foreign key (farm_id) references Monitored_Farms(farm_id) on delete cascade )";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        ProduceModel pm1 = new ProduceModel ("Wheat", "FARM001", "May", 2023, 100);
        ProduceModel pm2 = new ProduceModel ("Wheat", "FARM001", "April", 2023, 120);
        ProduceModel pm3 = new ProduceModel ("Wheat", "FARM001", "August", 2023, 120);

//        ProduceModel pm4 = new ProduceModel ("Wheat", "FARM001", "May", 2023, 130);
        ProduceModel pm5 = new ProduceModel ("Wheat", "FARM001", "April", 2022, 240);
        ProduceModel pm6 = new ProduceModel ("Wheat", "FARM001", "August", 2022, 300);

        ProduceModel pm7 = new ProduceModel ("Wheat", "FARM001", "May", 2021, 200);
        ProduceModel pm8 = new ProduceModel ("Wheat", "FARM001", "April", 2021, 300);
        ProduceModel pm9 = new ProduceModel ("Wheat", "FARM001", "August", 2021, 320);

        ProduceModel pm19 = new ProduceModel("Rice", "FARM001", "August", 2023, 120);
        ProduceModel pm20 = new ProduceModel("Rice", "FARM001", "June", 2023,100);

        ProduceModel pm21 = new ProduceModel("Rice", "FARM001", "April", 2024, 220);
        ProduceModel pm22 = new ProduceModel("Rice", "FARM001", "May", 2024, 200);

        ProduceModel pm10 = new ProduceModel("Corn", "FARM001", "May", 2022, 100);
        ProduceModel pm11 = new ProduceModel("Corn", "FARM001", "September", 2022, 20);
        ProduceModel pm12 = new ProduceModel("Corn", "FARM001", "January", 2022, 50);

        ProduceModel pm23 = new ProduceModel ("Wheat", "FARM002", "May", 2023, 100);
        ProduceModel pm24 = new ProduceModel ("Wheat", "FARM002", "April", 2023, 120);
        ProduceModel pm25 = new ProduceModel ("Wheat", "FARM002", "August", 2023, 120);

//        ProduceModel pm26 = new ProduceModel ("Wheat", "FARM002", "May", 2023, 130);
        ProduceModel pm27 = new ProduceModel ("Wheat", "FARM002", "April", 2022, 240);
        ProduceModel pm28 = new ProduceModel ("Wheat", "FARM002", "August", 2022, 300);

        ProduceModel pm29 = new ProduceModel ("Wheat", "FARM002", "May", 2021, 200);
        ProduceModel pm30 = new ProduceModel ("Wheat", "FARM002", "April", 2021, 300);
        ProduceModel pm31 = new ProduceModel ("Wheat", "FARM002", "August", 2021, 320);

        ProduceModel pm38 = new ProduceModel("Corn", "FARM002", "May", 2022, 100);
        ProduceModel pm37 = new ProduceModel("Corn", "FARM002", "September", 2022, 20);
        ProduceModel pm36 = new ProduceModel("Corn", "FARM002", "January", 2022, 50);




        ProduceModel pm32 = new ProduceModel("Corn", "FARM003", "May", 2022, 100);
        ProduceModel pm33 = new ProduceModel("Corn", "FARM003", "September", 2022, 20);
        ProduceModel pm34 = new ProduceModel("Corn", "FARM003", "January", 2022, 50);

        insertProduce(pm38);
        insertProduce(pm37);
        insertProduce(pm36);

        insertProduce(pm34);
        insertProduce(pm33);
        insertProduce(pm32);
        insertProduce(pm31);
        insertProduce(pm30);
        insertProduce(pm29);

        insertProduce(pm28);
        insertProduce(pm27);
//        insertProduce(pm26);
        insertProduce(pm25);
        insertProduce(pm24);

        insertProduce(pm23);
        insertProduce(pm22);
//        insertProduce(pm21);
        insertProduce(pm21);
        insertProduce(pm20);
        insertProduce(pm19);
//        insertProduce(pm18);
//        insertProduce(pm16);
//        insertProduce(pm17);
//        insertProduce(pm15);
//        insertProduce(pm14);
//        insertProduce(pm13);
        insertProduce(pm12);
        insertProduce(pm11);
        insertProduce(pm10);
        insertProduce(pm9);
        insertProduce(pm8);
        insertProduce(pm7);
        insertProduce(pm6);
        insertProduce(pm5);
//        insertProduce(pm4);
        insertProduce(pm3);
        insertProduce(pm2);
        insertProduce(pm1);
    }
    public void createProduceStatus() {
        try {
            String query = "CREATE TABLE ProduceStatus (crop_name varchar(20), farm_id varchar(20),start_month varchar(10),start_year integer, status varchar(20) NOT NULL , primary key (crop_name,start_month,start_year,farm_id), foreign key (crop_name,farm_id,start_month,start_year) references Produce (crop_name,farm_id,start_month,start_year) on delete cascade)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","May",2021,"sowing"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","April",2021,"sowing"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","August",2021,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","May",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","April",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","August",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","May",2023,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","April",2023,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM001","August",2023,"harvested"));

        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","May",2021,"sowing"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","April",2021,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","August",2021,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","May",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","April",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","August",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","May",2023,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","April",2023,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Wheat","FARM002","August",2023,"harvested"));

        insertProduceStatus(new ProduceStatusModel("Rice","FARM001","April",2024,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Rice","FARM001","May",2024,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Rice","FARM001","June",2023,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Rice","FARM001","August",2023,"harvested"));

        insertProduceStatus(new ProduceStatusModel("Corn","FARM003","January",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Corn","FARM003","September",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Corn","FARM003","May",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Corn","FARM001","January",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Corn","FARM001","September",2022,"sowing"));
        insertProduceStatus(new ProduceStatusModel("Corn","FARM001","May",2022,"harvested"));

        insertProduceStatus(new ProduceStatusModel("Corn","FARM002","January",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Corn","FARM002","September",2022,"harvested"));
        insertProduceStatus(new ProduceStatusModel("Corn","FARM002","May",2022,"harvested"));


    }

    public MonitoredFarmsModel getMonitoredFarms(String bn) {
        MonitoredFarmsModel bm = null;
        try {
            String query = "SELECT * FROM Monitored_Farms WHERE supervisior_id  = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, bn);
            ResultSet rs = ps.executeQuery();
            rs.next();
            bm = new MonitoredFarmsModel(rs.getString("farm_id"),
                    rs.getString("location"),
                    rs.getFloat("area"),
                    rs.getString("supervisior_id"),
                    rs.getString("phone_number"),
                    rs.getString("supervisior_name"),
                    rs.getInt("salary"));
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return bm;
    }
    public WorkingFarmersModel getWorkingFarmers(String farmer_id) {
        WorkingFarmersModel bm = null;

        try {
            String query = "SELECT * FROM Working_Farmers WHERE farmer_id  = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, farmer_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            bm = new WorkingFarmersModel(rs.getString("farmer_id"),
                    rs.getString("farmer_name"),
                    rs.getString("farmer_phone_number"),
                    rs.getString("farmer_type"),
                    rs.getString("farm_id"),
                    rs.getFloat("farmer_hours"));

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return bm;
    }

    public ProduceAndStatusModel[] getProduceAndStatus(String fn) {
        ArrayList<ProduceAndStatusModel> result = new ArrayList<ProduceAndStatusModel>();


        try {
            String query = "SELECT pr.farm_id AS farm_id,pr.crop_name AS crop_name,pr.start_date AS start_date,pr.quantity AS quantity,ps.status AS status FROM Produce pr LEFT JOIN ProduceStatus ps ON  pr.start_date = ps.start_date AND pr.crop_name = ps.crop_name WHERE pr.farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                ProduceAndStatusModel model = new ProduceAndStatusModel(rs.getString("farm_id"),
                        rs.getString("crop_name"),
                        rs.getString("start_month"),
                        rs.getInt("start_year"),
                        rs.getInt("quantity"),
                        rs.getString("status"));
                result.add(model);
            }


            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new ProduceAndStatusModel[result.size()]);

    }
    public void updateFarmer(WorkingFarmersModel model) {
        boolean t = false;
        try {
            String query = "UPDATE Working_Farmers SET farmer_name = ?, farmer_phone_number = ?, farmer_type = ?, farmer_hours = ? WHERE farmer_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            System.out.println("Update Function");
            System.out.println(model.getFarmer_name());
            System.out.println(model.getFarmer_phone_number());
            System.out.println(model.getFarmer_type());
            System.out.println(model.getFarmer_hours());
            System.out.println(model.getFarmer_id());
            System.out.println("Updating details");
            ps.setString(1, model.getFarmer_name());
            ps.setString(2, model.getFarmer_phone_number());
            ps.setString(3, model.getFarmer_type());
            ps.setFloat(4, model.getFarmer_hours());
            ps.setString(5, model.getFarmer_id());
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " farm " + model.getFarmer_id() + " does not exist!");
            }
            else {
                System.out.println(model.getFarmer_id() + "exists");
            }

            connection.commit();

            ps.close();
        JOptionPane.showMessageDialog(null, "Updated");

        } catch (SQLException e) {
            if(!t) {
                JOptionPane.showMessageDialog(null, "not Updated");
                System.out.println(EXCEPTION_TAG + " " + e.getMessage());
                rollbackConnection();
            }

        }
    }
    public String[] getCropNames(String fn) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct crop_name AS crop_name from Produce where farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getCrop_name_quantity(String fn) {
        // show the crop name and quantity from Produce
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct crop_name AS crop_name, quantity AS quantity from Produce where farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+ " " +
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getCrop_name_startDate(String fn) {
        // show the crop name and quantity from Produce
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct crop_name AS crop_name, start_month AS start_month, start_year AS start_year from Produce where farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+ " "
                        +rs.getString("start_month")+ " "+
                        rs.getString("start_year"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getCrop_startDate_quantity(String fn) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT * from Produce where farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name") + " "+
                        rs.getString("start_month") + " " +
                        rs.getString("start_year")+ " "+
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getCrop_name_status(String fn) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct p.crop_name AS crop_name, ps.status from Produce p, ProduceStatus ps where p.crop_name = ps.crop_name AND p.start_month = ps.start_month AND p.start_year = ps.start_year AND p.farm_id = ? AND p.farm_id = ps.farm_id";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name") + " "+
                        rs.getString("status"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getCrop_startDate_status(String fn) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct p.crop_name AS crop_name, ps.status, p.start_month as start_month, p.start_year AS start_year from Produce p, ProduceStatus ps where p.crop_name = ps.crop_name AND p.start_month = ps.start_month AND p.start_year = ps.start_year AND p.farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name") + " "+
                        rs.getString("start_month") + " " +
                        rs.getString("start_year")+ " "+
                        rs.getString("status"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getCrop_startDate_status_quantity(String fn) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct p.crop_name AS crop_name, ps.status as status, p.start_month as start_month, p.start_year AS start_year, p.quantity as quantity from Produce p, ProduceStatus ps where p.crop_name = ps.crop_name AND p.start_month = ps.start_month AND p.start_year = ps.start_year AND p.farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+" "+
                        rs.getString("start_month") + " " +
                        rs.getString("start_year")+ " "+
                        rs.getString("status") + " " +
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getSq(String fn) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct p.crop_name AS crop_name, ps.status as status, p.start_month as start_month, p.start_year AS start_year, p.quantity as quantity from Produce p, ProduceStatus ps where p.crop_name = ps.crop_name AND p.start_month = ps.start_month AND p.start_year = ps.start_year AND p.farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+" "+
                        rs.getString("status") + " " +
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getTotal_Quantity(String fn) {
        // show the crop name and quantity from Produce
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT crop_name AS crop_name, sum(quantity) AS quantity from Produce where farm_id = ? group by crop_name";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+ " " +
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }
    public String[] getTotal_Quantity_Year(String fn) {
        // show the crop name and quantity from Produce
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT start_year AS start_year, sum(quantity) AS quantity from Produce where farm_id = ? group by start_year";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("start_year") + " "+
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getTotal_Quantity_Name_Year(String fn) {
        // show the crop name and quantity from Produce
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT crop_name AS crop_name, start_year AS start_year, sum(quantity) AS quantity from Produce where farm_id = ? group by crop_name , start_year";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+ " " +
                        rs.getString("start_year") + " "+
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }
    public String[] getHaving_total_quantity_name(String fn, int q) {
        // show the crop name and quantity from Produce
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT crop_name AS crop_name, sum(quantity) AS quantity from Produce where farm_id = ? group by crop_name having sum(quantity) >= ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ps.setInt(2, q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+ " " +
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }
    public String[] getAvg_year_total_quantity(String fn) {
        // show the crop name and quantity from Produce
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "select crop_name, sum(quantity)/count(distinct start_year) as average_quantity from produce WHERE farm_id = ? group by crop_name";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, fn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+ " " +
                        String.valueOf(rs.getInt("average_quantity")) );;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }
    public String[] selectStatus(String status,String fid) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT crop_name, start_month, start_year from ProduceStatus WHERE status = ? AND farm_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, status);
            ps.setString(2, fid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+" "+
                        rs.getString("start_month") + " " +
                        rs.getString("start_year"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] selectDate(String month, int year) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct crop_name, status from ProduceStatus WHERE start_month = ? AND start_year = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+" "+
                        rs.getString("status"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getJoinStatusQuantity(String status, String fid) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT distinct p.crop_name, p.quantity from Produce p, ProduceStatus ps WHERE ps.status = ? AND p.crop_name = ps.crop_name AND p.farm_id = ? AND p.farm_id = ps.farm_id";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, status);
            ps.setString(2, fid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name")+" "+
                        rs.getString("quantity"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);

    }
    public String[] getAllCropNames() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT * from Crop ";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("crop_name") + " " + rs.getString("growth_duration_days"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }
    public String[] getAllFarms() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "SELECT * from Monitored_Farms ";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("farm_id") + " " +
                        rs.getString("location") + " " +
                        rs.getString("area"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    public String[] getFarmsWithALlCrops() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String query = "select location,area from Monitored_Farms m where not exists (select crop_name from Crop c minus select crop_name from Produce p where p.farm_id = m.farm_id)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("location") + " " + rs.getString("area"));;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }
    public void deleteCrop(ProduceModel model) {
        boolean isValid = false;
        try {
            String query = "DELETE FROM Produce WHERE crop_name = ? AND start_month = ? AND start_year = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, model.getCrop_name());
            ps.setString(2, model.getStart_month());
            ps.setInt(3, model.getStart_year());

            int rowCount = ps.executeUpdate();
            isValid = true;
            if (rowCount == 0) {
                isValid = false;
                JOptionPane.showMessageDialog(null, model.getCrop_name() + " does not exist", "Message Box", JOptionPane.PLAIN_MESSAGE);
            }

            connection.commit();


            ps.close();
        } catch (SQLException e) {
            isValid = false;
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        if(isValid) {
            JOptionPane.showMessageDialog(null, model.getCrop_name() + " Deleted", "Message Box", JOptionPane.PLAIN_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, model.getCrop_name() + " Cannot be deleted", "Message Box", JOptionPane.PLAIN_MESSAGE);
        }
    }
}