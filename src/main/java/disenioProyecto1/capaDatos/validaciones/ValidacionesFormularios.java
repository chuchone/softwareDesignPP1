package disenioProyecto1.capaDatos.validaciones;

import disenioProyecto1.gestorBanco.GestionBanco;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionesFormularios {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.length() == 8 && telefono.matches("\\d+");
    }
    
    public static boolean validarTelefonoCJuridico(String telefono) {
        return telefono != null && telefono.length() == 8 && telefono.matches("\\d+");
    }
    
    

    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarMaxCuentas(String maxCuentas) {
        if (maxCuentas == null || maxCuentas.isEmpty()) {
            return false;
        }
        try {
            int maxC = Integer.parseInt(maxCuentas);
            return maxC > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarCedulaJuridica(String cedulaJuridica) {
        return cedulaJuridica != null && cedulaJuridica.length() == 11 && cedulaJuridica.matches("\\d+");
    }

    public static boolean validarCamposRequeridos(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    
    public static boolean validarIdentificacion(String identificacion){
    
       // GestionBa
       return true;
    }
}
