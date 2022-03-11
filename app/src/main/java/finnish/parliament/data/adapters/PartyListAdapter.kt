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
import finnish.parliament.R
import finnish.parliament.data.model.MemberOfParliament
import finnish.parliament.databinding.PartyListViewBinding


//RecyclerViewAdapter for partylist
class PartyListAdapter(private val onClicked: OnClickListener) :
    ListAdapter<MemberOfParliament, PartyListAdapter.PartyViewHolder>(PartyDiffCallback) {

    //viewholder for each row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val binding = PartyListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartyViewHolder(binding)
    }
    //bind holder
    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        val partyView = getItem(position)
        //set image for each party
        holder.binding.partyImage.setImageResource(
            when (partyView.party) {
                "ps" -> R.drawable.perus
                "vihr" -> R.drawable.vihreat
                "sd" -> R.drawable.sdp
                "kesk" -> R.drawable.keskusta
                "kok" -> R.drawable.kokoomus
                "vas" -> R.drawable.vasemmisto
                "r" -> R.drawable.rkp
                "kd" -> R.drawable.kristillis
                "liik" -> R.drawable.liike
                else -> R.drawable.vkk
            })
        //set full names for party
            when(partyView.party){
                "ps" -> holder.binding.partyName.text = "Perussuomalaiset"
                "vihr"->holder.binding.partyName.text= "Vihreä liitto"
                "sd" ->holder.binding.partyName.text= "Suomen Sosialidemokraattinen Puolue"
                "kesk" ->holder.binding.partyName.text= "Suomen Keskusta"
                "kok" ->holder.binding.partyName.text= "Kansallinen Kokoomus"
                "vas" ->holder.binding.partyName.text= "Vasemmistoliitto"
                "r" -> holder.binding.partyName.text="Suomen ruotsalainen kansanpuolue"
                "kd" ->holder.binding.partyName.text= "Suomen Kristillisdemokraatit"
                "liik" ->holder.binding.partyName.text= "Liike Nyt"
                else -> holder.binding.partyName.text="Valta kuuluu kansalle"
            }
        //set clickable recyclerview for each row
        holder.itemView.setOnClickListener {
            onClicked.onClick(partyView)
        }
    }
    //bind PartyListFragment for recyclerview
    class PartyViewHolder(val binding: PartyListViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    //if not null then cross-check if items are same and then returns content if the contents are same
    companion object PartyDiffCallback : DiffUtil.ItemCallback<MemberOfParliament>() {
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