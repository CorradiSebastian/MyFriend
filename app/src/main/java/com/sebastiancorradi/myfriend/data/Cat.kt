package com.sebastiancorradi.myfriend.data

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    @SerializedName("_id")
    val id: String= "",
    val tags: List<String> = listOf(),
    val owner: String = "",
    val createdAt: String = "",
    val upatedAt: String = ""
): Parcelable {}