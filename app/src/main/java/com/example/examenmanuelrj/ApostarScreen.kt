package com.example.examenmanuelrj

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.examenmanuelrj.ui.ApostarViewModel
import com.example.examenmanuelrj.ui.InicioScreen
import com.example.examenmanuelrj.ui.VaciaScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController



enum class ApostarScreen(@StringRes val title: Int) {
    Start(title = R.string.manuel_ruiz_jimenez),
    Vacia(title = R.string.soy_una_pantalla_vacia),
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApostarAppBar(
    currentScreen: ApostarScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title),modifier=Modifier.padding(start = 20.dp)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.volver)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApostarApp(
    viewModel: ApostarViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ApostarScreen.valueOf(
        backStackEntry?.destination?.route ?: ApostarScreen.Start.name
    )

    Scaffold(
        topBar = {
            ApostarAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ApostarScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ApostarScreen.Start.name) {
                InicioScreen(
                    onNextButtonClicked = {
                        navController.navigate(ApostarScreen.Vacia.name)
                    },
                    viewModel = viewModel,
                    uiState = uiState
                )
            }
            composable(route = ApostarScreen.Vacia.name) {
                VaciaScreen(
                    modifier = Modifier.fillMaxHeight()
                )
            }

        }
    }
}
