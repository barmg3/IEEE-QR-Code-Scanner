package com.nour.ieeeevent.data.modeles


import com.google.gson.annotations.SerializedName

data class AttenderDto(
    @SerializedName("attenders")
    var attenders: List<List<Any>>
)