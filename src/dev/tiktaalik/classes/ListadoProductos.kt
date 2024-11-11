package dev.tiktaalik.classes

// Crear una clase de datos para los productos
data class Product(
    val id: Int,
    var name: String,
    val description: String?, // Puede ser nula
    var isAvailable: Boolean = true,
    var isEnable: Boolean = true,
    var stock: Int = 0,
)

// Crear una función que extienda Boolean y retorne Sí o No
fun Boolean.showSiNo(): String {
    return if (this) {
        "Sí"
    } else {
        "No"
    }
}

// Crear una función que extienda Int y retorne "Sin Stock" cuando este es cero
fun Int.showSinStock(): Any {
    return if (this == 0) {
        "Sin Stock"
    } else {
        this
    }
}


/**
 * ADVERTENCIA: No modificar la funcion populateData() o los datos
 */
fun populateData(): MutableMap<Int, Product?> =
    mutableMapOf(
        1 to Product(
            id = 100,
            name = "Lápiz",
            description = null,
            isAvailable = true,
            isEnable = true,
            stock = 20
        ),
        2 to Product(
            id = 101,
            name = "Hoja de oficio",
            description = "Hojas para impresora",
            isAvailable = false,
            isEnable = true,
            stock = 150
        ),
        3 to Product(
            id = 102,
            name = "Hoja de carta",
            description = "Hojas para impresora",
            isAvailable = true,
            isEnable = true,
            stock = 100
        ),
        4 to Product(
            id = 103,
            name = "Corchetera",
            description = null,
            isAvailable = true,
            isEnable = false,
            stock = 150
        ),
        5 to Product(
            id = 104,
            name = "Tijeras",
            description = null,
            isAvailable = true,
            isEnable = true,
            stock = 100
        ),
        6 to Product(
            id = 105,
            name = "Sillas de oficina",
            description = null,
            isAvailable = false,
            isEnable = true,
            stock = 0
        ),
    )

fun main() {

    // Crear un listado de productos
    val products = populateData()

    // Ordenar la lista en forma descendente, según la cantidad de stock
    var sorted_products = products.entries.sortedByDescending { it.value?.stock }

    // Imprimir el detalle de cada producto como una tarjeta
    sorted_products.forEach {
        println("+++++++++++++++++++++++++++++++++++++++++++++")
        val item = it.value
        println(
            "ID: ${item?.id} " +
                    "\nNOMBRE: ${item?.name?.uppercase()} " + // Transformar en mayúsculas
                    "\nDESCRIPCIÓN: ${item?.description ?: "N/A"} " + // Reemplazar la salida por "N/A" si el valor es nulo
                    "\nDISPONIBLE: ${item?.isAvailable?.showSiNo()} " + // Reemplazar por "Sí" o "No"
                    "\nHABILITADO  ${item?.isEnable?.showSiNo()} " + // Reemplazar por "Sí" o "No"
                    "\nSTOCK: ${item?.stock?.showSinStock()}" // Reemplazar por "Sin Stock" si este es cero
        )
        println("+++++++++++++++++++++++++++++++++++++++++++++")
    }
}
