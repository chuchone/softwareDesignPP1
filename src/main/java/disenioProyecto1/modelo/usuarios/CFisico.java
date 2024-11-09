/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.usuarios;
import disenioProyecto1.modelo.gestorBanco.GestionBanco;
import disenioProyecto1.capaDatos.validaciones.ValidacionesInternas;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesInternas.conseguirNombreDeUsuario;
/**
 *
 * @author Nelson
 */
public class CFisico extends Cliente {
    public String fechaNacimiento;
   
    public CFisico(int telefono, String correo, String nombre, int identificacion, String fechaNacimiento, int maxCuentas, String codigoCliente){
        String cuenta = conseguirNombreDeUsuario(correo);
        if (cuenta.equals("n")){return;}
        this.tipo = "fisico"; // cambiar con la capa de vista
        this.telefono = telefono;
        this.cuenta = cuenta;
        this.correo = correo;
        this.nombre = nombre;
        this.codigoCliente = codigoCliente;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.maxCuentas = maxCuentas;
        
        
    }
    public void setTelefono (int nuevoTelefono){this.telefono = nuevoTelefono;}
    


}