package disenioProyecto1.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "AudioServlet", urlPatterns = {"/AudioServlet"})
public class AudioServlet extends HttpServlet {

    private static final String AUDIO_DIRECTORY = "C:\\Users\\Usuario\\Desktop\\Nueva carpeta\\audios";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("file");

        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre del archivo no proporcionado.");
            return;
        }

        File audioFile = new File(AUDIO_DIRECTORY, fileName);

        if (!audioFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "El archivo no existe.");
            return;
        }

        response.setContentType("audio/mpeg");
        response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

        try (FileInputStream inputStream = new FileInputStream(audioFile);
             OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
