
package Modelo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String usuario="root";
    private static final String url = "jdbc:mysql://localhost:3306/bd_kpricho?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String clave="";
    public Conexion(){
        
    }
    
    
    public static Connection hacerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Conexión establecida correctamente.");
            return DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ ERROR al conectar: " + e.getMessage());
            throw new SQLException(e);
        }
    }
}
