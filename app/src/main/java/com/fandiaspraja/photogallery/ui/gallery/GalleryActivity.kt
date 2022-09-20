package com.fandiaspraja.photogallery.ui.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fandiaspraja.photogallery.core.data.Resource
import com.fandiaspraja.photogallery.core.ui.GalleryAdapter
import com.fandiaspraja.photogallery.core.utils.EndlessRecyclerViewScrollListener
import com.fandiaspraja.photogallery.core.utils.hide
import com.fandiaspraja.photogallery.core.utils.show
import com.fandiaspraja.photogallery.databinding.ActivityGalleryBinding
import com.fandiaspraja.photogallery.ui.gallerydetail.DetailGalleryActivity
import kotlinx.android.synthetic.main.activity_gallery.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryActivity : AppCompatActivity() {

    private val galleryViewModel: GalleryViewModel by viewModel()

    private lateinit var binding: ActivityGalleryBinding

    val  galleryAdapter = GalleryAdapter()

    var page = 1
    var perPage = 10
    var query: String? = null

    lateinit var loadMoreScrollListener : EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getGallery()

        observeGallery()

        galleryAdapter.onItemClick = {
            val intent = Intent(this, DetailGalleryActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }


        var layoutMan = LinearLayoutManager(this)

        loadMoreScrollListener = object : EndlessRecyclerViewScrollListener(layoutMan) {
            override fun onLoadMore(pages: Int) {

            }

            override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(view, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                getGallery()
            }
        }

        with(binding.rvGallery){
            layoutManager = layoutMan
            setHasFixedSize(true)
            adapter = galleryAdapter
            addOnScrollListener(loadMoreScrollListener)
        }

        search_gallery.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(searchname: String?): Boolean {
                query = searchname
                galleryAdapter.clearData()
                getSearchPhotos()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                return false
            }

        })

        search_gallery.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                galleryAdapter.clearData()
                query = null
                page = 1
                getGallery()
                return false
            }

        })
    }

    fun getSearchPhotos(){
        showProgress(true)
        query?.let {
            galleryViewModel.getSearchPhotos("PofQZ9yXkzO4FLXrbZZusNQdVOP9Q21BU3P_RAuXc14", page, perPage,
                it
            )
        }
    }

    fun getGallery(){
        showProgress(true)
        galleryViewModel.getPhotos("PofQZ9yXkzO4FLXrbZZusNQdVOP9Q21BU3P_RAuXc14", page, perPage)
        page += 1
    }

    fun observeGallery(){
        galleryViewModel.responsePhotos.observe(this, { data ->
            if (data.data != null){
                when (data) {
                    is Resource.Loading -> {
                        showProgress(true)
                    }

                    is Resource.Success -> {

                        galleryAdapter.setData(data.data)
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