package com.stslex.feature_profile.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.stslex.core_data_source.NoteDao

interface ProfileRepository {

    suspend fun signOut()
    suspend fun getSyncNotesSize(): Int

    class Base(
        private val noteDao: NoteDao,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser
    ) : ProfileRepository {

        override suspend fun signOut() {
            Firebase.auth.signOut()
        }

        override suspend fun getSyncNotesSize(): Int {
            return 0
        }
    }
}