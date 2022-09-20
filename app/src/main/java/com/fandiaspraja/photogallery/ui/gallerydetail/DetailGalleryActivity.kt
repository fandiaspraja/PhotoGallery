package com.fandiaspraja.photogallery.ui.gallerydetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fandiaspraja.photogallery.core.data.Resource
import com.fandiaspraja.photogallery.core.utils.hide
import com.fandiaspraja.photogallery.core.utils.show
import com.fandiaspraja.photogallery.databinding.ActivityDetailGalleryBinding
import com.fandiaspraja.photogallery.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.activity_detail_gallery.*
import kotlinx.android.synthetic.main.activity_detail_gallery.progressBar
import kotlinx.android.synthetic.main.content_detail_gallery.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGalleryActivity : AppCompatActivity() {

    var id = ""

    private val galleryViewModel: GalleryViewModel by viewModel()

    private lateinit var binding: ActivityDetailGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null){
            id = intent.getStringExtra("id")!!

        }

        getPhotosById()
        observePhotosById()
    }

    fun getPhotosById(){
        showProgress(true)
        galleryViewModel.getPhotosById(id,"PofQZ9yXkzO4FLXrbZZusNQdVOP9Q21BU3P_RAuXc14")
    }

    fun observePhotosById(){
        galleryViewModel.response.observe(this, { data ->
            if (data.data != null){
                when (data) {
                    is Resource.Loading -> {
                        showProgress(true)
                    }

                    is Resource.Success -> {

                        Glide.with(this)
                            .load(data.data.urlImage)
                            .into(iv_detail_image)

                        tv_detail_description.setText(data.data.description)

                        toolbar.setTitle(data.data.userName)

                        showProgress(false)

                    }
                    is Resource.Error -> {
                        showProgress(false)

//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text = phone.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    fun showProgress(isShow: Boolean){
        if (isShow){
            progressBar.show()
        }else{
            progressBar.hide()
        }
    }
}