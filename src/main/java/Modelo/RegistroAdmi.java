/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author TOLOZA
 */
public class RegistroAdmi {
    
        private int idEmpleado;
    private String nombre;
    private String correo;
    private String estatus;
    private int Codigo;
    private String contraseña;

    public RegistroAdmi(int idEmpleado, String nombre, String correo, String estatus, int codigo, String contraseña) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estatus = estatus;
        this.Codigo = codigo;

    }

 
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    
    
    public int getIdAdmin() {
        return Codigo;
    }

    public void setIdAdmin(int idAdmin) {
        this.Codigo = idAdmin;
    }

    
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseñas() {
        return contraseña;
    }

    public void setIdProductosAlmacenamiento(String contraseña) {
        this.contraseña = contraseña;
    }

}


