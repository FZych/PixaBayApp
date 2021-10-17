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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.rememberImagePainter
import com.filipzych.pixabay.gallery.data.entities.Hit
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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
                }
            }
        }
    }

    @Composable
    fun GalleryLayout(viewModel: GalleryViewModel) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp) ,
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
            TextField(value = textValue, onValueChange = onTextChanged, modifier = Modifier.fillMaxWidth())
        }
    }

    @Composable
    fun GalleryHandler(viewModel: GalleryViewModel) {
        viewModel.images.observeAsState().value.let {
            GalleryGrid(it ?: emptyList())
        }
    }

    @Composable
    fun GalleryGrid(galleryItems: List<Hit>) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(galleryItems.size) { photo ->
                PhotoItem(galleryItems[photo])
            }
        }
    }

    @Composable
    fun PhotoItem(photo: Hit) {
        Image(
            painter = rememberImagePainter(photo.previewURL),
            contentDescription = null,
            modifier = Modifier.size(128.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}





