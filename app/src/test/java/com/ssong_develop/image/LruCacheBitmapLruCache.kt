package com.ssong_develop.image

import android.util.Log
import androidx.collection.LruCache
import junit.framework.Assert.assertEquals
import org.junit.Test

// Least Recently Used(최근에 가장 적게 참조됨)
// LruCache는 제한된 사이즈에서 참조된지 가장 오래된 객체를 제거하는 데이터구조
// LruCache를 사용할떄는 자주 참조되는 객체일 수록 빠르게 캐시를 통해 객체에 접근할 수 있다.
class LruCacheBitmapLruCache {

    val cache = LruCache<String,Int>(5)

    @Test
    fun lruCacheExample1() {
        cache.put("A",0) // [A]
        cache.put("B",0) // [A,B]
        cache.put("C",0) // [A,B,C]
        cache.put("D",0) // [A,B,C,D]
        cache.put("E",0) // [A,B,C,D,E]

        //
        cache.put("F",0) // [B,C,D,E,F]
        // A를 가장 최근에 사용하지 않았기 떄문에
        cache.put("D",0) // [B,C,E,F,D]
        // 가장 최근에 사용된 것은 우측으로, 가장 사용되지 않은 것은 좌측으로 데이터들이 재배열된다.
        assertEquals(cache.size(), 5)
    }

}