package com.example.museumsenkiv.data.repository

import com.example.museumsenkiv.data.dao.MuseumResponseDao
import com.example.museumsenkiv.data.entity.MuseumService
import com.example.museumsenkiv.data.entity.response.artobjectResponse.ArtObject
import com.example.museumsenkiv.data.entity.response.departmentsResponse.Department

open class MuseumRepository(
    private val museumService: MuseumService,
    private val museumResponseDao: MuseumResponseDao
) {

    suspend fun getDepartments(): List<Department> {
        val departments = museumResponseDao.getDepartments()
        return if (departments.isEmpty()) {
            val apiDepartments = museumService.getDepartmentsData().departments
            val validDepartments = apiDepartments.filter { it.displayName.isNotBlank() }
            validDepartments.forEach { department ->
                val existingDepartment = museumResponseDao.getDepartmentById(department.departmentId)
                if (existingDepartment == null) {
                    museumResponseDao.insertDepartment(department)
                }
            }
            museumResponseDao.getDepartments()
        } else {
            departments
        }
    }
    suspend fun getArtList(query: String, departmentId: Int): List<ArtObject> {
        val cachedArtObjects = museumResponseDao.getArtObjectsByDepartment(departmentId)
        if (cachedArtObjects.isNotEmpty()) {
            return cachedArtObjects
        } else {
            val departmentIDsResponse = museumService.searchArt(q = query, departmentId = departmentId)
            val objectIDs = departmentIDsResponse?.objectIDs ?: emptyList()

            if (objectIDs.isEmpty()) return emptyList()

            val artObjects = objectIDs.mapNotNull { objectID ->
                try {
                    museumService.getObjectDetails(id = objectID)
                } catch (e: Exception) {
                    null
                }
            }
            return artObjects
        }
    }

    suspend fun getArtDetails(objectID: Int): ArtObject? {
        val cachedArtDetails = museumResponseDao.getArtObjectById(objectID)
        return cachedArtDetails ?: run {
                val artDetails = museumService.getObjectDetails(id = objectID)
                artDetails
        }
    }
}
