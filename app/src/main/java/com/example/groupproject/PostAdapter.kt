import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groupproject.Post
import com.example.groupproject.R

class PostAdapter(private val posts: MutableList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun submitList(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postIdTextView: TextView = itemView.findViewById(R.id.postIdTextView)
        private val userIdTextView: TextView = itemView.findViewById(R.id.userIdTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        private val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)

        fun bind(post: Post) {
            postIdTextView.text = "Post ID: ${post.postId}"
            userIdTextView.text = "User ID: ${post.userId}"
            contentTextView.text = "Content: ${post.content}"
            timestampTextView.text = "Timestamp: ${post.timestamp}"
        }
    }
}
