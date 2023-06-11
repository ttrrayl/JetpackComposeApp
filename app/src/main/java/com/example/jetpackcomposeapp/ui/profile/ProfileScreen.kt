package com.example.jetpackcomposeapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.ui.item.ProfileItems
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Column( modifier = modifier.padding(16.dp)  ){
        Icon(
            contentDescription = "Back",
            imageVector = Icons.Default.ArrowBack,
            modifier = Modifier
                .padding(16.dp)
                .clickable { navigateBack() } )
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(360.dp)
                .padding(16.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.niacan),
            contentDescription = "profile",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = modifier.height(8.dp))
        ProfileItems(title = "Name", field = "Tri Ayunia Patma Lubis")
        ProfileItems(title = "Email", field = "triayuniapatma@gmail.com")
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JetpackComposeAppTheme {
        ProfileScreen(navigateBack = {})
    }
}