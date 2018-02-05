package vn.asiantech.nhanphant.roompersistencelibrarydemo.ui

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.UserRepository
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
class UserViewModel(private val repository: UserRepository) {
    fun getAllUsers(): Single<MutableList<User>> {
        return repository.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertUser(user: User): Single<Boolean> {
        return Single.create<Boolean> {
            repository.insertUser(user)
            it.onSuccess(true)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteAllUsers(): Single<Boolean> {
        return Single.create<Boolean> {
            repository.deleteAllUsers()
            it.onSuccess(true)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun deleteUser(user: User): Single<Boolean> {
        return Single.create<Boolean> {
            repository.deleteUser(user)
            it.onSuccess(true)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun updateUser(user: User): Single<Boolean> {
        return Single.create<Boolean> {
            repository.updateUser(user)
            it.onSuccess(true)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

    }
}