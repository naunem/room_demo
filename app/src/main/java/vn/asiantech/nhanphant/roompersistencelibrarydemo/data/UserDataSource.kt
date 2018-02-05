package vn.asiantech.nhanphant.roompersistencelibrarydemo.data

import io.reactivex.Single
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
interface UserDataSource {
    fun getAllUsers(): Single<MutableList<User>>

    fun insertUser(user: User)

    fun deleteAllUsers()

    fun deleteUser(user: User)

    fun updateUser(user: User)
}
