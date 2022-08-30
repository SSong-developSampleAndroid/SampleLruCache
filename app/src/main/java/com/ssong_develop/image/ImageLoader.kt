package com.ssong_develop.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import kotlinx.coroutines.*
import java.net.URL

object ImageLoader {
    private val cache = BitmapLruCache()

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun loadImage(url: String, completed: (Bitmap?) -> Unit) {
        if (url.isEmpty()) {
            completed(null)
            return
        }

        if (cache.get(url) != null) {
            completed(cache[url])
            return
        }

        scope.launch(Dispatchers.IO) {
            try {
                val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
                cache.put(url,bitmap)

                withContext(Dispatchers.Main) {
                    completed(bitmap)
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    completed(null)
                }
            }
        }
    }
}