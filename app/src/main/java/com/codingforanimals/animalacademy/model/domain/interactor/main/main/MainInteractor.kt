package com.codingforanimals.animalacademy.model.domain.interactor.main.main

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.codingforanimals.animalacademy.model.data.repositories.main.MainRepository

class MainInteractor {

    private val PATH_USERS = "users/"

    private val repository = MainRepository()

    fun getFirebaseUser(): FirebaseUser? = repository.getFirebaseUser()

    fun getUserInfo(userId: String): Task<DocumentSnapshot> =
        repository.getUserInfo(PATH_USERS + userId)
}