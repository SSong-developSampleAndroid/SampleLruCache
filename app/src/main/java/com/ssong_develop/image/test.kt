package com.ssong_develop.image

import android.graphics.Bitmap
import androidx.collection.LruCache

class BitmapLruCache : LruCache<String, Bitmap>(4 * 1024 * 1024) {
    override fun sizeOf(key: String, value: Bitmap): Int {
        return value.byteCount
    }
}