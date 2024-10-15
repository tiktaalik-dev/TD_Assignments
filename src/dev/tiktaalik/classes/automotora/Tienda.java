package dev.tiktaalik.classes.automotora;

import java.util.ArrayList;

public class Tienda {

    // Crear variables de clase
    private Vendedor vendedor;
    private Cliente cliente;
    private String nombreTienda;
    private int stock;
    private ArrayList<Vehiculo> vehiculosDisponibles;

    public Tienda(Vendedor vendedor, Cliente cliente, String nombreTienda, int stock,
                  ArrayList<Vehiculo> vehiculosDisponibles) {

        // Asignar los valores iniciales
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.nombreTienda = nombreTienda;
        this.stock = stock;
        this.vehiculosDisponibles = vehiculosDisponibles;
    }

    public Vendedor getVendedor() {

        // Retornar el vendedor asignado a esta tienda
        return vendedor;
    }

    public boolean setVendedor(Vendedor vendedor) {

        // Intentar asignar un nuevo vendedor a esta tienda
        try {
            this.vendedor = vendedor;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public Cliente getCliente() {

        // Retornar el cliente que compra en esta tienda
        return cliente;
    }

    public boolean setCliente(Cliente cliente) {

        // Intentar asignar un nuevo cliente a esta tienda
        try {
            this.cliente = cliente;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public String getNombreTienda() {

        // Retornar el nombre de esta tienda
        return nombreTienda;
    }

    public boolean setNombreTienda(String nombreTienda) {

        // Intentar asignar un nuevo nombre a esta tienda
        try {
            this.nombreTienda = nombreTienda;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public int getStock() {

        // Retornar el stock actual de productos
        return stock;
    }

    public boolean setStock(int stock) {

        // Intentar asignar un nuevo stock de productos
        try {
            this.stock = stock;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public ArrayList<Vehiculo> getVehiculosDisponibles() {

        // Retornar la lista de vehículos disponibles
        return vehiculosDisponibles;
    }

    public boolean setVehiculosDisponibles(ArrayList<Vehiculo> vehiculosDisponibles) {

        // Intentar asignar una nueva lista de vehículos disponibles
        try {
            this.vehiculosDisponibles = vehiculosDisponibles;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public String existeStock() {
        return System.out.printf("Cantidad de stock es: %d", getStock()).toString();
    }
}
