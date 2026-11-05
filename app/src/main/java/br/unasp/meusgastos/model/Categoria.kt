package br.unasp.meusgastos.model

// Categorias que o usuario pode escolher para cada gasto.
enum class Categoria(val rotulo: String) {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    MORADIA("Moradia"),
    LAZER("Lazer"),
    OUTROS("Outros")
}
