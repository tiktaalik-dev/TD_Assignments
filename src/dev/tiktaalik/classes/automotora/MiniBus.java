package dev.tiktaalik.classes.automotora;

public class MiniBus extends Bus {

    // Crear variables de clase
    private String tipoViaje;

    public MiniBus(String color, String patente, int cantidadAsientos, String tipoViaje) {

        // Asignar los valores iniciales
        super(color, patente, cantidadAsientos);
        this.tipoViaje = tipoViaje;
    }

    public String getTipoViaje() {

        // Retornar el tipo de viaje que hace este mini bus
        return tipoViaje;
    }

    public boolean setTipoViaje(String tipoViaje) {

        // Intentar asignar el tipo de viaje que hace este mini bus
        try {
            this.tipoViaje = tipoViaje;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public void imprimeBus() {

        // Crear las cadenas de texto
        String header = "\n\nEste bus tiene estas características:\n";
        String color_str = "Color: %s\n".formatted(getColor());
        String patente_str = "Patente: %s\n".formatted(getPatente());
        String total_asientos_str = "Total de Asientos: %s\n".formatted(getCantidadAsientos());
        String asientos_libres_str = "Asientos disponibles: %s\n".formatted(asientosDisponibles());
        String tipo_viaje_str = "Tipo de viaje: %s\n".formatted(getTipoViaje());
        String minibus_traits = "%s%s%s%s%s%s\n\n".formatted(header, color_str, patente_str, total_asientos_str,
                asientos_libres_str, tipo_viaje_str);

        // Imprimir información
        System.out.println(minibus_traits);
    }
}
