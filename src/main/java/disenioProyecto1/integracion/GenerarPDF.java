/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.integracion;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import disenioProyecto1.modelo.gestorBanco.Transaccion;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.EstadoCuenta;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.IGeneradorEstadoCuenta;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nelson
 */
public class GenerarPDF implements IGeneradorEstadoCuenta {

    @Override
    public String crearEstadoCuenta(EstadoCuenta estadoCuenta) throws IOException, DocumentException {
        String fileName = "EstadoCuenta" + estadoCuenta.moneda + "_" + estadoCuenta.numeroCuenta + ".pdf";
        Document document = new Document();
        document.setMargins(36, 36, 36, 36);
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, new BaseColor(0, 51, 102));
        Paragraph title = new Paragraph("Estado de Cuenta en " + estadoCuenta.moneda, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);

        Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingBefore(10f);
        infoTable.setWidths(new int[]{1, 3});

        infoTable.addCell(createCell("Número de Cuenta:", infoFont, BaseColor.LIGHT_GRAY));
        infoTable.addCell(createCell(estadoCuenta.numeroCuenta, infoFont, BaseColor.WHITE));
        infoTable.addCell(createCell("Nombre del Cliente:", infoFont, BaseColor.LIGHT_GRAY));
        infoTable.addCell(createCell(estadoCuenta.nombreCliente, infoFont, BaseColor.WHITE));
        infoTable.addCell(createCell("Saldo Actual:", infoFont, BaseColor.LIGHT_GRAY));
        infoTable.addCell(createCell(String.format("%.2f", estadoCuenta.saldoActual/estadoCuenta.tipoCambio), infoFont, BaseColor.WHITE));
        infoTable.addCell(createCell("Fecha de Generación:", infoFont, BaseColor.LIGHT_GRAY));
        infoTable.addCell(createCell(new SimpleDateFormat("dd-MM-yyyy").format(new Date()), infoFont, BaseColor.WHITE));
        document.add(infoTable);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setWidths(new int[]{2, 2, 2, 2, 2, 2});

        String[] headers = {"Tipo Transacción", "Monto", "Dinero en Cuenta", "Fecha", "Comisión", "Número de Cuenta"};
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
        for (String header : headers) {
          PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
          headerCell.setBackgroundColor(new BaseColor(0, 51, 102)); // Color de fondo
          headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
          headerCell.setPadding(8f);
          table.addCell(headerCell);
        }

        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
        BaseColor[] rowColors = {BaseColor.WHITE, new BaseColor(230, 240, 255)};
        int rowIndex = 0;

        for (Transaccion transaccion : estadoCuenta.transacciones) {
          BaseColor rowColor = rowColors[rowIndex % 2];
          table.addCell(createDataCell(transaccion.tipoTransaccion, dataFont, rowColor));
          table.addCell(createDataCell(String.format("%.2f", transaccion.monto/estadoCuenta.tipoCambio), dataFont, rowColor));
          table.addCell(createDataCell(String.format("%.2f", transaccion.dineroEnCuenta/estadoCuenta.tipoCambio), dataFont, rowColor));
          table.addCell(createDataCell(transaccion.fecha, dataFont, rowColor));
          table.addCell(createDataCell(String.format("%.2f", transaccion.comision/estadoCuenta.tipoCambio), dataFont, rowColor));
          table.addCell(createDataCell(transaccion.numCuentaQuePertenese, dataFont, rowColor));
          rowIndex++;
        }

        document.add(table);
        document.close();
        return fileName;
    }

    private PdfPCell createCell(String text, Font font, BaseColor backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(backgroundColor);
        cell.setPadding(5f);
        cell.setBorderWidth(0.5f);
        return cell;
    }

    private PdfPCell createDataCell(String text, Font font, BaseColor backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(backgroundColor);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5f);
        cell.setBorderWidth(0.5f);
        return cell;
    }
}