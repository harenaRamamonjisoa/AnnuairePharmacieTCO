package com.annuairepharmacie.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.annuairepharmacie.data.Pharmacie
import com.annuairepharmacie.data.PharmacieRepository

class PharmacieViewModel : ViewModel() {
    private val repository = PharmacieRepository()

    var pharmacies by mutableStateOf(repository.getAllPharmacies())
        private set

    var filtreActif by mutableStateOf(FiltreType.AUCUN)
        private set

    var rechercheAdresse by mutableStateOf("")
        private set

    var rechercheMedicament by mutableStateOf("")
        private set

    fun afficherToutesLesPharmacies() {
        pharmacies = repository.getAllPharmacies()
        filtreActif = FiltreType.AUCUN
        rechercheAdresse = ""
        rechercheMedicament = ""
    }

    fun afficherPharmaciesDeGarde() {
        pharmacies = repository.getPharmaciesDeGarde()
        filtreActif = FiltreType.GARDE
        rechercheAdresse = ""
        rechercheMedicament = ""
    }

    fun rechercherParAdresse(adresse: String) {
        rechercheAdresse = adresse
        if (adresse.isBlank()) {
            afficherToutesLesPharmacies()
        } else {
            pharmacies = repository.searchByAdresse(adresse)
            filtreActif = FiltreType.ADRESSE
            rechercheMedicament = ""
        }
    }

    fun rechercherParMedicament(medicament: String) {
        rechercheMedicament = medicament
        if (medicament.isBlank()) {
            afficherToutesLesPharmacies()
        } else {
            pharmacies = repository.searchByMedicament(medicament)
            filtreActif = FiltreType.MEDICAMENT
            rechercheAdresse = ""
        }
    }

    fun getPharmacieById(id: Long): Pharmacie? = repository.getPharmacieById(id)
}

enum class FiltreType {
    AUCUN, GARDE, ADRESSE, MEDICAMENT
}

