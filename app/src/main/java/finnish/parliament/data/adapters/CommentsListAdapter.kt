package finnish.parliament.data.adapters
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import finnish.parliament.data.model.Comments
import finnish.parliament.data.repository.CommentsRepository
import finnish.parliament.databinding.CommentListViewBinding

class CommentsListAdapter :
    ListAdapter<Comments, CommentsListAdapter.CommentListViewHolder>(CommentDiffCallback) {

    val comments = CommentsRepository.commentData


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListViewHolder {
        val binding = CommentListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentListViewHolder, position: Int) {
        val commentData = getItem(position)
        holder.binding.currentTime.text = commentData.timestamp
        holder.binding.commentField.text = commentData.comment
    }


    class CommentListViewHolder(val binding:CommentListViewBinding) :
        RecyclerView.ViewHolder(binding.root)


    companion object CommentDiffCallback : DiffUtil.ItemCallback<Comments>() {
        override fun areItemsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem.personNumber == newItem.personNumber
        }
    }
}