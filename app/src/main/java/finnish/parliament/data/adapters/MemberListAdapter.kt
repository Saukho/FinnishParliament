package finnish.parliament.data.adapters
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import finnish.parliament.R
import finnish.parliament.data.model.MemberOfParliament
import finnish.parliament.databinding.MemberListViewBinding

//RecyclerViewAdapter for MemberList

class MemberListAdapter(private val lifeCycle: LifecycleOwner,
                        private val onCliked: OnClickListener
                       ) :
    ListAdapter<MemberOfParliament, MemberListAdapter.MemberListViewHolder>(MemberDiffCallback) {

    //viewholder for each row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val binding = MemberListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberListViewHolder(binding)
    }
    //bind holder
    override fun onBindViewHolder(holder: MemberListViewHolder, position: Int) {
        //set details for each member
        val memberData = getItem(position)
        val fullName = memberData.first + " " + memberData.last
        val minister = if (!memberData.minister) {
            "Member"
        } else {
            "Minister"
        }
        //set full names for party
        holder.binding.memberParty.setImageResource(
            when (memberData.party) {
                "sd" -> R.drawable.sdp
                "kesk" -> R.drawable.keskusta
                "ps" -> R.drawable.perus
                "r" -> R.drawable.rkp
                "kd" -> R.drawable.kristillis
                "vas" -> R.drawable.vasemmisto
                "vihr" -> R.drawable.vihreat
                "kok" -> R.drawable.kokoomus
                else -> R.drawable.liike
            })
        holder.binding.memberName.text = fullName
        //set clickable recyclerview for each row
        holder.itemView.setOnClickListener {
            onCliked.onClick(memberData)
        }
    }
    //bind MemberListFragment for recyclerview
    class MemberListViewHolder(val binding: MemberListViewBinding) :
        RecyclerView.ViewHolder(binding.root)
    //if not null then cross-check if items are same and then returns content if the contents are same

    companion object MemberDiffCallback : DiffUtil.ItemCallback<MemberOfParliament>() {
        override fun areItemsTheSame(oldItem: MemberOfParliament, newItem: MemberOfParliament): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: MemberOfParliament, newItem: MemberOfParliament): Boolean {
            return oldItem.personNumber == newItem.personNumber
        }
    }
    //OnClickListener for each row item
    class OnClickListener(val clickListener: (members: MemberOfParliament) -> Unit) {
        fun onClick(members: MemberOfParliament) = clickListener(members)
    }
}