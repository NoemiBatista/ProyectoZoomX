package com.example.proyectozoomx.entities

import java.time.LocalDateTime

data class Sala (val nombre: String, val responsable: String, val fechaDeReserva: LocalDateTime,
                 val tiempoReservaEnHoras: Int, val url: String)



