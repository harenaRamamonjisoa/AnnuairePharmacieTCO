package com.annuairepharmacie.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.annuairepharmacie.data.Pharmacie
import com.annuairepharmacie.ui.components.PharmacieCard
import com.annuairepharmacie.ui.viewmodel.PharmacieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PharmacieListScreen(
    viewModel: PharmacieViewModel,
    onPharmacieClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    // Observer les changements du ViewModel
    val pharmacies = viewModel.pharmacies
    val filtreActif = viewModel.filtreActif
    
    var rechercheAdresse by remember { mutableStateOf(viewModel.rechercheAdresse) }
    var rechercheMedicament by remember { mutableStateOf(viewModel.rechercheMedicament) }
    var showAdresseDialog by remember { mutableStateOf(false) }
    var showMedicamentDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        // Barre de titre
        TopAppBar(
            title = { Text("Annuaire des Pharmacies") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        // Boutons de filtrage
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = filtreActif == com.annuairepharmacie.ui.viewmodel.FiltreType.GARDE,
                onClick = {
                    viewModel.afficherPharmaciesDeGarde()
                },
                label = { Text("De Garde") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocalPharmacy,
                        contentDescription = null
                    )
                },
                modifier = Modifier.weight(1f)
            )
            
            FilterChip(
                selected = showAdresseDialog,
                onClick = { showAdresseDialog = true },
                label = { Text("Par Adresse") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null
                    )
                },
                modifier = Modifier.weight(1f)
            )
            
            FilterChip(
                selected = showMedicamentDialog,
                onClick = { showMedicamentDialog = true },
                label = { Text("Par Médicament") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Medication,
                        contentDescription = null
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }

        // Bouton pour réinitialiser les filtres
        if (filtreActif != com.annuairepharmacie.ui.viewmodel.FiltreType.AUCUN) {
            TextButton(
                onClick = {
                    viewModel.afficherToutesLesPharmacies()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Réinitialiser"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Afficher toutes les pharmacies")
            }
        }

        // Liste des pharmacies
        if (pharmacies.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Column(
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.SearchOff,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Aucune pharmacie trouvée",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(pharmacies) { pharmacie ->
                    PharmacieCard(
                        pharmacie = pharmacie,
                        onClick = { onPharmacieClick(pharmacie.id) }
                    )
                }
            }
        }
    }

    // Dialog pour recherche par adresse
    if (showAdresseDialog) {
        AlertDialog(
            onDismissRequest = { showAdresseDialog = false },
            title = { Text("Rechercher par adresse") },
            text = {
                OutlinedTextField(
                    value = rechercheAdresse,
                    onValueChange = { rechercheAdresse = it },
                    label = { Text("Entrez une adresse") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.rechercherParAdresse(rechercheAdresse)
                        showAdresseDialog = false
                    }
                ) {
                    Text("Rechercher")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAdresseDialog = false }) {
                    Text("Annuler")
                }
            }
        )
    }

    // Dialog pour recherche par médicament
    if (showMedicamentDialog) {
        AlertDialog(
            onDismissRequest = { showMedicamentDialog = false },
            title = { Text("Rechercher par médicament") },
            text = {
                OutlinedTextField(
                    value = rechercheMedicament,
                    onValueChange = { rechercheMedicament = it },
                    label = { Text("Entrez un médicament") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.rechercherParMedicament(rechercheMedicament)
                        showMedicamentDialog = false
                    }
                ) {
                    Text("Rechercher")
                }
            },
            dismissButton = {
                TextButton(onClick = { showMedicamentDialog = false }) {
                    Text("Annuler")
                }
            }
        )
    }
}

