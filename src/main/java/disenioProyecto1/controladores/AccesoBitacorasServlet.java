
package disenioProyecto1.controladores;

/**
 *
 * @author Nelson
 */

import disenioProyecto1.integracion.FaceRecognitionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/AccesoBitacorasServlet")
public class AccesoBitacorasServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FaceRecognitionService faceRecognitionService = new FaceRecognitionService();

        // Ruta para guardar la imagen capturada
        String capturePath = "C:\\Users\\Usuario\\Desktop\\imagenesPrueba\\captura_usuario.jpg";
        
        // Capturar la imagen del usuario y guardarla en la ruta especificada
        String capturedImagePath = faceRecognitionService.captureImage(capturePath);

        if (capturedImagePath == null) {
            response.getWriter().println("Error: No se pudo capturar la imagen.");
            return;
        }

        // Cargar la imagen capturada y la imagen de referencia
        byte[] capturedImageBytes = faceRecognitionService.loadImageBytes(capturedImagePath);
        byte[] referenceImageBytes = faceRecognitionService.loadImageBytes("C:\\Users\\Usuario\\Desktop\\universidad\\2024_Semestre_2\\Disenio_de_software\\proyecto_1\\pp1\\web-app\\src\\main\\webapp\\images\\imagenReferencia.jpg");

        // Comparar ambas imágenes
        boolean faceMatch = faceRecognitionService.compareFaces(referenceImageBytes, capturedImageBytes);

        // Eliminar la imagen capturada después de la comparación
        File capturedImageFile = new File(capturedImagePath);
        if (capturedImageFile.exists() && !capturedImageFile.delete()) {
            System.out.println("Advertencia: No se pudo eliminar la imagen capturada.");
        }

        faceRecognitionService.close(); // Cerrar el cliente de Rekognition

        if (faceMatch) {
            // Si la verificación es exitosa, redirige a la página de bitácoras
            response.sendRedirect("verBitacoras.jsp");
        } else {
            // Si no coincide, muestra un mensaje de error
            response.getWriter().println("Error: No se pudo verificar la identidad.");
        }
    }
}
