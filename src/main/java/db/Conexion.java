package db;

import co.edu.uniquindio.alquiler.model.Domain;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;

public class Conexion {

    private Connection conexionT=null;

    private final String usuario="NicolasPena";
    private final String contrasenia="nicolas";
    private final String bd="Software2";
    private final String Ip="localhost";
    private final String puerto="1433";

    private static Conexion conexion;

    private Conexion(){

    }

    public static Conexion getInstance(){
        if(conexion == null){
            conexion = new Conexion();
        }

        return conexion;
    }


    String cadenaJDBC="jdbc:sqlserver://"+Ip+":"+puerto+"/"+bd;

    public Connection conectarBDSoftware2(){
        try
        {
            String cadena="jdbc:sqlserver://localhost:"+puerto+";"+"DatabaseName="+bd+";"+"encrypt=true;trustServerCertificate=true;";
            conexionT= DriverManager.getConnection(cadena,usuario,contrasenia);
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alerta");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return conexionT;
    }

    public void cerrarConexion() {
        try
        {
            conexionT.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Connection getConexionT() {
        return conexionT;
    }

    public void setConexionT(Connection conexionT) {
        this.conexionT = conexionT;
    }
}
