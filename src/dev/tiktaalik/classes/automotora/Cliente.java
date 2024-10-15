package dev.tiktaalik.classes.automotora;

public class Cliente extends Persona {

    // Crear variables de clase
    private int edad;

    public Cliente(String rut, String nombre, int edad) {

        // Asignar los valores iniciales
        super(rut, nombre);
        this.edad = edad;
    }

    public int getEdad() {

        // Retornar la edad registrada del cliente
        return edad;
    }

    public boolean setEdad(int edad) {

        // Intentar asignar una nueva edad
        try {
            this.edad = edad;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }
}
