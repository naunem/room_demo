package vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.TypeConverters
import vn.asiantech.nhanphant.roompersistencelibrarydemo.util.DateTimeConverter
import java.util.*

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
@Entity(indices = [Index("name")],
        primaryKeys = ["id", "gender"])
@TypeConverters(DateTimeConverter::class)
data class User(var name: String?) : ParentUser(){
    var id: Long = 0
    var age: Int? = null
    var createAt: Date? = null
//    var gender: Int? = null
//    @Embedded
//    var address: Address = Address()
    @Ignore
    constructor() : this("")
}

open class ParentUser {
    var gender: Long = 0
}
