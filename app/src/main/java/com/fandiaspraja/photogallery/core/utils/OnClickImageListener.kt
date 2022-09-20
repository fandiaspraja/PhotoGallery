package com.fandiaspraja.photogallery.core.utils

import java.io.File

interface OnClickImageListener {

    fun onClickImageResource(type: Int)

    fun OnCreatePhotoFile(photo: File)
}