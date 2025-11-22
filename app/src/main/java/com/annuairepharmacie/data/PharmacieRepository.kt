package com.annuairepharmacie.data

class PharmacieRepository {
    // Données de démonstration - Pharmacies à Madagascar
    private val pharmacies = mutableListOf(
        Pharmacie(
            id = 1,
            nom = "Pharmacie Centrale Antaninarenina",
            adresse = "Antaninarenina, 101 Antananarivo, Madagascar",
            telephone = "+261 20 22 000 01",
            estDeGarde = true,
            medicaments = listOf("Paracétamol", "Ibuprofène", "Aspirine", "Vitamine D")
        ),
        Pharmacie(
            id = 2,
            nom = "Pharmacie d’Isotry",
            adresse = "Isotry, 101 Antananarivo, Madagascar",
            telephone = "+261 34 12 345 67",
            estDeGarde = false,
            medicaments = listOf("Paracétamol", "Doliprane", "Smecta")
        ),
        Pharmacie(
            id = 3,
            nom = "Pharmacie Analakely Santé",
            adresse = "Rue du Commerce, Analakely, 101 Antananarivo, Madagascar",
            telephone = "+261 32 45 678 90",
            estDeGarde = true,
            medicaments = listOf("Ibuprofène", "Aspirine", "Vitamine C", "Magnésium")
        ),
        Pharmacie(
            id = 4,
            nom = "Pharmacie de la Gare Soarano",
            adresse = "Gare Soarano, 101 Antananarivo, Madagascar",
            telephone = "+261 20 22 111 22",
            estDeGarde = false,
            medicaments = listOf("Paracétamol", "Ibuprofène", "Spasfon")
        ),
        Pharmacie(
            id = 5,
            nom = "Pharmacie de Garde Mahamasina 24/7",
            adresse = "Mahamasina, 101 Antananarivo, Madagascar",
            telephone = "+261 33 11 222 33",
            estDeGarde = true,
            medicaments = listOf("Paracétamol", "Doliprane", "Ibuprofène", "Aspirine", "Vitamine D", "Magnésium")
        ),
        Pharmacie(
            id = 6,
            nom = "Pharmacie du Centre Tamatave",
            adresse = "Boulevard Ratsimilaho, Toamasina (Tamatave), Madagascar",
            telephone = "+261 20 53 000 01",
            estDeGarde = false,
            medicaments = listOf("Paracétamol", "Smecta", "Vitamine C")
        )
    )

    fun getAllPharmacies(): List<Pharmacie> = pharmacies.toList()

    fun getPharmaciesDeGarde(): List<Pharmacie> = pharmacies.filter { it.estDeGarde }

    fun searchByAdresse(adresse: String): List<Pharmacie> {
        val adresseLower = adresse.lowercase()
        return pharmacies.filter {
            it.adresse.lowercase().contains(adresseLower)
        }
    }

    fun searchByMedicament(medicament: String): List<Pharmacie> {
        val medicamentLower = medicament.lowercase()
        return pharmacies.filter { pharmacie ->
            pharmacie.medicaments.any { it.lowercase().contains(medicamentLower) }
        }
    }

    fun getPharmacieById(id: Long): Pharmacie? = pharmacies.find { it.id == id }
}

