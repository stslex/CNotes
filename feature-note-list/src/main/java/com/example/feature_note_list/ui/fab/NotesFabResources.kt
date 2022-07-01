package com.example.feature_note_list.ui.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.vector.ImageVector
import com.stslex.core_resources.R

sealed interface NotesFabResources {

    val label: Int
    val icon: ImageVector

    object Create : NotesFabResources {
        override val label: Int = R.string.lb_create
        override val icon: ImageVector = Icons.Default.Create
    }

    object Delete : NotesFabResources {
        override val label: Int = R.string.lb_delete
        override val icon: ImageVector = Icons.Default.Delete
    }
}