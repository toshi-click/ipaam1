package click.toshi.ipaam1.data.api.response

data class Response(
        val sessions: List<Session>?,
        val rooms: List<Room>?,
        val speakers: List<Speaker>?,
        val categories: List<Category>?
)
