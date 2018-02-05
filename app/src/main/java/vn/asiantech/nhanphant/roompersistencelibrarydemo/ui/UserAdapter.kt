package vn.asiantech.nhanphant.roompersistencelibrarydemo.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_user.view.*
import vn.asiantech.nhanphant.roompersistencelibrarydemo.R
import vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model.User
import java.text.SimpleDateFormat
import java.util.*

/**
 * AsianTech Inc.
 * Created by nhanphant on 19/12/2017.
 */
class UserAdapter(private val context: Context,
                  private val users: MutableList<User>,
                  private val onClickItemListener: OnClickItemListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        holder?.bindData(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var mTvUserId: TextView? = null
        private var mTvName: TextView? = null
        private var mTvAge: TextView? = null
        private var mTvCreateAt: TextView? = null

        init {
            mTvUserId = view.tvUserId
            mTvName = view.tvName
            mTvAge = view.tvAge
            mTvCreateAt = view.tvCreateAt

            view.setOnClickListener {
                onClickItemListener.onClickItem(adapterPosition)
            }

            view.setOnLongClickListener {
                onClickItemListener.onLongClickItem(adapterPosition)
            }
        }

        fun bindData(user: User) {
            mTvUserId?.text = user.id.toString()
            mTvName?.text = user.name
            mTvAge?.text = user.gender.toString()
            val createAt = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.ENGLISH)
            mTvCreateAt?.text = createAt.format(user.createAt)
        }
    }

    interface OnClickItemListener {
        fun onClickItem(position: Int)

        fun onLongClickItem(position: Int): Boolean
    }
}