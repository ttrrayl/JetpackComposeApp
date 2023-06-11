package com.example.jetpackcomposeapp.ui.item

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileItems(
    title: String,
    field: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column (
            modifier.padding(start=16.dp, top=8.dp, end=8.dp, bottom=8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = modifier.padding(bottom=2.dp),
                text = field,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun ProfilePreview(){
    JetpackComposeAppTheme {
        ProfileItems(title = "Email", field = "email@gmail.com")
    }
}
