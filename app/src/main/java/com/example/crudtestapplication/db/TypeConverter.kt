package com.example.crudtestapplication.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.crudtestapplication.model.Source

class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(sourceAsString: String): Source {
        return sourceAsString.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])
        }
    }
}