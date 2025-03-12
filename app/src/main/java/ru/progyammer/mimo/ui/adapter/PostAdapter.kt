package ru.progyammer.mimo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.progyammer.mimo.R
import ru.progyammer.mimo.model.Post

class PostAdapter(private var posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.post_image)
        val likesCountView: TextView = view.findViewById(R.id.post_likes_count)
        val commentsCountView: TextView = view.findViewById(R.id.post_comments_count)
        val likeButton: ImageButton = view.findViewById(R.id.post_like_button)
        val commentButton: ImageButton = view.findViewById(R.id.post_comment_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.likesCountView.text = post.likesCount.toString()
        holder.commentsCountView.text = post.commentsCount.toString()
        Glide.with(holder.itemView.context).load(post.photoUrl).into(holder.imageView)

        var liked = false

        holder.likeButton.setOnClickListener {
            val iconRes = if (liked) {
                --post.likesCount
                R.drawable.ic_like
            } else {
                ++post.likesCount
                R.drawable.ic_pressed_like
            }

            holder.likeButton.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, iconRes))
            holder.likesCountView.text = post.likesCount.toString()
            liked = !liked
        }

        holder.commentButton.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Скоро можно будет оставлять комментарии)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount() = posts.size

    fun updateData(newProfiles: List<Post>) {
        val diffCallback = PostDiffCallback(posts, newProfiles)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        posts = newProfiles
        diffResult.dispatchUpdatesTo(this)
    }
}