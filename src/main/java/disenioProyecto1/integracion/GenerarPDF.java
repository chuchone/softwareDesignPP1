/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.integracion;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import disenioProyecto1.modelo.gestorBanco.Transaccion;
import static disenioProyecto1.integracion.ConexionBCCR.obtenerTipoCambio;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Nelson
 */
public class GenerarPDF {

    
    public static String crearEstadoCuenta(String emailDestino, String numeroCuenta, String nombreCliente, double saldoActual, List<Transaccion> transacciones)throws IOException, DocumentException {
        
        String fileName = "EstadoCuenta_" + numeroCuenta + ".pdf";
        // Aquí generas el PDF como antes
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        
        // Añadir título
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Paragraph title = new Paragraph("Estado de Cuenta", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
        
        // Añadir información del cliente
        document.add(new Paragraph("Número de Cuenta: " + numeroCuenta));
        document.add(new Paragraph("Nombre del Cliente: " + nombreCliente));
        document.add(new Paragraph("Saldo Actual: ₡" + String.format("%.2f", saldoActual)));
        document.add(new Paragraph("Fecha de Generación: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        document.add(Chunk.NEWLINE);
        
        // Añadir tabla de transacciones
        PdfPTable table = new PdfPTable(6); // 6 columnas
        table.setWidthPercentage(100);
        
        // Añadir encabezados de tabla
        table.addCell("Tipo Transacción");
        table.addCell("Monto");
        table.addCell("Dinero en Cuenta");
        table.addCell("Fecha");
        table.addCell("Comisión");
        table.addCell("Número de Cuenta");
        
        // Añadir filas de transacciones
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Transaccion transaccion : transacciones) {
            table.addCell(transaccion.tipoTransaccion);
            table.addCell(String.format("%.2f", transaccion.monto));
            table.addCell(String.format("%.2f", transaccion.dineroEnCuenta));
            table.addCell(transaccion.fecha);
            table.addCell(String.format("%.2f", transaccion.comision));
            table.addCell(transaccion.numCuentaQuePertenese);
        }
        
        document.add(table);
        
        document.close();
        String asunto = "Estado de Cuenta " + numeroCuenta;
        String mensaje = "Adjunto se encuentra el estado de cuenta solicitado.";
        
        CorreoElectronico.mandar(emailDestino, asunto, mensaje, fileName);
        
        return fileName;
    }
    
    
    public static String crearEstadoCuentaDolares(String emailDestino, String numeroCuenta, String nombreCliente, double saldoActual, List<Transaccion> transacciones) throws IOException, DocumentException {
        
        // Obtener tipo de cambio
        double tipoCambio = obtenerTipoCambio("318");
        double saldoActualDolares = saldoActual / tipoCambio;
        
        String fileName = "EstadoCuentaDolares_" + numeroCuenta + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        
        // Añadir título
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Paragraph title = new Paragraph("Estado de Cuenta en Dólares", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
        
        // Añadir información del cliente
        document.add(new Paragraph("Número de Cuenta: " + numeroCuenta));
        document.add(new Paragraph("Nombre del Cliente: " + nombreCliente));
        document.add(new Paragraph("Saldo Actual: $" + String.format("%.2f", saldoActualDolares)));
        document.add(new Paragraph("Fecha de Generación: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        document.add(Chunk.NEWLINE);
        
        // Añadir tabla de transacciones
        PdfPTable table = new PdfPTable(6); // 6 columnas
        table.setWidthPercentage(100);
        
        // Añadir encabezados de tabla
        table.addCell("Tipo Transacción");
        table.addCell("Monto");
        table.addCell("Dinero en Cuenta");
        table.addCell("Fecha");
        table.addCell("Comisión");
        table.addCell("Número de Cuenta");
        
        // Añadir filas de transacciones
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Transaccion transaccion : transacciones) {
            table.addCell(transaccion.tipoTransaccion);
            table.addCell(String.format("%.2f", transaccion.monto));
            table.addCell(String.format("%.2f", transaccion.dineroEnCuenta / tipoCambio));
            table.addCell(transaccion.fecha);
            table.addCell(String.format("%.2f", transaccion.comision / tipoCambio));
            table.addCell(transaccion.numCuentaQuePertenese);
        }
        
        document.add(table);
        
        document.close();
        String asunto = "Estado de Cuenta en Dólares " + numeroCuenta;
        String mensaje = "Adjunto se encuentra el estado de cuenta solicitado.";
        
        CorreoElectronico.mandar(emailDestino, asunto, mensaje, fileName);
        
        return fileName;
    }
    


    
}