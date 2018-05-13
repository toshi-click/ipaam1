package click.toshi.ipaam1.model

data class Speaker(
        val id: String,
        val name: String,
        val tagLine: String,
        val imageUrl: String,
        val twitterUrl: String?,
        val githubUrl: String?,
        val blogUrl: String?,
        val companyUrl: String?
)
