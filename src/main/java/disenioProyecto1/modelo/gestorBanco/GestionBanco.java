/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.gestorBanco;

/**
 *
 * @author Nelson
 */


import disenioProyecto1.modelo.gestorBanco.CuentaBancaria;
import com.itextpdf.text.DocumentException;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosBitacoras.obtenerListaBitacoras;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCFisico.obtenerListaClientesFisicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCJuridico.obtenerListaClientesJuridicos;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosCuentaBancaria.obtenerCuentasBancarias;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosRegistros.obtenerTransacciones;
import static disenioProyecto1.capaDatos.conexionSql.BaseDeDatosConteoUsers.*;
import disenioProyecto1.integracion.AnalisisSentimientosAWS;
import static disenioProyecto1.integracion.ConexionBCCR.obtenerTipoCambio;
import disenioProyecto1.integracion.GenerarPDF;
import static disenioProyecto1.integracion.GenerarPDF.*;
import static disenioProyecto1.modelo.bitacoras.AnalisisBitacoras.analizarUltimasBitacoras;
import disenioProyecto1.modelo.bitacoras.Bitacoras;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.EstadoCuenta;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.IGeneradorEstadoCuenta;
import disenioProyecto1.modelo.gestorBanco.EstadoCuentaDecorador.DecoradorTraduccion;
import java.util.ArrayList;
import java.util.List;
import disenioProyecto1.modelo.usuarios.CJuridico;
import disenioProyecto1.modelo.usuarios.CFisico;
import java.io.IOException;
import java.sql.SQLException;
import net.suuft.libretranslate.Language;

public class GestionBanco {


    public static String generarCodigoCliente() {      
        return "CTE" + numClientesCreados();
    }
    public static String generarCodigoCuentaBancaria() {
        return "CTA" + numCuentasCreadas();
    }
    public static String generarCodigoBitacora(){
        return "BTC" + incrementarConteoBitacoras();
    }


    public static double obtenerComisiones(String numeroCuenta) throws SQLException{
    
        List<Transaccion> listaTransacciones = obtenerTransacciones();
        double cantidad = 0;
        for(Transaccion transaccion: listaTransacciones){
        
            if(transaccion.numCuentaQuePertenese.equals(numeroCuenta)){
                cantidad = cantidad + transaccion.comision;
            
            }
        
        }
        listaTransacciones.clear();
        return cantidad;
    }
    public static boolean prevMandarStatusDolares(String numeroCuenta, String idioma) throws SQLException, IOException, DocumentException {
        List<Transaccion> listaTransaccionesDelUsuario = ObtenerlistaTransacciones(numeroCuenta);
        CuentaBancaria cuenta = obtenerCuenta(numeroCuenta);
        if (cuenta != null && listaTransaccionesDelUsuario != null) {
          long identificacion = cuenta.cedulaPersonaAsociada;
          Language language = Language.valueOf(idioma.toUpperCase());
          double tipoCambio = obtenerTipoCambio("318");

          if (identificacion <= 999999999) {
            CFisico cliente = obtenerCFisico(identificacion);
            EstadoCuenta estado = new EstadoCuenta(cliente.correo, numeroCuenta, cliente.nombre, cuenta.dineroEnLaCuenta, listaTransaccionesDelUsuario, tipoCambio, "Dólares");
            IGeneradorEstadoCuenta decorador = new DecoradorTraduccion(new GenerarPDF(), language);
            decorador.crearEstadoCuenta(estado);
            return true;
          } else {
            CJuridico cliente = obtenerCJuridico(identificacion);
            EstadoCuenta estado = new EstadoCuenta(cliente.correo, numeroCuenta, cliente.nombre, cuenta.dineroEnLaCuenta, listaTransaccionesDelUsuario, tipoCambio, "Dólares");
            IGeneradorEstadoCuenta decorador = new DecoradorTraduccion(new GenerarPDF(), language);
            decorador.crearEstadoCuenta(estado);
            return true;
          }
        }
        return false;
      }

      public static boolean prevMandarStatus(String numeroCuenta, String idioma) throws SQLException, IOException, DocumentException {
        List<Transaccion> listaTransaccionesDelUsuario = ObtenerlistaTransacciones(numeroCuenta);
        CuentaBancaria cuenta = obtenerCuenta(numeroCuenta);
        if (cuenta != null && listaTransaccionesDelUsuario != null) {
          long identificacion = cuenta.cedulaPersonaAsociada;
          Language language = Language.valueOf(idioma.toUpperCase());
          double tipoCambio = 1;

          if (identificacion <= 999999999) {
            CFisico cliente = obtenerCFisico(identificacion);
            EstadoCuenta estado = new EstadoCuenta(cliente.correo, numeroCuenta, cliente.nombre, cuenta.dineroEnLaCuenta, listaTransaccionesDelUsuario, tipoCambio, "Colones");
            IGeneradorEstadoCuenta decorador = new DecoradorTraduccion(new GenerarPDF(), language);
            decorador.crearEstadoCuenta(estado);
            return true;
          } else {
            CJuridico cliente = obtenerCJuridico(identificacion);
            EstadoCuenta estado = new EstadoCuenta(cliente.correo, numeroCuenta, cliente.nombre, cuenta.dineroEnLaCuenta, listaTransaccionesDelUsuario, tipoCambio, "Colones");
            IGeneradorEstadoCuenta decorador = new DecoradorTraduccion(new GenerarPDF(), language);
            decorador.crearEstadoCuenta(estado);
            return true;
          }
        }
        return false;
      }
    public static List<Transaccion> ObtenerlistaTransacciones (String numeroCuenta) throws SQLException{
        List<Transaccion> listaTransacciones = obtenerTransacciones();
        ArrayList<Transaccion> listaTransaccionesDelUsuario = new ArrayList<>(); 

        for (Transaccion obj: listaTransacciones){
            if (obj.numCuentaQuePertenese.equals(numeroCuenta)){
                listaTransaccionesDelUsuario.add(obj);
            }
        }    
        return listaTransaccionesDelUsuario;     
    }
    public static CuentaBancaria obtenerCuenta (String numCuenta) throws SQLException{
    
        List<CuentaBancaria> listaCuentas = obtenerCuentasBancarias();
        for(CuentaBancaria cuenta : listaCuentas){
        
            if(cuenta.numeroCuenta.equals(numCuenta)){
                return cuenta;
            }
        }
        return null;
    }       
        
    public static CFisico obtenerCFisico(long identificacion) throws SQLException{
        List<CFisico> listaDeCFisico = obtenerListaClientesFisicos();
        for(CFisico cliente: listaDeCFisico){
            if(cliente.identificacion == identificacion){
                listaDeCFisico.clear();
                return cliente;           
            }        
        }
        listaDeCFisico.clear();
        return null;
    }
    public static CJuridico obtenerCJuridico(long identificacion) throws SQLException{
        
        List<CJuridico> listaDeCJuridico = obtenerListaClientesJuridicos();
        for(CJuridico cliente: listaDeCJuridico){
            if(cliente.identificacion == identificacion){
                listaDeCJuridico.clear();
                return cliente;              
            }            
        }
        listaDeCJuridico.clear();
        return null;
    
    }
    public static String analisisSentimientos (List<Bitacoras> bitacoras) throws SQLException{            
        String text = analizarUltimasBitacoras(bitacoras);
        System.out.println(text);
        //AnalisisSentimientosAWS sentimentAnalysis = new AnalisisSentimientosAWS();
        //String sentimentResult = sentimentAnalysis.analyzeSentiment(text);
        return text;
    }
        
}
  