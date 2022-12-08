package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient

class PriorityRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    private val database = TaskDatabase.getDatabase(context).priorityDAO()

    companion object {
        private val cache = mutableMapOf<Int, String>()
        fun getDescription(id: Int): String {
            return cache[id] ?: ""
        }

        fun setDescription(id: Int, string: String) {
            cache[id] = string
        }
    }


    fun getDescription(id: Int): String {
        val cached = PriorityRepository.getDescription(id)
        if (PriorityRepository.getDescription(id) == "") {
            val description = database.getDescription(id)
            PriorityRepository.setDescription(id, description)
            return description
        } else {
            return cached
        }
    }

    fun list(listener: APIListener<List<PriorityModel>>) {
        val call = remote.list()
        executeCall(remote.list(), listener)

    }

    fun list(): List<PriorityModel> {
        return database.list()
    }

    fun save(list: List<PriorityModel>) {
        database.clear()
        database.save(list)
    }
}