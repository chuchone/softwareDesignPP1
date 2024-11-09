/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.gestorBanco.EstadoCuenta;

import com.itextpdf.text.DocumentException;
import java.io.IOException;

/**
 *
 * @author Nelson
 */
public interface IGeneradorEstadoCuenta {
    public String crearEstadoCuenta(EstadoCuenta estadoCuenta) throws IOException, DocumentException;
}
