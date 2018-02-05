package vn.asiantech.nhanphant.roompersistencelibrarydemo.util

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * AsianTech Inc.
 * Created by nhanphant on 25/12/2017.
 */
class DateTimeConverter {
    @TypeConverter
    fun longToDate(value: Long): Date = value.let { Date(value) }

    @TypeConverter
    fun dateToLong(date: Date): Long = date.time
}
