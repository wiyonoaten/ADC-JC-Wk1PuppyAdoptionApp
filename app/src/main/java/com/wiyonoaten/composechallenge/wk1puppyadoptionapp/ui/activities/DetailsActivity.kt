package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.viewmodels.DetailsViewModel
import org.threeten.bp.OffsetDateTime

const val EXTRA_KEY_PUPPY_ID = "id"

class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val puppyId = intent.getStringExtra(EXTRA_KEY_PUPPY_ID) ?: "1"

        detailsViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    DetailsViewModel(lifecycleScope, puppyId) as T
            }
        ).get(DetailsViewModel::class.java)

        setContent {
            Wk1PuppyAdoptionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ActivityScreen(detailsViewModel)
                }
            }
        }
    }
}

@Composable
private fun ActivityScreen(detailsViewModel: DetailsViewModel) { with(detailsViewModel) {
    PuppyDetailsViewer(
        name = name,
        breed = breed,
        birthdate = birthdate,
        photoUrl = photoUrl
    )
}}

@Composable
private fun PuppyDetailsViewer(
    name: String,
    breed: String,
    birthdate: OffsetDateTime,
    photoUrl: String? = null,
) {
    AppScaffold {
        Column {
            Text("Puppy details")
            Text("Name: $name")
            Text("Breed: $breed")
            Text("Age: ${currentAge(birthdate)} years old")
            NetworkImage(
                url = photoUrl,
                placeholderResId = R.drawable.ic_placeholder,
                errorResId = R.drawable.ic_placeholder,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    CompositionLocalProvider(LocalPreviewMode provides true) {
        val samplePuppy = Puppy("1", "Shinu", "Shiba Inu", OffsetDateTime.parse("2009-01-09T00:00+00:00"))

        Wk1PuppyAdoptionAppTheme {
            with(samplePuppy) {
                PuppyDetailsViewer(
                    name = name,
                    breed = breed,
                    birthdate = birthdate,
                    photoUrl = photoUrl
                )
            }
        }
    }
}
