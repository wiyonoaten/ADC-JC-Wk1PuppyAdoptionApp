package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.R
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.models.Puppy
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.LocalPreviewMode
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.components.AppScaffold
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.components.NetworkImage
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.theme.Wk1PuppyAdoptionAppTheme
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.utils.currentAge
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.viewmodels.ListViewModel
import org.threeten.bp.OffsetDateTime

class MainActivity : AppCompatActivity() {
    private lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    ListViewModel(lifecycleScope) as T
            }
        ).get(ListViewModel::class.java)

        setContent {
            Wk1PuppyAdoptionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ActivityScreen(listViewModel)
                }
            }
        }
    }
}

@Composable
private fun ActivityScreen(listViewModel: ListViewModel) { with(listViewModel) {
    PuppyChooser(
        isLoading = isLoading,
        puppyList = puppies,
        onRefresh = ::onRefresh,
        onPuppySelected = ::onPuppySelected
    )

    listViewModel.selectedPuppy?.let {
        with(LocalContext.current) {
            startActivity(Intent(this, DetailsActivity::class.java).apply {
                putExtra(EXTRA_KEY_PUPPY_ID, it.id)
            })
        }
        listViewModel.onPuppyUnselected(it)
    }
}}

@Composable
private fun PuppyChooser(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    puppyList: List<Puppy>,
    onRefresh: () -> Unit = {},
    onPuppySelected: (Puppy) -> Unit = {}
) {
    AppScaffold(
        modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onRefresh) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh"
                )
            }
        }
    ) {
        Column {
            Text(
                text = "Which puppy would you like to adopt?",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
            Spacer(Modifier.padding(5.dp))
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                PuppyList(
                    puppyList = puppyList,
                    onPuppySelected = onPuppySelected
                )
            }
        }
    }
}

@Composable
private fun PuppyList(
    modifier: Modifier = Modifier,
    puppyList: List<Puppy>,
    onPuppySelected: (Puppy) -> Unit
) {
    LazyColumn(modifier) {
        items(puppyList) {
            Card(
                border = BorderStroke(1.dp, Color.Gray),
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        onPuppySelected(it)
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NetworkImage(
                        url = it.photoUrl,
                        placeholderResId = R.drawable.ic_placeholder,
                        errorResId = R.drawable.ic_placeholder,
                        modifier = Modifier
                            .padding(15.dp)
                            .width(133.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                    )
                    Column {
                        Text(
                            text = it.name
                        )
                        Spacer(Modifier.padding(3.dp))
                        Text(
                            text = "${currentAge(it.birthdate)} years old"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    CompositionLocalProvider(LocalPreviewMode provides true) {
        val sampleList = listOf(
            Puppy("1", "Shinu", "Shiba Inu", OffsetDateTime.parse("2009-01-09T00:00+00:00")),
            Puppy("2", "Sheppy", "German Shepherd", OffsetDateTime.parse("2016-01-01T00:00+00:00"))
        )

        Wk1PuppyAdoptionAppTheme {
            PuppyChooser(isLoading = false, puppyList = sampleList)
        }
    }
}
