package vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

/**
 * AsianTech Inc.
 * Created by nhanphant on 25/12/2017.
 */
@Entity(foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = CASCADE)])
data class Book(
        var title: String,
        @ColumnInfo(name = "user_id")
        var userId: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    var bookId: Int = 0
}
