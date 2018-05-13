package click.toshi.ipaam1.data.api.response

data class Category(
        val id: Int?,
        val sort: Int?,
        val title: String?,
        val items: List<CategoryItem?>?
)
