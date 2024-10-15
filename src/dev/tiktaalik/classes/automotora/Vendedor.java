package dev.tiktaalik.classes.automotora;

public class Vendedor extends Persona {

    // Crear variables de clase
    private String direccion;

    public Vendedor(String rut, String nombre, String direccion) {

        // Asignar los valores iniciales
        super(rut, nombre);
        this.direccion = direccion;
    }

    public String getDireccion() {

        // Retornar la dirección actual donde trabaja el vendedor
        return direccion;
    }

    public boolean setDireccion(String direccion) {

        // Intentar asignar una nueva dirección laboral para el vendedor
        try {
            this.direccion = direccion;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }
}
