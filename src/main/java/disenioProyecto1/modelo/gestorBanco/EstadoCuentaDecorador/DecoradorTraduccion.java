/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.modelo.gestorBanco.EstadoCuentaDecorador;

/**
 *
 * @author Nelson
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import disenioProyecto1.integracion.CorreoElectronico;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.*;
import disenioProyecto1.modelo.gestorBanco.EstadoCuenta.IGeneradorEstadoCuenta;
import disenioProyecto1.modelo.gestorBanco.Transaccion;
import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DecoradorTraduccion extends DecoradorEstadoCuenta {

  private final Language idioma;

  public DecoradorTraduccion(IGeneradorEstadoCuenta generadorBase, Language idioma) {
    super(generadorBase);
    this.idioma = idioma;
  }

  @Override
  public String crearEstadoCuenta(EstadoCuenta estadoCuenta) throws IOException, DocumentException {

    String fileName = estadoDecorado.crearEstadoCuenta(estadoCuenta);
    PdfReader reader = new PdfReader(fileName); 
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName, true)); // Modo de apéndice
    document.open();

    PdfContentByte cb = writer.getDirectContent();
    PdfImportedPage page;
    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
      document.newPage();
      page = writer.getImportedPage(reader, i);
      cb.addTemplate(page, 0, 0);
    }

    document.newPage();

    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, new BaseColor(0, 51, 102));
    Paragraph translatedTitle = new Paragraph(traducirTexto("Estado de Cuenta en " + estadoCuenta.moneda), titleFont);
    translatedTitle.setAlignment(Element.ALIGN_CENTER);
    translatedTitle.setSpacingAfter(20f);
    document.add(translatedTitle);

    Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
    PdfPTable translatedInfoTable = new PdfPTable(2);
    translatedInfoTable.setWidthPercentage(100);
    translatedInfoTable.setSpacingBefore(10f);
    translatedInfoTable.setWidths(new int[]{1, 3});

    addInfoRow(translatedInfoTable, traducirTexto("Número de Cuenta:"), estadoCuenta.numeroCuenta, infoFont);
    addInfoRow(translatedInfoTable, traducirTexto("Nombre del Cliente:"), estadoCuenta.nombreCliente, infoFont);
    addInfoRow(translatedInfoTable, traducirTexto("Saldo Actual:"), String.format("%.2f", estadoCuenta.saldoActual / estadoCuenta.tipoCambio), infoFont);
    addInfoRow(translatedInfoTable, traducirTexto("Fecha de Generación:"), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), infoFont);
    document.add(translatedInfoTable);

    PdfPTable translatedTable = new PdfPTable(6);
    translatedTable.setWidthPercentage(100);
    translatedTable.setSpacingBefore(20f);
    translatedTable.setWidths(new int[]{2, 2, 2, 2, 2, 2});

    String[] headers = {"Tipo Transacción", "Monto", "Dinero en Cuenta", "Fecha", "Comisión", "Número de Cuenta"};
    Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
    for (String header : headers) {
      PdfPCell headerCell = new PdfPCell(new Phrase(traducirTexto(header), headerFont));
      headerCell.setBackgroundColor(new BaseColor(0, 51, 102));
      headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      headerCell.setPadding(8f);
      translatedTable.addCell(headerCell);
    }

    for (Transaccion transaccion : estadoCuenta.transacciones) {
      translatedTable.addCell(traducirTexto(transaccion.tipoTransaccion));
      translatedTable.addCell(String.format("%.2f", transaccion.monto / estadoCuenta.tipoCambio));
      translatedTable.addCell(String.format("%.2f", transaccion.dineroEnCuenta / estadoCuenta.tipoCambio));
      translatedTable.addCell(transaccion.fecha);
      translatedTable.addCell(String.format("%.2f", transaccion.comision / estadoCuenta.tipoCambio));
      translatedTable.addCell(transaccion.numCuentaQuePertenese);
    }

    document.add(translatedTable);
    document.close();
    reader.close();

    String asunto = "Estado de Cuenta en Dólares " + estadoCuenta.numeroCuenta;
    String mensaje = "Adjunto se encuentra el estado de cuenta solicitado.";

    CorreoElectronico.mandar(estadoCuenta.emailDestino, asunto, mensaje, fileName);

    return fileName; 
  }

  private void addInfoRow(PdfPTable table, String label, String value, Font font) {
    table.addCell(createCell(label, font, BaseColor.LIGHT_GRAY));
    table.addCell(createCell(value, font, BaseColor.WHITE));
  }

  private String traducirTexto(String texto) {
    try {
      return Translator.translate(Language.SPANISH, idioma, texto);
    } catch (Exception e) {
      e.printStackTrace();
      return texto; 
    }
  }

  private PdfPCell createCell(String text, Font font, BaseColor backgroundColor) {
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    cell.setBackgroundColor(backgroundColor);
    cell.setPadding(5f);
    cell.setBorderWidth(0.5f);
    return cell;
  }
}
