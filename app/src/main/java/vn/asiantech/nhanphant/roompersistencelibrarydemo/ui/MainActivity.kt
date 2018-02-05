package vn.asiantech.nhanphant.roompersistencelibrarydemo.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_insert_dialog.view.*
import vn.asiantech.nhanphant.roompersistencelibrarydemo.Application
import vn.asiantech.nhanphant.roompersistencelibrarydemo.BaseActivity
import vn.asiantech.nhanphant.roompersistencelibrarydemo.R
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.UserRepository
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.local.UserLocalDataSource
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User
import java.util.*


/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
class MainActivity : BaseActivity(), View.OnClickListener, UserAdapter.OnClickItemListener {
    private lateinit var mViewModel: UserViewModel
    private lateinit var mSubscription: CompositeDisposable
    private val mUsers = mutableListOf<User>()
    private var mRecyclerView: RecyclerView? = null
    private var mBtnAdd: FloatingActionButton? = null
    private var mBtnDelete: FloatingActionButton? = null
    private var mUserAdapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun bindViewModel() {
        val userLocalDataSource = UserLocalDataSource(Application.database?.userDao()!!)
        mViewModel = UserViewModel(UserRepository(userLocalDataSource))
        mSubscription = CompositeDisposable()

        // init
        initView()
        setupRecyclerView()

        getAllUsers()
    }


    override fun unbindViewModel() {
        mSubscription.clear()
    }

    private fun initView() {
        mRecyclerView = recyclerView
        mBtnAdd = btnAdd
        mBtnDelete = btnDelete
        mBtnAdd?.setOnClickListener(this)
        mBtnDelete?.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        mRecyclerView?.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false)
        mUserAdapter = UserAdapter(this, mUsers, this)
        mRecyclerView?.adapter = mUserAdapter
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnAdd -> {
                showInsertUserDialog()
            }
            R.id.btnDelete -> {
                deleteAllUsers()
            }
        }
    }

    override fun onClickItem(position: Int) {
        val editText = EditText(this)
        val user = mUsers[position]
        editText.setText(user.name)
        editText.hint = "Edit user name"
        editText.requestFocus()
        AlertDialog.Builder(this).setTitle("Edit")
                .setMessage("Edit user name")
                .setView(editText)
                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { _, _ ->
                    if (TextUtils.isEmpty(editText.text.toString())) {
                        return@OnClickListener
                    }
                    user.name = editText.text.toString()
                    updateUser(user)
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create()
                .show()
    }

    override fun onLongClickItem(position: Int): Boolean {
        AlertDialog.Builder(this).setTitle("Delete")
                .setMessage("Do you want delete this user?")
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    deleteUser(mUsers[position])
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create()
                .show()
        return true
    }

    private fun getAllUsers() {
        mSubscription.add(mViewModel.getAllUsers()
                .subscribe({
                    getAllUserSuccess(it)
                }, {
                    e("aaaa", "get all user error: ${it.message}")
                }))
    }

    private fun getAllUserSuccess(users: MutableList<User>) {
        mUsers.clear()
        mUsers.addAll(users)
        mUserAdapter?.notifyDataSetChanged()
    }

    private fun insertUser(user: User) {
        mSubscription.add(mViewModel.insertUser(user)
                .subscribe({
                    getAllUsers()
                }, {}
                ))
    }

    private fun deleteAllUsers() {
        mSubscription.add(mViewModel.deleteAllUsers()
                .subscribe({
                    getAllUsers()
                }, {}
                ))
    }

    private fun deleteUser(user: User) {
        mSubscription.add(mViewModel.deleteUser(user)
                .subscribe({
                    getAllUsers()
                }, {}
                ))
    }

    private fun updateUser(user: User) {
        mSubscription.add(mViewModel.updateUser(user)
                .subscribe({
                    getAllUsers()
                }, {}
                ))
    }

    @SuppressLint("InflateParams")
    private fun showInsertUserDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.item_insert_dialog, null)
        val edtName = view.edtName
        val edtAge = view.edtAge
        AlertDialog.Builder(this).setTitle("Create")
                .setMessage("Create new user")
                .setView(view)
                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { _, _ ->
                    if (TextUtils.isEmpty(edtName.text.toString()) || TextUtils.isEmpty(edtAge.text.toString())) {
                        return@OnClickListener
                    }
                    val user = User()
                    val rand = System.currentTimeMillis()
                    user.id = rand
                    user.gender = user.id + 1
                    user.name = edtName.text.toString()
                    user.age = edtAge.text.toString().toInt()
                    user.createAt = Calendar.getInstance().time
                    insertUser(user)
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create()
                .show()
    }
}
