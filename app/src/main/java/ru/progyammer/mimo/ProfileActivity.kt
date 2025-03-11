package ru.progyammer.mimo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.progyammer.mimo.model.Post
import ru.progyammer.mimo.model.Profile
import ru.progyammer.mimo.ui.adapter.PostAdapter

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        enableEdgeToEdge()

        val profile = getExampleProfile()
        fillData(profile)

        val subscribeButton: Button = findViewById(R.id.subscribe_button)
        subscribeButton.setOnClickListener {
            subscribeButton.text = "Подписки"
            Toast.makeText(
                this,
                "Подписан)",
                Toast.LENGTH_SHORT
            ).show()
        }

        val messageButton: Button = findViewById(R.id.message_button)
        messageButton.setOnClickListener {
            Toast.makeText(
                this,
                "Скоро можно будет написать сообщение)",
                Toast.LENGTH_SHORT
            ).show()
        }

        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun fillData(profile: Profile) {
        val nameView: TextView = findViewById(R.id.profile_name)
        nameView.text = profile.name

        val descView: TextView = findViewById(R.id.profile_desc)
        descView.text = profile.description

        val imageView: ImageView = findViewById(R.id.profile_image)
        Glide.with(this).load(profile.photoUrl).apply(RequestOptions.circleCropTransform())
            .into(imageView)

        val postsCountView: TextView = findViewById(R.id.posts_count)
        postsCountView.text = profile.posts.size.toString()

        val followersCountView: TextView = findViewById(R.id.followers_count)
        followersCountView.text = profile.followersCount.toString()

        val subscriptionsCountView: TextView = findViewById(R.id.subscriptions_count)
        subscriptionsCountView.text = profile.subscriptionsCount.toString()

        val postsView: RecyclerView = findViewById(R.id.profile_posts)
        val postAdapter = PostAdapter(profile.posts)
        postsView.layoutManager = LinearLayoutManager(this)
        postsView.adapter = postAdapter
    }

    private fun getExampleProfile(): Profile {
        val posts = mutableListOf<Post>()
        posts.add(
            Post(
                1,
                "https://avatars.mds.yandex.net/i?id=8af7988be6a75f921d6f3ee6538a4b8a_l-10024022-images-thumbs&n=13",
                2343,
                330,
                "Первый пост)",
            )
        )
        posts.add(
            Post(
                2,
                "https://i.pinimg.com/736x/09/92/f8/0992f834a3461611fb80775735c262a1.jpg",
                8932,
                219,
                "Второй пост)",
            )
        )
        posts.add(
            Post(
                3,
                "https://cache3.youla.io/files/images/780_780/5e/d6/5ed66e95ccb633422a2c5092.jpg",
                4497,
                94,
                "Третий пост)",
            )
        )

        return Profile(
            "prog.yammer",
            "https://svinki.ru/media/original_images/s1200.jpg",
            "Это мой основной аккаунт)))",
            52,
            78,
            posts,
        )
    }
}
