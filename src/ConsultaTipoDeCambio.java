import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ConsultaTipoDeCambio {

    private static final String API_KEY = "68f2f72ba7261ffffe8d12b7"; // Reemplaza con tu clave de API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    /**
     * Obtiene los datos de tasas de conversión para la moneda base especificada.
     *
     * @param base Código de la moneda base (por ejemplo, "USD").
     * @return Objeto Moneda con los datos obtenidos de la API.
     * @throws IOException            Si ocurre un error de E/S.
     * @throws InterruptedException   Si la operación es interrumpida.
     */
    public Moneda obtenerTasasDeConversion(String base) throws IOException, InterruptedException {
        String url = BASE_URL + base; // URL completa para la solicitud
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() == 200) {
            // Convertir la respuesta JSON en un objeto Moneda
            return new Gson().fromJson(respuesta.body(), Moneda.class);
        } else {
            throw new RuntimeException("Error al obtener datos de la API: Código " + respuesta.statusCode());
        }
    }
}
