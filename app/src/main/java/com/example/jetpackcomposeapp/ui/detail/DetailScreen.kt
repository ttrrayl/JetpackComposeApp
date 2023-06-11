package com.example.jetpackcomposeapp.ui.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.di.Injection
import com.example.jetpackcomposeapp.ui.State
import com.example.jetpackcomposeapp.ui.ViewModelFactory
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import com.example.jetpackcomposeapp.ui.theme.Shapes

@Composable
fun DetailScreen(
    originoteId: Long,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = State.Loading).value.let {
        state -> when (state) {
            is State.Loading -> {
                viewModel.getOriginoteId(originoteId)
            }
            is State.Success -> {
                val data = state.data
                DetailItem(
                    image = data.originote.photoUrl,
                    name = data.originote.name,
                    price = data.originote.price,
                    skintype = data.originote.skinType,
                    description = data.originote.desc,
                    onBackClick = navigateBack )
            }
        }
    }
}

@Composable
fun DetailItem(
    @DrawableRes image: Int,
    name: String,
    price: String,
    skintype: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Back",
        modifier = Modifier
            .padding(16.dp)
            .clickable { onBackClick() }
    )
    Column(modifier = modifier) {
        ImageDetail(image = image, modifier = modifier)
        Spacer(modifier = modifier.width(24.dp))
        DescriptionDetail(
            name = name,
            price = price,
            skintype = skintype,
            description = description)
    }
}

@Composable
fun ImageDetail(
    image: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(48.dp)
            .clip(CircleShape)
    ) {
        Card(modifier = modifier.fillMaxWidth()) {
            Image(
                modifier = modifier.padding(24.dp).clip(Shapes.medium),
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}


@Composable
fun DescriptionDetail(
    name: String,
    price: String,
    skintype: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight())
    {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false) )
        {
            Text(
                modifier = modifier.padding(start = 18.dp, top=18.dp, end=18.dp),
                text = name,
                style = MaterialTheme.typography.displaySmall)
            PriceTag(priceTag = price, modifier = modifier)
            Text(
                modifier = modifier.padding(start=18.dp, bottom=12.dp),
                text = skintype,
                style = MaterialTheme.typography.labelMedium)
            Text(
                modifier = modifier.padding(start = 18.dp, bottom = 12.dp),
                text = description,
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun PriceTag(
    priceTag: String,
    modifier: Modifier = Modifier,
) {
    Box{
        Card (
            modifier = modifier
                .padding(18.dp)
                .width(94.dp)
                .height(43.dp)
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(4.dp),
                text = priceTag,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailItemPreview(){
    JetpackComposeAppTheme {
        DetailItem(
            image = R.drawable.acne,
            name = "sunscreen",
            price = "Rp 20.000",
            skintype = "Oily",
            description = "This is the awesome sunscreen oh my god",
            onBackClick = {}    )
    }
}