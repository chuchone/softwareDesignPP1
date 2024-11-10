
package disenioProyecto1.integracion;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Nelson
 */

public class FaceRecognitionService {

    private final RekognitionClient rekognitionClient;
    private static final String CAPTURES_DIR = "capturas"; 
    private static AtomicInteger imageCounter = new AtomicInteger(1); 

    public FaceRecognitionService() {
        this.rekognitionClient = RekognitionClient.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        
        File directory = new File(CAPTURES_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public byte[] captureImage() {
        Webcam webcam = Webcam.getDefault();
        if (webcam == null) {
            System.out.println("No se detectó ninguna cámara.");
            return null;
        }
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        try {
            // Captura la imagen desde la cámara
            BufferedImage image = webcam.getImage();
            webcam.close();

            // Guarda la imagen en el directorio de capturas
            String filename = CAPTURES_DIR + "/captured_image_" + imageCounter.getAndIncrement() + ".jpg";
            ImageIO.write(image, "JPG", new File(filename));
            System.out.println("Imagen capturada y guardada como " + filename);

            // Convierte la imagen capturada a un arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            return baos.toByteArray();

        } catch (IOException e) {
            System.out.println("Error al capturar o guardar la imagen: " + e.getMessage());
            return null;
        }
    }

    public byte[] loadImageBytes(String imagePath) throws IOException {
        return Files.readAllBytes(Paths.get(imagePath));
    }

    public List<FaceDetail> detectFacesInImage(byte[] imageBytes) {
        software.amazon.awssdk.services.rekognition.model.Image image = software.amazon.awssdk.services.rekognition.model.Image.builder()
                .bytes(SdkBytes.fromByteArray(imageBytes))
                .build();

        DetectFacesRequest detectFacesRequest = DetectFacesRequest.builder()
                .image(image)
                .attributes(Attribute.ALL)
                .build();

        DetectFacesResponse detectFacesResponse = rekognitionClient.detectFaces(detectFacesRequest);
        return detectFacesResponse.faceDetails();
    }

    public boolean compareFaces(byte[] sourceImageBytes, byte[] targetImageBytes) {
        try {
            // Verifica si hay rostros en ambas imágenes
            List<FaceDetail> sourceFaces = detectFacesInImage(sourceImageBytes);
            List<FaceDetail> targetFaces = detectFacesInImage(targetImageBytes);

            if (sourceFaces.isEmpty() || targetFaces.isEmpty()) {
                System.out.println("Una o ambas imágenes no contienen caras detectables.");
                return false;
            }

            // Crea las imágenes para la comparación
            software.amazon.awssdk.services.rekognition.model.Image source = software.amazon.awssdk.services.rekognition.model.Image.builder()
                    .bytes(SdkBytes.fromByteArray(sourceImageBytes))
                    .build();
            software.amazon.awssdk.services.rekognition.model.Image target = software.amazon.awssdk.services.rekognition.model.Image.builder()
                    .bytes(SdkBytes.fromByteArray(targetImageBytes))
                    .build();

            // Realiza la comparación
            CompareFacesRequest request = CompareFacesRequest.builder()
                    .sourceImage(source)
                    .targetImage(target)
                    .similarityThreshold(80F)  // Ajustar el umbral según sea necesario
                    .build();

            CompareFacesResponse response = rekognitionClient.compareFaces(request);

            return !response.faceMatches().isEmpty();
        } catch (InvalidParameterException e) {
            System.out.println("Error en la comparación de imágenes: parámetros inválidos. Verifica las imágenes.");
            return false;
        } catch (Exception e) {
            System.out.println("Ocurrió un error durante la comparación: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        rekognitionClient.close();
    }
}
