package org.example.dogcollector.presentation.tab.home



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object HomeTab : Tab {
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Home tab")
        }
    }

    override val options: TabOptions
        @Composable
        get() =
            TabOptions(
                index = 0u,
                title = "Home",
                icon =  null,
            )
}