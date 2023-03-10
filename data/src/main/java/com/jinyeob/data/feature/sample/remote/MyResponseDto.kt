package com.jinyeob.data.feature.sample.remote

import com.jinyeob.doamin.feature.sample.model.MyModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MyResponseDto(
    @Json(name = "id")
    val id: Long,

    @Json(name = "name")
    val name: String?
) {
    companion object {
        fun MyResponseDto.toModel() = MyModel(
            id = id,
            name = name ?: ""
        )
    }
}
