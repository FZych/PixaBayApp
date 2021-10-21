package com.filipzych.pixabay.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import com.filipzych.pixabay.R
import com.filipzych.pixabay.gallery.data.entities.Hit
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@ExperimentalMaterialApi
@ExperimentalFoundationApi
class GalleryFragment : Fragment() {

    private val vm by sharedViewModel<GalleryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(context = requireContext()).apply {
            setContent {
                MaterialTheme {
                    GalleryLayout(viewModel = vm)
                    ConfirmationDialog(viewModel = vm)
                    DetailsNavigation(viewModel = vm)
                }
            }
        }
    }

    @Composable
    fun ConfirmationDialog(viewModel: GalleryViewModel) {
        viewModel.showConfirmationDialog.observeAsState().value?.let { showDialog ->
            if (showDialog) {
                ShowComposableDialog(onConfirmClick = {
                    viewModel.showConfirmationDialog.value = false
                    viewModel.navigateToDetails(true)
                },
                    onDismissClick = { viewModel.showConfirmationDialog.value = false })
            }
        }
    }

    @Composable
    fun DetailsNavigation(viewModel: GalleryViewModel) {
        viewModel.navigateToDetails.observeAsState().value?.let {
            if (it) {
                findNavController().navigate(R.id.nav_details)
                viewModel.navigateToDetails(false)
            }
        }
    }

    @Composable
    fun ShowComposableDialog(onConfirmClick: () -> Unit, onDismissClick: () -> Unit) {
        AlertDialog(
            onDismissRequest = { onDismissClick.invoke() },
            text = {
                Text("Would You like to see more details about this image?")
            },
            confirmButton = {
                Button(
                    onClick = { onConfirmClick.invoke() }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismissClick.invoke() }) {
                    Text("No")
                }
            }
        )
    }

    @Composable
    fun GalleryLayout(viewModel: GalleryViewModel) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                SearchHandler(viewModel = viewModel)
                GalleryHandler(viewModel = viewModel)
            }
        )
    }

    @Composable
    fun SearchHandler(viewModel: GalleryViewModel) {
        viewModel.searchText.observeAsState().value.let { query ->
            GallerySearch(textValue = query ?: "") { text ->
                vm.fetchImages(query = text)
            }
        }
    }

    @Composable
    fun GallerySearch(textValue: String, onTextChanged: (String) -> Unit) {
        Surface(elevation = 2.dp, color = MaterialTheme.colors.surface) {
            TextField(
                value = textValue,
                onValueChange = onTextChanged,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @Composable
    fun GalleryHandler(viewModel: GalleryViewModel) {
        viewModel.images.observeAsState().value?.let {
            GalleryGrid(it) { photo ->
                viewModel.selectedPhoto(photo)
            }
        }
    }

    @Composable
    fun GalleryGrid(galleryItems: List<Hit>, onClick: (Hit) -> Unit) {
        LazyVerticalGrid(
            modifier = Modifier.semantics { contentDescription = "grid" },
            cells = GridCells.Fixed(3)
        ) {
            items(galleryItems.size) { photo ->
                PhotoItem(galleryItems[photo], onClick)
            }
        }
    }

    @Composable
    fun PhotoItem(photo: Hit, onClick: (Hit) -> Unit) {
        Card(
            elevation = 4.dp,
            onClick = { onClick(photo) }
        ) {
            Column {
                GalleryImage(photo)
                Text(text = "User: " + photo.user, maxLines = 1)
                Text(text = "Tags: " + photo.tags, maxLines = 2)
            }
        }

    }

    @Composable
    fun GalleryImage(photo: Hit) {
        Image(
            painter = rememberImagePainter(data = photo.previewURL, builder = {
                placeholder(R.drawable.placeholder).build()
            }),
            contentDescription = "image tags ${photo.tags}",
            modifier = Modifier.size(128.dp),
            contentScale = ContentScale.FillBounds
        )
    }


}





