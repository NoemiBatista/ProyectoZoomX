package com.example.proyectozoomx.entities

import java.time.LocalDateTime

data class Sala (val nombre: String, val responsable: String, val fecha_de_Reserva: LocalDateTime,
                 val tiempo_de_reserva_Hs: Int, val url: String)



