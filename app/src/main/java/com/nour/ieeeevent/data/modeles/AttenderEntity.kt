package com.nour.ieeeevent.data.modeles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AttenderEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo("id") var id :Int,
    @ColumnInfo("name")  var name :String ,
    @ColumnInfo("VIP")  var VIP :String ) {
}