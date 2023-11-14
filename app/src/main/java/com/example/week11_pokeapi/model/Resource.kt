package com.example.week11_pokeapi.model

interface Resource{
    val id: Int
    val category: String
}

// Buat resource yang gapunya field name
data class ApiResource(
    val url: String,
) : Resource {
    constructor(category: String, id: Int) : this(resourceUrl(id, category))
    override val id by lazy { urlToId(url) }
    override val category by lazy { urlToCategory(url) }
}

// Buat resource yang punya field name
data class NamedApiResource(
    val name: String,
    val url: String,
) : Resource {
    constructor(category: String, id: Int, name: String) : this(name, resourceUrl(id, category))
    override val id by lazy { urlToId(url) }
    override val category by lazy { urlToCategory(url) }
}

data class ApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApiResource>,
)

data class NamedApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>,
)

// Extract id dari url
private fun urlToId(url: String): Int {
    return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
}

// Extract category dari url
private fun urlToCategory(url: String): String {
    return "/[a-z\\-]+/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isLetter() || it == '-' }
}

// Convert id + category ke url
private fun resourceUrl(id: Int, category: String): String {
    return "/api/v2/$category/$id/"
}
