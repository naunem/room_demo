package vn.asiantech.nhanphant.roompersistencelibrarydemo.data.local

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Single
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE name = :name")
    fun getUsers(name: String): MutableList<User>

    @Query("SELECT * FROM user")
    fun getAllUsers(): Single<MutableList<User>>

    // Methods annotated with @Insert can return either void, long, Long, long[], Long[] or List<Long>.
    @Insert(onConflict = REPLACE)
    fun insertUser(vararg user: User)

//    @Insert
//    fun insertBoth(user: User, user1: User)
//
//    @Insert
//    fun insertAll(users: MutableList<User>)

    // Methods annotated with @Delete can return either void, long, Long, long[], Long[] or List<Long>.
    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAllUsers()

    // Methods annotated with @Update can return either void, long, Long, long[], Long[] or List<Long>.
    @Update
    fun updateUser(user: User)
}
