package disenioProyecto1.integracion;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CifradorDES {

    // Definir una clave estática (debe ser de 56 bits, 8 bytes)
    private static final String SECRET_KEY = "12345678"; // 8 caracteres para DES
    private static final SecretKey KEY = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");

    public static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, KEY);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, KEY);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

    public static String encriptarPIN(String pinAEncriptar) throws Exception {
        return encrypt(pinAEncriptar);
    }
    public static String desencriptrarPIN(String pinEncriptado) throws Exception {
        return decrypt(pinEncriptado);
    }
}