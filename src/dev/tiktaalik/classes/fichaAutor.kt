package dev.tiktaalik.classes

/** Completa o modifica la clase Author, permitiendo almacenar
 *  Nombres
 *  Apellidos
 *  Obras destacadas
 *  Año de nacimiento
 *  Año de muerte (en caso de estar vivo, debe soportar valor nulo)
 *  Comentario
 */
data class Author(
    val name: String,
    val surname: String,
    val birth_year: Int,
    var death_year: Int?,
    var works: ArrayList<String>,
    var comment: String

)

/**
 * Calcula la edad basándose solamente en los años
 * Ejemplo: si nació en 1950 y estamos en 2022, entonces la edad es 72 años
 */
fun calculateAge(author: Author): Int? {
    var age = author.death_year?.minus(author.birth_year)
    return age
}

private fun build_card(author_data: Author): String {

    // Calcular el largo de todos los String para poder construir el marco
    var str_lengths: List<Int> = listOf<Int>(
        author_data.comment.length,
        author_data.name.length + author_data.surname.length + 1,
        author_data.works[0].length,
        author_data.works[1].length,
        author_data.works[2].length,
        author_data.birth_year.toString().length,
        author_data.death_year.toString().length
    )
    var max_length: Int = str_lengths.max() + 3

    // Construir las cadenas de texto
    var hz_line: String = "**%s**".format("*".repeat(max_length))
    val line_length: Int = hz_line.length
    var name_str = "* Nombre del autor: %s %s".format(author_data.name, author_data.surname)
    var works_header = "* Obras destacadas:"
    var works_content_1 = "*    %s".format(author_data.works[0])
    var works_content_2 = "*    %s".format(author_data.works[1])
    var works_content_3 = "*    %s".format(author_data.works[2])
    var birth_str = "* Año de nacimiento: %s".format(author_data.birth_year)
    var death_str = ""
    var datum_header = "* Dato interesante:"
    var datum_content = "*    %s".format(author_data.comment)
    name_str = "%s%s*".format(name_str, " ".repeat(line_length - name_str.length - 1))
    works_header = "%s%s*".format(works_header, " ".repeat(line_length - works_header.length - 1))
    works_content_1 = "%s%s*".format(works_content_1, " ".repeat(line_length - works_content_1.length - 1))
    works_content_2 = "%s%s*".format(works_content_2, " ".repeat(line_length - works_content_2.length - 1))
    works_content_3 = "%s%s*".format(works_content_3, " ".repeat(line_length - works_content_3.length - 1))
    birth_str = "%s%s*".format(birth_str, " ".repeat(line_length - birth_str.length - 1))
    datum_header = "%s%s*".format(datum_header, " ".repeat(line_length - datum_header.length - 1))
    datum_content = "%s%s*".format(datum_content, " ".repeat(line_length - datum_content.length - 1))
    var works_str = "%s\n%s\n%s\n%s".format(works_header, works_content_1, works_content_2, works_content_3)
    var datum_str = "%s\n%s".format(datum_header, datum_content)

    // Comprobar si la fecha de muerte es nula. Si lo es, saltar esa línea
    // Para eso, crear una cadena de texto alternativa
    author_data.death_year ?.let {
        // Obtener la edad del autor
        var age: Int? = calculateAge(author_data)

        // Construir la línea
        death_str = "\n* Año de muerte: %d, murió a la edad de %d años".format(author_data.death_year, age)
        death_str = "%s%s*".format(death_str, " ".repeat(line_length - death_str.length))
    }
    var variable_death_str: String = "%s".format(death_str)

    // Crear la tarjeta
    val author_card: String = "\n\n%s\n%s\n%s\n%s%s\n%s\n%s\n\n".format(
        hz_line,
        name_str,
        works_str,
        birth_str,
        variable_death_str,
        datum_str,
        hz_line
    )
    return author_card
}


fun main() {

    // Instanciar la clase Author y crear datos para dos tarjetas
    var author_data_1: Author = Author("J.R.R.", "Tolkien", 1892, 1973,
        arrayListOf("The Hobbit", "The Lord of The Rings", "The Silmarillion"),
        "Es considerado como un autor individual con un poder creativo equivalente al de toda una nación.")
    var author_data_2: Author = Author("손예진", "(Son Ye-jin)", 1982, null,
        arrayListOf("Thirty-Nine", "Crash Landing on You", "Something in the Rain"),
        "Una de las más importantes actrices Surcoreanas. Ha sido cariñosamente apodada \"El Primer Amor de la Nación\".")

    // Construir las tarjetas
    var author_card_1: String = build_card(author_data_1)
    var author_card_2: String = build_card(author_data_2)


    // Imprimir tarjeta
    println(author_card_1)
    println(author_card_2)
}
