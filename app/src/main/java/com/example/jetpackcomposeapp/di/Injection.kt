package com.example.jetpackcomposeapp.di

import com.example.jetpackcomposeapp.data.OriginoteRepository

object Injection {
    fun provideRepository(): OriginoteRepository {
        return OriginoteRepository.getInstance()
    }
}