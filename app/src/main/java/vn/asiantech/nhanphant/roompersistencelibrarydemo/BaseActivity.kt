package vn.asiantech.nhanphant.roompersistencelibrarydemo

import android.support.v7.app.AppCompatActivity

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        bindViewModel()
    }

    override fun onPause() {
        super.onPause()
        unbindViewModel()
    }

    abstract fun bindViewModel()
    abstract fun unbindViewModel()
}