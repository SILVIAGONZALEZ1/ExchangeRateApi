import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/68f2f72ba7261ffffe8d12b7/latest/USD"; // URL de la API

    public static void main(String[] args) {
        System.out.println("Sea bienvenido/a al Conversor de Moneda");
        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {
            System.out.println("\n1) Dólar a Peso argentino");
            System.out.println("2) Peso argentino a Dólar");
            System.out.println("3) Dólar a Real brasileño");
            System.out.println("4) Real brasileño a Dólar");
            System.out.println("5) Dólar a Peso colombiano");
            System.out.println("6) Peso colombiano a Dólar");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    convertirMoneda("USD", "ARS");
                    break;
                case 2:
                    convertirMoneda("ARS", "USD");
                    break;
                case 3:
                    convertirMoneda("USD", "BRL");
                    break;
                case 4:
                    convertirMoneda("BRL", "USD");
                    break;
                case 5:
                    convertirMoneda("USD", "COP");
                    break;
                case 6:
                    convertirMoneda("COP", "USD");
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Gracias por usar el conversor de moneda. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }

        scanner.close();
    }

    private static void convertirMoneda(String fromCurrency, String toCurrency) {
        try {
            // Obtener el JSON de la API
            String jsonResponse = getApiResponse(API_URL);

            // Guardar el JSON en un archivo utilizando GeneradorDeArchivos
            GeneradorDeArchivos.guardarJsonEnArchivo("tasas_de_conversion.json", jsonResponse);

            // Parsear el JSON y trabajar con las tasas de conversión
            Moneda moneda = parseJsonResponse(jsonResponse);
            Map<String, Double> tasas = moneda.conversion_rates();

            if (tasas.containsKey(fromCurrency) && tasas.containsKey(toCurrency)) {
                double tasaDeCambio = tasas.get(toCurrency) / tasas.get(fromCurrency);
                System.out.printf("La tasa de cambio de %s a %s es: %.2f\n", fromCurrency, toCurrency, tasaDeCambio);

                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese la cantidad en " + fromCurrency + ": ");
                double cantidad = scanner.nextDouble();
                double resultado = cantidad * tasaDeCambio;
                System.out.printf("%.2f %s equivalen a %.2f %s\n", cantidad, fromCurrency, resultado, toCurrency);
            } else {
                System.out.println("No se encontró información de las monedas seleccionadas.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener los datos de la API: " + e.getMessage());
        }
    }

    private static String getApiResponse(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Error en la conexión con la API: Código " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();
        return response.toString();
    }

    private static Moneda parseJsonResponse(String jsonResponse) {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Moneda.class);
    }
}


