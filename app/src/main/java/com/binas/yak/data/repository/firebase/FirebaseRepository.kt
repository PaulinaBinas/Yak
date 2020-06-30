package com.binas.yak.data.repository.firebase

import com.google.firebase.firestore.FirebaseFirestore

interface FirebaseRepository {

    fun getFirestoreInstance(): FirebaseFirestore
}