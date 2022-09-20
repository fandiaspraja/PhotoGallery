package com.fandiaspraja.photogallery.di

import com.fandiaspraja.photogallery.core.domain.usecase.GalleryInteractor
import com.fandiaspraja.photogallery.core.domain.usecase.GalleryUseCase
import com.fandiaspraja.photogallery.ui.gallery.GalleryViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val userCaseModule = module {
    factory<GalleryUseCase> { GalleryInteractor(get()) }
}

val viewModelModule = module {

    viewModel { GalleryViewModel(get()) }
}

