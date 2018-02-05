package vn.asiantech.nhanphant.roompersistencelibrarydemo.data.local

import io.reactivex.Single
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.UserDataSource
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
class UserLocalDataSource(private val userDao: UserDao) : UserDataSource {

    override fun getAllUsers(): Single<MutableList<User>> {
        return userDao.getAllUsers()
    }

    override fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}