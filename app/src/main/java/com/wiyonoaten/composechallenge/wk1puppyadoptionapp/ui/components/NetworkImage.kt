/*
 * Copyright 2021 Wiyono Aten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.R
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.LocalPreviewMode

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String?,
    @DrawableRes placeholderResId: Int? = null,
    @DrawableRes errorResId: Int? = null
) {

    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }

    if (LocalPreviewMode.current) {
        Image(
            painterResource(id = placeholderResId ?: R.drawable.ic_placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
        return
    }

    DisposableEffect(url) {
        val picasso = Picasso.get()

        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                drawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                drawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap?.asImageBitmap()
            }
        }

        picasso.load(url).apply {
            if (placeholderResId != null) placeholder(placeholderResId)
            if (errorResId != null) error(errorResId)
            into(target)
        }

        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }

    if (image != null) {
        val theImage: ImageBitmap = image!!
        Image(bitmap = theImage, contentDescription = null, modifier = modifier, contentScale = ContentScale.Crop)
    } else if (drawable != null) {
        val theDrawable = drawable!!
        Canvas(modifier = modifier) {
            drawIntoCanvas { canvas ->
                val aspectRatio = theDrawable.intrinsicWidth.toFloat() / theDrawable.intrinsicHeight
                val scaledWidth = theDrawable.intrinsicWidth.coerceAtMost(size.width.toInt())
                val scaledHeight = scaledWidth / aspectRatio
                val left = (size.width - scaledWidth).coerceAtLeast(0f) / 2
                val top = (size.height - scaledHeight) / 2
                theDrawable.setBounds(left.toInt(), top.toInt(), scaledWidth, scaledHeight.toInt())
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}
