/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.gestorBanco.EstadoCuenta;

import disenioProyecto1.modelo.gestorBanco.Transaccion;
import java.util.List;

/**
 *
 * @author Keeler
 */
public class EstadoCuenta {
  public String emailDestino;
  public String numeroCuenta;
  public String nombreCliente;
  public double saldoActual;
  public double tipoCambio;
  public String moneda;
  public List<Transaccion> transacciones;

  public EstadoCuenta(String emailDestino, String numeroCuenta, String nombreCliente, double saldoActual, List<Transaccion> transacciones, double tipoCambio, String moneda) {
    this.emailDestino = emailDestino;
    this.numeroCuenta = numeroCuenta;
    this.nombreCliente = nombreCliente;
    this.saldoActual = saldoActual;
    this.transacciones = transacciones;
    this.tipoCambio = tipoCambio;
    this.moneda = moneda;
  }
}