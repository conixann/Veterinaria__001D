package cl.duoc.veterinaria.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection cone;

    public Conexion(){
        try{
            String url="jdbc:mysql://localhost:3306/veterinaria";
            String usuario="root";
            String password="";
            cone = DriverManager.getConnection(url,usuario,password);

        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCone() {
        return cone;
    }
}
