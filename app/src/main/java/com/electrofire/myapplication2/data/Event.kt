package com.electrofire.myapplication2.data

data class Event(
    val id: Long,
    val date: String,
    val hour: String,
    val place: String,
    val price: Double,
    val sport: Sport


) {
    override fun toString(): String {
        return "\n\nEvento(Dia:'$date', Hora:'$hour', Lugar:'$place', Precio:$price, Deporte:$sport)"
    }
}
