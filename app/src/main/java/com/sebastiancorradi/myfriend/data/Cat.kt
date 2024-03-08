package com.sebastiancorradi.myfriend.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Cat(
    @PrimaryKey(autoGenerate = true)
    val internalId: Int = 0,
    @SerializedName("_id")
    val id: String= "",
    val tags: List<String> = listOf(),
    val mimetype: String = "",
    val size: Int = 64591
): Parcelable {}