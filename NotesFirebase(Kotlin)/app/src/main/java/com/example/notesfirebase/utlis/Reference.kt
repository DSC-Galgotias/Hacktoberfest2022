package com.example.notesfirebase.utlis

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object Reference {
    var collectionReference: CollectionReference? = null
    fun getReference(): CollectionReference? {
        return FirebaseFirestore.getInstance().collection("notes")
            .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .collection("myNotes")
    }
}