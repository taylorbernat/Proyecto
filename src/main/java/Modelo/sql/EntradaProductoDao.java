
package Modelo.sql;

import Modelo.EntradaProducto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EntradaProductoDao {
    private final Connection conex;

    public EntradaProductoDao(Connection conector){
        this.conex = conector;

    }
    public boolean sumarStock(EntradaProducto registro) throws SQLException {
        String sql = "UPDATE entrada_almacen SET cantidad_producto = cantidad_producto + ? WHERE id_producto = ?";

        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setInt(1, registro.getCantProduct());
            stmt.setInt(2, registro.getIdProducto());
            return stmt.executeUpdate() > 0;
        }
    }
    public boolean agregarRegistro(EntradaProducto registro) throws SQLException {
        String sqlText = "INSERT INTO entrada_almacen(id_producto , cantidad_producto, fecha_entrega, Provedor, movimiento, precio, tipo_producto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conex.prepareStatement(sqlText)) {
            stmt.setInt(1, registro.getIdProducto());
            stmt.setInt(2, registro.getCantProduct());
            stmt.setDate(3, (Date) registro.getFechaProduct());
            stmt.setString(4, registro.getProovedor());
            stmt.setString(5, registro.getMovimiento());
            stmt.setInt(6, registro.getPrecioProducto());
            stmt.setString(7, registro.getTipoProducto());


            int filas = stmt.executeUpdate();

            return filas > 0;

        }
    }

    public boolean existeRegistro(int idEmpleado) throws SQLException {
        String sqlText = "SELECT id_producto FROM entrada_almacen WHERE id_producto = ?";
        try (PreparedStatement stmt = conex.prepareStatement(sqlText)) {
            stmt.setInt(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    // Obtener todos los registros como objetos Registro
    public List<EntradaProducto> obtenerTodosLosRegistros() throws SQLException {
        List<EntradaProducto> registros = new ArrayList<>();
        String sqlText = "SELECT * FROM entrada_almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                EntradaProducto r = new EntradaProducto(rs.getInt("id_producto"), rs.getInt("cantidad_producto"), rs.getDate("fecha_entrega"), rs.getString("Provedor"), rs.getString("movimiento"), rs.getInt("precio"), rs.getString("tipo_producto"));
                registros.add(r);
            }
        }
        return registros;
    }

    // Métodos adicionales por campo si los necesitas individualmente
    public List<Integer> obtenerIds() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sqlText = "SELECT id_producto FROM entrada_almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                ids.add(rs.getInt("id_producto"));
            }
        }
        return ids;
    }

    public List<String> obtenerPrecios() throws SQLException {
        List<String> precios = new ArrayList<>();
        String sqlText = "SELECT cantidad_producto FROM entrada_almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                precios.add(rs.getString("cantidad_producto"));
            }
        }
        return precios;
    }

    public List<String> obtenerFechas() throws SQLException {
        List<String> fechas = new ArrayList<>();
        String sqlText = "SELECT fecha_entrega FROM entrada_almacen";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                fechas.add(rs.getString("fecha_entrega"));
            }
        }
        return fechas;
    }

    
}
