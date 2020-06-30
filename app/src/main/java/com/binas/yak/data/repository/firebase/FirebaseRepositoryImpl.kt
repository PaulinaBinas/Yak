package com.binas.yak.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepositoryImpl() : FirebaseRepository {

    override fun getFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}