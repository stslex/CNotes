package com.stslex.core_data_source

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class NoteRoomDatabaseCallback(
    private val scope: CoroutineScope,
    private val dispatchers: AppDispatchers
) : RoomDatabase.Callback() {

    private val roomDatabase: NoteRoomDatabase by inject(NoteRoomDatabase::class.java)

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        roomDatabase.let { database ->
            scope.launch(dispatchers.io) { populateDatabase(database.noteDao()) }
        }
    }

    private suspend fun populateDatabase(noteDao: NoteDao) {
        noteDao.deleteAllNotes()
        val initialNote = NoteEntity(
            id = 0,
            title = initialTitle,
            content = initialContent,
            timestamp = System.currentTimeMillis()
        )
        noteDao.insertNote(initialNote)
    }

    companion object {
        private const val initialTitle = "Now in Android #61 — Special Google I/O 2022 Edition"
        private const val initialContent =
            "Last week was Google I/O, which means that we have a ton of stuff to cover; this post can serve as your guide to navigate all that content.\n" +
                    "\n" +
                    "First off, we had the Google Keynote, which had a ton of announcements about where Android and Pixel are heading. Then, the Developer Keynote covered top Android dev announcements such as the Compose for Wear OS Beta, the Compose 1.2 beta, the Health Connect API, and large screen updates including 12L and 13 features along with the large-screen-optimized Google Play Store." +
                    "We have two quick videos — a quick run through of what’s new, and #TheAndroidShow: What’s new for Android devs at I/O, in 60 seconds to move you quickly through some of the top material. For a more complete survey of what I/O 22 offers to Android developers in video form check out the What’s New in Android talk." +
                    "Finally, the Android Fireside Chat is back; Android leaders answered your questions from the stage." +
                    "After being available on this blog, our YouTube series, and a podcast, starting today, you can check out the alpha version of the Now in Android app on GitHub that was featured in the Google I/O 2022 Developer Keynote \uD83C\uDF89\n" +
                    "\n" +
                    "The app showcases best practices, opinionated designs, and solutions to complex real-world problems. It does so with an open source implementation of a real world app — a working app planned for publication on the Play Store that will help you keep up to date with your Android development areas of interest. It’s still under heavy development and isn’t yet feature complete, so stay tuned here for updates."
    }
}