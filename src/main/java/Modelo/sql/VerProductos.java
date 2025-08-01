
package Modelo.sql;

import Modelo.Almacen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class VerProductos {
    private final Connection conex;

    public VerProductos(Connection conector){
        this.conex = conector;

    }
    public boolean agregarRegistro(Almacen Almacen) throws SQLException {
        String sqlText = "INSERT INTO almacen(id_productos, unidades_stock, precio_producto, id_producots_entrada, tipo_producto, nombre_producto) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conex.prepareStatement(sqlText)) {
            stmt.setInt(1, Almacen.getId_productos());
            stmt.setInt(2, Almacen.getUnidades_stock());
            stmt.setFloat(3, Almacen.getPrecio_producto());
            stmt.setInt(4, Almacen.getId_producots_entrada());
            stmt.setString(5, Almacen.getTipo_producto());
            stmt.setString(6, Almacen.getNombre_producto());

            int filas = stmt.executeUpdate();

            return filas > 0;

        }
    }
    
    public boolean sumarStock(Almacen registro) throws SQLException {
        String sql = "UPDATE almacen SET unidades_stock = unidades_stock + ? WHERE id_productos = ?";

        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setInt(1, registro.getUnidades_stock());
            stmt.setInt(2, registro.getId_productos());
            return stmt.executeUpdate() > 0;
        }
    }
    // Verificar si un id_empleado ya existe
    public boolean existeRegistro(int idEmpleado) throws SQLException {
        String sqlText = "SELECT id_productos FROM almacen WHERE id_productos = ?";
        try (PreparedStatement stmt = conex.prepareStatement(sqlText)) {
            stmt.setInt(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true si ya existe
        }
    }

    // Obtener todos los registros como objetos Registro
    public List<Almacen> obtenerTodosLosRegistros() throws SQLException {
        List<Almacen> registros = new ArrayList<>();
        String sqlText = "SELECT * FROM almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                
                
                Almacen r = new Almacen(rs.getInt("id_productos"), rs.getInt("unidades_stock"), rs.getFloat("precio_producto"), rs.getInt("id_producots_entrada"), rs.getString("tipo_producto"), rs.getString("nombre_producto"));
                registros.add(r);
            }
        }
        return registros;
    }

    // Métodos adicionales por campo si los necesitas individualmente
    public List<Integer> obtenerIds() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sqlText = "SELECT id_productos FROM almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                ids.add(rs.getInt("id_productos"));
            }
        }
        return ids;
    }

    public List<String> obtenerStock() throws SQLException {
        List<String> precios = new ArrayList<>();
        String sqlText = "SELECT unidades_stock FROM almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                precios.add(rs.getString("unidades_stock"));
            }
        }
        return precios;
    }

    public List<String> obtenerPrecios() throws SQLException {
        List<String> fechas = new ArrayList<>();
        String sqlText = "SELECT precio_producto FROM almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                fechas.add(rs.getString("precio_producto"));
            }
        }
        return fechas;
    }
   
    
}
