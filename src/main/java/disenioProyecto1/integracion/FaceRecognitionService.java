
package disenioProyecto1.integracion;


/**
 *
 * @author Nelson
 */
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;
import software.amazon.awssdk.core.SdkBytes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FaceRecognitionService {

    private final RekognitionClient rekognitionClient;
    private static final String CAPTURES_DIR = "C:\\Users\\Usuario\\Desktop\\universidad\\2024_Semestre_2\\Disenio_de_software\\proyecto_1\\pp1\\web-app\\src\\main\\webapp\\images";

    public FaceRecognitionService() {
        this.rekognitionClient = RekognitionClient.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }
    public byte[] loadImageBytes(String imagePath) throws IOException {
        return Files.readAllBytes(Paths.get(imagePath));
    }


    public String captureImage(String cedula) {
        Webcam webcam = Webcam.getDefault();
        if (webcam == null) {
            System.out.println("Error: No se encontró ninguna cámara disponible.");
            return null;
        }

        webcam.setViewSize(WebcamResolution.VGA.getSize());

        if (!webcam.open()) {
            System.out.println("Error: No se pudo abrir la cámara.");
            return null;
        }

        String filename = CAPTURES_DIR + "/captura_" + cedula + ".jpg";
        
        try {
            BufferedImage image = webcam.getImage();
            
            if (image == null) {
                System.out.println("Error: No se pudo capturar la imagen (image == null).");
                return null;
            }
            
            ImageIO.write(image, "JPG", new File(filename));
            System.out.println("Imagen guardada correctamente en: " + filename);
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen: " + e.getMessage());
            return null;
        } finally {
            webcam.close();
        }

        return filename;
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
        software.amazon.awssdk.services.rekognition.model.Image source = software.amazon.awssdk.services.rekognition.model.Image.builder()
                .bytes(SdkBytes.fromByteArray(sourceImageBytes))
                .build();
        software.amazon.awssdk.services.rekognition.model.Image target = software.amazon.awssdk.services.rekognition.model.Image.builder()
                .bytes(SdkBytes.fromByteArray(targetImageBytes))
                .build();

        CompareFacesRequest request = CompareFacesRequest.builder()
                .sourceImage(source)
                .targetImage(target)
                .similarityThreshold(80F)
                .build();

        CompareFacesResponse response = rekognitionClient.compareFaces(request);

        return !response.faceMatches().isEmpty();
    }

    public void close() {
        rekognitionClient.close();
    }
}
