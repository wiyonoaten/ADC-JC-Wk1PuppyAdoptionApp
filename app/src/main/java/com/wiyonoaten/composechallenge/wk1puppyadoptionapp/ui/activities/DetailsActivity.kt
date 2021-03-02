package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
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
        isLoading = isLoading,
        details = PuppyDetails(
            name = name,
            breed = breed,
            birthdate = birthdate,
            photoUrl = photoUrl,
            description = description
        )
    )
}}

@Composable
private fun PuppyDetailsViewer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    details: PuppyDetails,
) {
    AppScaffold(
        modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { /** */ }) {
                Text(
                    text = "Adopt me ❤",
                    modifier = Modifier.padding(15.dp)
                )
            }
        }
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                item {
                    PuppyInfoCard(details = details)
                }
                if (details.description != null) {
                    item {
                        PuppyDescription(
                            descriptionText = details.description,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
                item {
                    Spacer(Modifier.padding(vertical = 35.dp))
                }
            }
        }
    }
}

@Composable
private fun PuppyInfoCard(
    modifier: Modifier = Modifier,
    details: PuppyDetails,
) { with (details) {
    Card(
        border = BorderStroke(1.dp, Color.Gray),
        elevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            NetworkImage(
                url = photoUrl,
                placeholderResId = R.drawable.ic_placeholder,
                errorResId = R.drawable.ic_placeholder,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
            )
            Spacer(Modifier.padding(5.dp))
            Text(makeStyledLabelledText("Name", name))
            Spacer(Modifier.padding(2.dp))
            Text(makeStyledLabelledText("Breed", breed))
            Spacer(Modifier.padding(2.dp))
            Text(makeStyledLabelledText("Age", "${currentAge(birthdate)} years old"))
        }
    }
}}

private fun makeStyledLabelledText(label: String, value: String) = with(AnnotatedString.Builder()) {
    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
    append(label)
    append(": ")
    pop()
    append(value)
    toAnnotatedString()
}

@Composable
private fun PuppyDescription(
    modifier: Modifier = Modifier,
    descriptionText: String
) {
    Text(
        text = descriptionText,
        modifier = modifier
            .padding(horizontal = 10.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    CompositionLocalProvider(LocalPreviewMode provides true) {
        val samplePuppy = Puppy("1", "Shinu", "Shiba Inu", OffsetDateTime.parse("2009-01-09T00:00+00:00"),
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Tempor nec feugiat nisl pretium fusce id velit ut tortor. In dictum non consectetur a erat. Id diam maecenas ultricies mi eget mauris pharetra et ultrices. Duis ut diam quam nulla porttitor massa. Condimentum id venenatis a condimentum vitae sapien pellentesque. Mollis nunc sed id semper risus. Aliquet nibh praesent tristique magna sit amet purus. Scelerisque mauris pellentesque pulvinar pellentesque habitant morbi tristique senectus et. Facilisis leo vel fringilla est ullamcorper eget nulla facilisi etiam. Duis at tellus at urna condimentum mattis pellentesque id. Sapien eget mi proin sed libero enim sed faucibus. Eget nunc lobortis mattis aliquam faucibus purus in. Egestas maecenas pharetra convallis posuere morbi leo.

Phasellus faucibus scelerisque eleifend donec pretium vulputate sapien nec sagittis. Ac odio tempor orci dapibus ultrices in. Ut ornare lectus sit amet. Amet aliquam id diam maecenas ultricies mi eget mauris pharetra. Dolor purus non enim praesent elementum facilisis leo vel. Eu facilisis sed odio morbi quis commodo odio. Facilisi nullam vehicula ipsum a arcu cursus. Semper risus in hendrerit gravida rutrum quisque. Arcu risus quis varius quam quisque id diam. Cras pulvinar mattis nunc sed blandit. Fusce ut placerat orci nulla pellentesque dignissim enim sit. Rhoncus mattis rhoncus urna neque viverra justo nec ultrices dui. In ornare quam viverra orci sagittis eu volutpat. Velit egestas dui id ornare. Eu sem integer vitae justo eget magna fermentum iaculis."""
        )

        Wk1PuppyAdoptionAppTheme {
            with(samplePuppy) {
                PuppyDetailsViewer(
                    isLoading = false,
                    details = PuppyDetails(
                        name = name,
                        breed = breed,
                        birthdate = birthdate,
                        photoUrl = photoUrl,
                        description = description
                    )
                )
            }
        }
    }
}

private data class PuppyDetails(
    val name: String,
    val breed: String,
    val birthdate: OffsetDateTime,
    val photoUrl: String? = null,
    val description: String? = null
)
