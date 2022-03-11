package finnish.parliament.fragments
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import finnish.parliament.MyApp
import finnish.parliament.R
import finnish.parliament.data.adapters.MemberListAdapter
import finnish.parliament.databinding.FragmentMemberListBinding
import finnish.parliament.data.viewmodels.MemberListViewModel
import finnish.parliament.data.viewmodels.MemberListViewModelFactory

class MemberListFragment : Fragment() {

    private lateinit var viewModel: MemberListViewModel
    private lateinit var binding: FragmentMemberListBinding
    private lateinit var memberAdapter: MemberListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application= requireNotNull(activity).application
        val arguments =
            MemberListFragmentArgs.fromBundle(requireArguments()).selectedParty
        val viewModelFactory = MemberListViewModelFactory(arguments, application)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MemberListViewModel::class.java)
        memberAdapter = MemberListAdapter(this, MemberListAdapter.OnClickListener {
            viewModel.memberDetails(it)
        })

        binding.memberListView.adapter = memberAdapter
        binding.memberListView.layoutManager = LinearLayoutManager(MyApp.appContext)

        //Adds vertical divider for the itemViews
        binding.memberListView.addItemDecoration(DividerItemDecoration(MyApp.appContext, DividerItemDecoration.VERTICAL))

        viewModel.selectedParty.observe(viewLifecycleOwner) {
            memberAdapter.submitList(it)
        }
        // Navigates to the next fragment and takes the chosen parliamentData to the next fragment, so it can be used there.
        viewModel.selectedMember.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController().navigate(
                    MemberListFragmentDirections.actionMemberListFragmentToMemberDetailsFragment(it))
                viewModel.navigationDone()
            }
        }
        return binding.root
    }

}
