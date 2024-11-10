/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.gestorBanco;

import java.sql.SQLException;

/**
 *
 * @author Nelson
 */
public interface IBasicosBanco {
    public void depositar(double cant, String ipAddress, String userAgent, String country) throws SQLException;
    public void retirar(double cant, String ipAddress, String userAgent, String country) throws SQLException;
}
