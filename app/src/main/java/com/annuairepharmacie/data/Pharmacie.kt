package com.annuairepharmacie.data

data class Pharmacie(
    val id: Long,
    val nom: String,
    val adresse: String,
    val telephone: String,
    val estDeGarde: Boolean,
    val medicaments: List<String>
)

