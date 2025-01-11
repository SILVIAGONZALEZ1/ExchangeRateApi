import java.util.Map;

public record ExchangeRateResponse(String resultado,
                                   String documentacion,
                                   String terminosDeUso,
                                   long horaUltimaActualizacionUnix,
                                   String timeLastUpdateUtc,
                                   long horaProximaActualizacionUnix,
                                   String timeNextUpdateUtc,
                                   String codigoBase,
                                   Map<String, Double>tasasDeConversion) {
    public Map<String, Double> getTasasDeConversion() {

        return Map.of();
    }
}