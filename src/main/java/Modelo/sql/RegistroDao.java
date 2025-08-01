package Modelo.sql;

import Modelo.Registro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroDao {
    
    private final Connection conex;

    public RegistroDao(Connection conector) {

        this.conex = conector;
    }


    // Insertar un nuevo registro
    public boolean agregarRegistro(Registro registro) throws SQLException {
        String sqlText = "INSERT INTO empleado(id_empleado, nombre, correo, contraseña, estado, estatus, Id_admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conex.prepareStatement(sqlText)) {
            stmt.setInt(1, registro.getIdEmpleado());
            stmt.setString(2, registro.getNombre());
            stmt.setString(3, registro.getCorreo());
            stmt.setString(4, registro.getContraseñas());
            stmt.setBoolean(5, registro.getEstado());
            stmt.setString(6, registro.getEstatus());
            stmt.setInt(7, registro.getIdAdmin());

            int filas = stmt.executeUpdate();

            return filas > 0;

            
        }
    }
    public boolean actualizarEstado(Registro usuario) throws SQLException {
        String sql = "UPDATE empleado SET estado = ? WHERE id_empleado = ?";

        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            System.out.println("estado actual"+usuario.getEstado());
            if(usuario.getEstado()){
                stmt.setBoolean(1, false);
                stmt.setInt(2, usuario.getIdEmpleado());
            }else{
            
                stmt.setBoolean(1, true);
                stmt.setInt(2, usuario.getIdEmpleado());
            }
            

            int filas = stmt.executeUpdate();
            return filas > 0; 
        }
    }
    public Registro login(String dato1, String dato2) throws SQLException {
        String sql = "SELECT * FROM empleado WHERE nombre = ? AND contraseña = ? AND estado = 1";

        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setString(1, dato1);
            stmt.setString(2, dato2);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Registro(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getBoolean("estado"),
                        rs.getString("estatus"),
                        rs.getInt("Id_admin")
                );
            }
        }

        return null; // No se encontró coincidencia
    }
    public boolean eliminarUsuarioPorId(Registro Registro) throws SQLException {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setInt(1, Registro.getIdEmpleado());
            return ps.executeUpdate() > 0; // Devuelve true si se borró alguna fila
        }
    }
    
    
    public Registro SeleccionarUsusrioId(Registro Registro) throws SQLException {
        String sql =  "SELECT * FROM empleado WHERE id_empleado = ?";
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setInt(1, Registro.getIdEmpleado());
            return Registro;
        }
    }
    // Verificar si un id_empleado ya existe
    public boolean existeRegistro(int idEmpleado) throws SQLException {
        String sqlText = "SELECT id_empleado FROM empleado WHERE id_empleado = ?";
        try (PreparedStatement stmt = conex.prepareStatement(sqlText)) {
            stmt.setInt(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true si ya existe
        }
    }
 
    
    
    
    

    // Obtener todos los registros como objetos Registro
    public List<Registro> obtenerTodosLosRegistros() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        String sqlText = "SELECT * FROM empleado";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                Registro r = new Registro(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getBoolean("estado"),
                        rs.getString("estatus"),
                        rs.getInt("Id_admin")
                );
                registros.add(r);
            }
        }
        return registros;
    }

    // Métodos adicionales por campo si los necesitas individualmente
    public List<Integer> obtenerIds() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sqlText = "SELECT id_empleado FROM empleado";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                ids.add(rs.getInt("id_empleado"));
            }
        }
        return ids;
    }

    public List<String> obtenerNombres() throws SQLException {
        List<String> nombres = new ArrayList<>();
        String sqlText = "SELECT nombre FROM empleado";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        }
        return nombres;
    }

    public List<String> obtenerCorreos() throws SQLException {
        List<String> correos = new ArrayList<>();
        String sqlText = "SELECT correo FROM empleado";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                correos.add(rs.getString("correo"));
            }
        }
        return correos;
    }

    public List<String> obtenerContraseña() throws SQLException {
        List<String> productos = new ArrayList<>();
        String sqlText = "SELECT contraseña FROM empleado";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                productos.add(rs.getString("contraseña"));
            }
        }
        return productos;
    }
}
