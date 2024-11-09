/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.gestorBanco.EstadoCuentaDecorador;

import com.itextpdf.text.DocumentException;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.EstadoCuenta;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.IGeneradorEstadoCuenta;
import java.io.IOException;

/**
 *
 * @author Nelson
 */
public abstract class DecoradorEstadoCuenta implements IGeneradorEstadoCuenta{
    protected IGeneradorEstadoCuenta estadoDecorado;

    public DecoradorEstadoCuenta(IGeneradorEstadoCuenta estadoDecorado) {
      this.estadoDecorado = estadoDecorado;
    }

    @Override
    public String crearEstadoCuenta(EstadoCuenta estadoCuenta) throws IOException, DocumentException {
      return estadoDecorado.crearEstadoCuenta(estadoCuenta);
    }
}
