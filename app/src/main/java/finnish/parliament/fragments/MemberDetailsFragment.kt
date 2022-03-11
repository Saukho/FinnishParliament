package finnish.parliament.fragments
/*
*08.03.2022
* Sami Sihvonen
* 2110603
*  MemberDetailsFragment
*
 */
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import finnish.parliament.R
import finnish.parliament.data.adapters.bindImage
import finnish.parliament.data.model.Comments
import finnish.parliament.data.model.Likes
import finnish.parliament.data.viewmodels.MemberDetailsViewModel
import finnish.parliament.data.viewmodels.MemberDetailsViewModelFactory
import finnish.parliament.databinding.FragmentMemberDetailsBinding
import java.text.SimpleDateFormat
import java.util.*

class MemberDetailsFragment : Fragment() {
    //bind fragment and ViewModel
    private lateinit var binding: FragmentMemberDetailsBinding
    private lateinit var viewModel: MemberDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                             ): View {
        val application = requireNotNull(activity).application
        val dataSource = MemberDetailsFragmentArgs.fromBundle(
            requireArguments()).selectedMember
        val viewModelFactory = MemberDetailsViewModelFactory(dataSource, application)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_member_details, container, false)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MemberDetailsViewModel::class.java)
        //bind MemberDetailsViewModel with MemberDetailsViewModel
        binding.viewModel = viewModel

        //update memberdetail view
        updateMemberInfo()
        binding.saveCommentsButton.setOnClickListener { addComments() }
        binding.showCommentButton.setOnClickListener { showComments() }
        binding.dislikeButton.setOnClickListener { disliked() }
        binding.likeButton.setOnClickListener { liked() }

        return binding.root
    }


    //update member view
    private fun updateMemberInfo(){
       //image binder adapter for fetch images from eduskunta api
        bindImage(
            binding.memberImage,
            "https://avoindata.eduskunta.fi/${viewModel.selectedMember?.picture}"
                 )
       //set binding memberInfo
        binding.partyButton.setImageResource(viewModel.partyImage)
        binding.memberDetailName.text = viewModel.fullname
        binding.memberDetailConstituency.text = viewModel.constituency
        binding.memberAge.text = viewModel.age
        binding.memberParty.text = viewModel.party
    }

    private fun addComments() {
        val personNumber = viewModel.selectedMember?.personNumber ?: 0
        val comment = binding.insertComment.text.toString()
        val timestamp = getCurrentDate()
        val commentData = Comments(personNumber, comment, timestamp)

        viewModel.selectedMember?.let {
            viewModel.addComment(
                commentData
                                )
        }
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
    }
    //show saved comments next fragment
    private fun showComments() {
        viewModel.selectedMember?.let {
            this.findNavController().navigate(MemberDetailsFragmentDirections.actionMemberDetailsFragmentToCommentsFragment(it))
        }
    }

    private fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'-'HH:mm:ss")
        return sdf.format(Date())
    }
    private fun disliked(){
        val personNumber = viewModel.selectedMember?.personNumber ?: 0
        val timestamp =getCurrentDate()
        val likesData  = Likes(personNumber,-1,timestamp)
        viewModel.selectedMember?.let {
            viewModel.addDislikes(likesData)
        }
    }
    private fun liked(){
        val personNumber = viewModel.selectedMember?.personNumber ?: 0
        val timestamp =getCurrentDate()
        val likesData  = Likes(personNumber,+1,timestamp)
        viewModel.selectedMember?.let {
            viewModel.addDislikes(likesData)
        }
    }


}
