/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.usuarios;
import disenioProyecto1.gestorBanco.GestionBanco;
import disenioProyecto1.capaDatos.validaciones.ValidacionesInternas;
import static disenioProyecto1.capaDatos.validaciones.ValidacionesInternas.conseguirNombreDeUsuario;
/**
 *
 * @author Nelson
 */
public class CFisico extends Cliente {
    private String fechaNacimiento;
   
    public CFisico(int telefono, String correo, String nombre, int identificacion, String fechaNacimiento, int maxCuentas){
        String cuenta = conseguirNombreDeUsuario(correo);
        if (cuenta.equals("n")){return;}
        this.tipo = "fisico"; // cambiar con la capa de vista
        this.telefono = telefono;
        this.cuenta = cuenta;
        this.correo = correo;
        this.nombre = nombre;
        this.codigoCliente = GestionBanco.generarCodigoCliente();
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.maxCuentas = maxCuentas;
        
        
    }
    public String getfechaNacimiento(){return fechaNacimiento;}
    


}