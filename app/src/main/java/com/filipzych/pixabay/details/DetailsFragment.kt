package com.filipzych.pixabay.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.rememberImagePainter
import com.filipzych.pixabay.R
import com.filipzych.pixabay.gallery.GalleryViewModel
import com.filipzych.pixabay.gallery.data.entities.Hit
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsFragment : Fragment() {

    private val vm by sharedViewModel<GalleryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(context = requireContext()).apply {
            setContent {
                MaterialTheme {
                    DetailsLayout(viewModel = vm)
                }
            }
        }
    }

    @Composable
    fun DetailsLayout(viewModel: GalleryViewModel) {
        viewModel.selectedPhoto.observeAsState().value?.let {
            Column {
                Row(horizontalArrangement = Arrangement.Center) {
                    SelectedImage(photo = it)
                }
                Text("User name : ${it.user}")
                Text("Image tags : ${it.tags}")
                Text("Number of likes : ${it.likes}")
                Text("Number of downloads : ${it.downloads}")
                Text("Number of comments : ${it.comments}")
            }
        }

    }



    @Composable
    fun SelectedImage(photo: Hit) {
        Image(
            painter = rememberImagePainter(data = photo.largeImageURL, builder = {
                placeholder(R.drawable.placeholder).build()
            }),
            contentDescription = "image tags ${photo.tags}",
            modifier = Modifier.size(256.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}