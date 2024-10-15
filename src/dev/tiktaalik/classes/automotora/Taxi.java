package dev.tiktaalik.classes.automotora;

public class Taxi extends Vehiculo {

    // Crear variables de clase
    private int valorPasaje;

    public Taxi(String color, String patente, int valorPasaje) {

        // Asignar los valores iniciales
        super(color, patente);
        this.valorPasaje = valorPasaje;
    }

    public int getValorPasaje() {

        // Retornar el valor actual del pasaje
        return valorPasaje;
    }

    public boolean setValorPasaje(int valorPasaje) {

        // Intentar asignar el nuevo valor del pasaje
        try {
            this.valorPasaje = valorPasaje;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public boolean pagarPasaje(int tarifa) {

        // Comprobar que el valor pagado es mayor que el costo del valor del pasaje
        int diferencia_tarifa = tarifa - getValorPasaje();
        if (diferencia_tarifa > 0) {

            // Si lo es, devolver el vuelto
            System.out.printf("\nAquí tiene su vuelto de $%d\n", diferencia_tarifa);
        } else {
            // Si no lo es, devolver todo el dinero recibido
            System.out.printf("\nEsto no alcanza para pagar el pasaje. Aquí tiene de vuelta sus $%d\n", tarifa);

            // Ya que falló, retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }
}
