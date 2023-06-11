package com.example.jetpackcomposeapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class OriginoteRepository {
    private val dataOriginote = mutableListOf<OriginoteList>()

    init {
        if (dataOriginote.isEmpty()){
            DataSource.originote.forEach {
                dataOriginote.add(OriginoteList(it,0))
            }
        }
    }

    fun getAllData(): Flow<List<OriginoteList>>{
        return flowOf(dataOriginote)
    }

    fun getDataById(dataId: Long): OriginoteList{
        return dataOriginote.first {
            it.originote.id == dataId
        }
    }

    companion object {
        @Volatile
        private var instance: OriginoteRepository? = null

        fun getInstance(): OriginoteRepository =
            instance ?: synchronized(this) {
                OriginoteRepository().apply {
                    instance = this
                }
            }
    }
}