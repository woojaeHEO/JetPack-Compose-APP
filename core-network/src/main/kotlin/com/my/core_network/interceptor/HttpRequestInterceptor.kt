package com.my.core_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        return chain.proceed(request)
    }
}