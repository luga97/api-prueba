/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diana
 */
public class Conexion {
    Connection conexion;
    Statement Sentencias;
    ResultSet Datos;
    PreparedStatement psPrepararaSentencia;
    
    public Conexion() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url= "jdbc:mysql://localhost:3306/db_prueba";
            conexion= DriverManager.getConnection(url, "root", "1234");
            Sentencias = conexion.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet Consultar1 (){
        try {
            Datos = Sentencias.executeQuery("select * from registro");
           
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datos;
    }
 
    public boolean insertUpdate(String nombre, String cedula, String huella_imagen, String huella_template_iso19794, String huella_template_ansi378){
        String sql="insert into registro( nombre, cedula, huella_imagen, huella_template_iso19794, huella_template_ansi378) values(?,?,?,?,?)";
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
           
            pst.setString(1, nombre);
            pst.setString(2, cedula);
            pst.setString(3, huella_imagen);
            pst.setString(4, huella_template_iso19794);
            pst.setString(5, huella_template_ansi378);
            
             int n = pst.executeUpdate();
            if (n>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return false;
    }
    
}
