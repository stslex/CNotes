package com.stslex.feature_note_list.ui.core

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.stslex.core_model.model.NoteDynamicUI

object UIObjectsExt {
    fun SnapshotStateList<NoteDynamicUI>.clearSelection() {
        forEach { it.setSelect(false) }
        clear()
    }
}