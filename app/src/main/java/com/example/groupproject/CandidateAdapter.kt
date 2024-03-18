import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.groupproject.Candidate
import com.example.groupproject.DetailActivity
import com.example.groupproject.R


class CandidateAdapter : RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder>() {

    private val candidatesList = mutableListOf<Candidate>()

    inner class CandidateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val photoImageView: ImageView = itemView.findViewById(R.id.photoImageView)

        fun bind(candidate: Candidate) {
            nameTextView.text = candidate.name
            titleTextView.text = candidate.title
            // Load photo using Glide
            Glide.with(itemView)
                .load(candidate.photoUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(photoImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_candidate, parent, false)
        return CandidateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        val candidate = candidatesList[position]
        holder.bind(candidate)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra("name", candidate.name)
                putExtra("title", candidate.title)
                putExtra("largerImageUrl", candidate.photoUrl) // Assuming the field is 'photoUrl'
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = candidatesList.size

    fun submitList(newList: List<Candidate>) {
        candidatesList.clear()
        candidatesList.addAll(newList)
        notifyDataSetChanged()
    }
}

