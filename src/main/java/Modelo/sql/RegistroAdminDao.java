/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.sql;

import Modelo.RegistroAdmi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOLOZA
 */
public class RegistroAdminDao {
    private final Connection conex;

    public RegistroAdminDao(Connection conector) {

        this.conex = conector;
    }

    // Insertar un nuevo registro
    public boolean agregarRegistro(RegistroAdmi registro) throws SQLException {
        String sqlText = "INSERT INTO administrador(id_administrador, correo, nombre, estatus, codigoAdmin, contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conex.prepareStatement(sqlText)) {
            stmt.setInt(1, registro.getIdEmpleado());
            stmt.setString(2, registro.getCorreo());
            stmt.setString(3, registro.getNombre());
            stmt.setString(4, registro.getEstatus());
            stmt.setInt(5, registro.getIdAdmin());
            stmt.setString(6, registro.getContraseñas());

            int filas = stmt.executeUpdate();

            return filas > 0;

        }
    }

    public RegistroAdmi login(String dato1, String dato2) throws SQLException {
        String sql = "SELECT * FROM administrador WHERE nombre = ? AND contraseña = ?";

        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setString(1, dato1);
            stmt.setString(2, dato2);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RegistroAdmi(
                        rs.getInt("id_administrador"),
                        rs.getString("correo"),
                        rs.getString("nombre"),
                        rs.getString("estatus"),
                        rs.getInt("codigoAdmin"),
                        rs.getString("contraseña")
                );
            }
        }

        return null; // No se encontró coincidencia
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
    public List<RegistroAdmi> obtenerTodosLosRegistros() throws SQLException {
        List<RegistroAdmi> registros = new ArrayList<>();
        String sqlText = "SELECT * FROM empleado";
        try (Statement stmt = conex.createStatement(); ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                RegistroAdmi r = new RegistroAdmi(
                        rs.getInt("id_empleado"),
                        rs.getString("correo"),
                        rs.getString("nombre"),
                        rs.getString("estatus"),
                        rs.getInt("codigoAdmin"),
                        rs.getString("contraseña")
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
