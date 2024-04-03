/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconnection;

/**
 *
 * @author home
 */
import java.sql.Connection;
import java.sql.SQLException;



public class DataBaseConnection {

    
    private static DataBaseConnection instance;
    private Connection connection;
    
    public static DataBaseConnection getInstance(){
        if (instance==null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
    private DataBaseConnection(){
        
    }
    public void ConnectToDatabase() throws  SQLException, ClassNotFoundException{
//        String server = "127.0.0.1";
//        String port = "3306";
//        String database ="carton";
//        String user = "root";
//        String password = "hyacenth23ortega";
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        connection = (Connection) java.sql.DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database,user,password);
        String server = "127.0.0.1";
        String port = "3306";
        String database ="carton_inventory_management_data";
        String user = "root";
        String password = "ayaortega";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = (Connection) java.sql.DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database,user,password);

    }
    public Connection getConnection() {
        return connection;
    }
}