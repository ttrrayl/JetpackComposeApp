package com.example.jetpackcomposeapp.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import com.example.jetpackcomposeapp.ui.theme.Shapes

@Composable
fun OriginoteItems(
    image: Int,
    price: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.width(180.dp)
    ) {
        Column(modifier = modifier.padding(8.dp),) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(Shapes.medium))
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            PriceTag(
                priceTag = price,
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

        }

    }
}

@Composable
fun PriceTag(
    priceTag: String,
    modifier: Modifier = Modifier,
) {
    Box {
        Card(modifier = modifier
            .width(64.dp)
            .height(22.dp)) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(4.dp),
                text = priceTag,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
@Preview(showBackground =  false)
fun ItemPreview(){
    JetpackComposeAppTheme {
        OriginoteItems(R.drawable.acne,"Rp 30.000", "Acne Serum")
    }
}

@Composable
@Preview(showBackground =  true)
fun PricePreview(){
    JetpackComposeAppTheme {
        PriceTag("Rp 30.000")
    }
}