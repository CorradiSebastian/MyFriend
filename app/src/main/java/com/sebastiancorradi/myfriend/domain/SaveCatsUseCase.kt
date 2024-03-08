package com.sebastiancorradi.myfriend.domain

import android.util.Log
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.ICatDataSource
import com.sebastiancorradi.myfriend.datasource.repository.db.AppDatabase
import javax.inject.Inject

class SaveCatsUseCase @Inject constructor() {
    suspend operator  fun invoke(cats: List<Cat>) {
        val db = AppDatabase.getDb()
        db.catDao().createAll(cats)
    }
}