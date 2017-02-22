package io.muic.ooc.webapp.service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JohnnyV on 2/14/2017 AD.
 */
public class DatabaseService {

    SecurityService securityService;

    Connection conn;
    private final String SQL_URL = "jdbc:mysql://localhost:3306/User_Database";
//    private final String SQL_USERNAME = "";

    public DatabaseService() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(SQL_URL, "root", "qazwsx992erd");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteDB(String uid){
        try {
            PreparedStatement preS = this.conn.prepareStatement("DELETE FROM User_Database.User_data WHERE Username = '" + uid + "' ");
            preS.executeUpdate();

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateDB(String uid, String usr, String fname){
        try {
//            PreparedStatement preS = this.conn.prepareStatement("UPDATE User_Database.User_data SET Username = ?, FirstName = ? WHERE Username = '" + usr + "';");
            PreparedStatement preS = this.conn.prepareStatement("UPDATE User_Database.User_data SET Username = ?, FirstName = ? WHERE Username = ?");
            preS.setString(1,usr);
            preS.setString(2,fname);
            preS.setString(3,usr);
            preS.executeUpdate();

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertDB(String usr, String pwd, String fname){
        try {
            PreparedStatement preS = this.conn.prepareStatement("INSERT INTO User_Database.User_data (Username,Password, FirstName) VALUES (?,?,?);");
            preS.setString(1,usr);
            preS.setString(2,pwd);
            preS.setString(3,fname);
            preS.executeUpdate();

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Map<String, String> readData(){
        Map<String, String> temp = new HashMap<>();

        try {
            PreparedStatement preS = this.conn.prepareStatement("SELECT * FROM User_Database.User_data;");
            ResultSet result = preS.executeQuery();

            while (result.next()){
                String id = result.getString("id");
                String usr = result.getString("username");
                String pwd = result.getString("password");
                String fname = result.getString("firstname");
//                System.out.println("id: " + id + ", username: " + usr + ", pwd: " + pwd + ", fname: " + fname);
                temp.put(usr,pwd);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

//        System.out.println(temp);
        return temp;
    }


}
