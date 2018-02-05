package vn.asiantech.nhanphant.roompersistencelibrarydemo.data

import io.reactivex.Single
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.local.UserLocalDataSource
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
class UserRepository(private val userLocalDataSource: UserLocalDataSource) : UserDataSource {

    override fun getAllUsers(): Single<MutableList<User>> {
        return userLocalDataSource.getAllUsers()
    }

    override fun insertUser(user: User) {
        userLocalDataSource.insertUser(user)
    }

    override fun deleteAllUsers() {
        userLocalDataSource.deleteAllUsers()
    }

    override fun deleteUser(user: User) {
        userLocalDataSource.deleteUser(user)
    }

    override fun updateUser(user: User) {
        userLocalDataSource.updateUser(user)
    }
}