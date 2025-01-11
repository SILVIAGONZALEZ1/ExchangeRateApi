import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivos {

    /**
     * Guarda un JSON en un archivo especificado.
     *
     * @param nombreArchivo Nombre del archivo donde se guardará el JSON.
     * @param contenidoJson El contenido JSON a guardar.
     */
    public static void guardarJsonEnArchivo(String nombreArchivo, String contenidoJson) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(contenidoJson);
            System.out.println("JSON guardado correctamente en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}

