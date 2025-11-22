package com.annuairepharmacie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.annuairepharmacie.ui.screens.PharmacieDetailScreen
import com.annuairepharmacie.ui.screens.PharmacieListScreen
import com.annuairepharmacie.ui.theme.AnnuairePharmacieTheme
import com.annuairepharmacie.ui.viewmodel.PharmacieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val viewModel = PharmacieViewModel()
        
        setContent {
            AnnuairePharmacieTheme {
                AnnuaireApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun AnnuaireApp(viewModel: PharmacieViewModel) {
    var selectedPharmacieId by remember { mutableStateOf<Long?>(null) }
    
    selectedPharmacieId?.let { id ->
        val pharmacie = viewModel.getPharmacieById(id)
        pharmacie?.let {
            PharmacieDetailScreen(
                pharmacie = it,
                onBackClick = { selectedPharmacieId = null }
            )
        }
    } ?: run {
        PharmacieListScreen(
            viewModel = viewModel,
            onPharmacieClick = { id -> selectedPharmacieId = id }
        )
    }
}