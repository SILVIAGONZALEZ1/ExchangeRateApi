import java.util.Map;

public record Moneda(String resultado,
                     String documentacion,
                     String terminosDeUso,
                     long horaUltimaActualizacionUnix,
                     String timeLastUpdateUtc,
                     long horaProximaActualizacionUnix,
                     String timeNextUpdateUtc,
                     String codigoBase,
                     Map<String, Double> conversion_rates) {

}