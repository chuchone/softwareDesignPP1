/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.usuarios;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesInternas.conseguirNombreDeUsuario;
import disenioProyecto1.gestorBanco.GestionBanco;
/**
 *
 * @author Nelson
 */
public class CJuridico extends Cliente {
    public String tipoNegocio;
    public String razonSocial;
    
    public CJuridico (String nombre, int telefono, String correo, String tipoNegocio, String razonSocial, long cedulaJuridicaLong){
        String cuenta = conseguirNombreDeUsuario(correo);

        this.tipoNegocio = tipoNegocio;
        this.tipo = "Juridico"; // cambiar con la capa de vista
        this.razonSocial = razonSocial;
        this.identificacion = cedulaJuridicaLong;
        this.maxCuentas = 999999999; // mejorar
        this.nombre = nombre;
        this.telefono = telefono;
        this.cuenta = cuenta;
        this.codigoCliente = GestionBanco.generarCodigoCliente();
        this.correo = correo;

    }

}
