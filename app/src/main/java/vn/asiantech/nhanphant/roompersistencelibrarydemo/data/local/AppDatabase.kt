package vn.asiantech.nhanphant.roompersistencelibrarydemo.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User
import vn.asiantech.nhanphant.roompersistencelibrarydemo.util.DateTimeConverter

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
@Database(entities = [User::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME = "app-database"
    }

    abstract fun userDao(): UserDao
}
