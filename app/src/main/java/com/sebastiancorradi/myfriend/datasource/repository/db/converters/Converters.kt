package com.sebastiancorradi.myfriend.datasource.repository.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StringListConverters {
    @TypeConverter
    fun fromStringListToString(stringList: List<String>): String = stringList.toString()
    @TypeConverter
    fun toStringListFromString(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (e: Exception) {

            }
        }
        return result
    }
}