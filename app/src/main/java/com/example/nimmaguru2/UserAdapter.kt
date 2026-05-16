package com.example.nimmaguru2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.nameText)
        val role = itemView.findViewById<TextView>(R.id.roleText)
        val subject = itemView.findViewById<TextView>(R.id.subjectText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = userList[position]

        holder.name.text = user.name ?: "No Name"
        holder.role.text = user.role ?: "No Role"
        holder.subject.text = user.subject ?: "No Subject"

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(context, ProfileActivity::class.java)

            intent.putExtra("name", user.name ?: "N/A")
            intent.putExtra("role", user.role ?: "N/A")
            intent.putExtra("subject", user.subject ?: "N/A")

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = userList.size
}