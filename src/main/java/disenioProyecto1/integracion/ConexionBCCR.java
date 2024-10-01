/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.integracion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nelson
 */
public class ConexionBCCR {
    
    public static double obtenerTipoCambio(String tipoOperacion){
        String tipoDeCambio = obtenerTipoDeCambio(tipoOperacion);
        double valor = Double.parseDouble(tipoDeCambio);
        return valor;
    }
    
    public static double convertirDolaresAColones(double montoDolares, double tipoCambio) {
        return montoDolares * tipoCambio;
    }
    
    public static String obtenerTipoDeCambio(String tipoDeOperacion)
    {
        try {
            // Crear el mensaje SOAP
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();

            // Crear el objeto SOAP
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("ws", "http://ws.sdde.bccr.fi.cr");

            SOAPBody soapBody = envelope.getBody();
            SOAPElement soapBodyElem = soapBody.addChildElement("ObtenerIndicadoresEconomicos", "ws");
            soapBodyElem.addChildElement("Indicador", "ws").addTextNode(tipoDeOperacion); 
            soapBodyElem.addChildElement("FechaInicio", "ws").addTextNode("26/09/2024");
            soapBodyElem.addChildElement("FechaFinal", "ws").addTextNode("26/09/2024");
            soapBodyElem.addChildElement("Nombre", "ws").addTextNode("Nelson Chavarria Aragón");
            soapBodyElem.addChildElement("SubNiveles", "ws").addTextNode("N");
            soapBodyElem.addChildElement("CorreoElectronico", "ws").addTextNode("jesuschavarria483@gmail.com");
            soapBodyElem.addChildElement("Token", "ws").addTextNode("M9SNM4E291");

            // Guardar los cambios
            soapMessage.saveChanges();

            // Añadir el encabezado SOAPAction
            MimeHeaders headers = soapMessage.getMimeHeaders();
            headers.addHeader("SOAPAction", "http://ws.sdde.bccr.fi.cr/ObtenerIndicadoresEconomicos");

            // Imprimir la solicitud SOAP
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapMessage.writeTo(out);
            String strMsg = new String(out.toByteArray());

            // Enviar la solicitud SOAP
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx";
            SOAPMessage soapResponse = soapConnection.call(soapMessage, url);

            // Procesar la respuesta SOAP
            ByteArrayOutputStream responseOut = new ByteArrayOutputStream();
            soapResponse.writeTo(responseOut);
            String strResponse = new String(responseOut.toByteArray());

            String salida = obtenerNumValor(strResponse);
            
            soapConnection.close();
            return salida;
        } catch (Exception e) {
            e.printStackTrace();
            return "falla";
        }
        
                
    }
    
    
    
    public static String obtenerNumValor(String strResponse) {
        try {
            // Crear un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Convertir la cadena XML a un objeto Document
            Document doc = builder.parse(new ByteArrayInputStream(strResponse.getBytes("UTF-8")));

            // Normalizar el documento
            doc.getDocumentElement().normalize();

            // Buscar el elemento NUM_VALOR
            NodeList numValorList = doc.getElementsByTagName("NUM_VALOR");
            if (numValorList.getLength() > 0) {
                return numValorList.item(0).getTextContent();
            } else {
                return null; // O manejar el caso donde no se encuentra NUM_VALOR
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // O manejar la excepción adecuadamente
        }
    }    
    
    
}
