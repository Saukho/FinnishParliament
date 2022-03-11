package finnish.parliament.fragments
/*
*08.03.2022
* Sami Sihvonen
* 2110603
 */
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import finnish.parliament.MyApp
import finnish.parliament.R
import finnish.parliament.data.adapters.PartyListAdapter
import finnish.parliament.data.viewmodels.PartyListViewModel
import finnish.parliament.databinding.FragmentPartyListBinding

class PartyListFragment : Fragment() {

    private lateinit var viewModel: PartyListViewModel
    private lateinit var binding: FragmentPartyListBinding
    private lateinit var partyAdapter: PartyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        viewModel = ViewModelProvider(this).get(PartyListViewModel::class.java)

        partyAdapter = PartyListAdapter(PartyListAdapter.OnClickListener {
            viewModel.partyDetails(it)
        })

        binding.partyListView.adapter = partyAdapter
        binding.partyListView.layoutManager = LinearLayoutManager(MyApp.appContext)

        viewModel.parties.observe(viewLifecycleOwner) {
            partyAdapter.submitList(it)
        }
        viewModel.selectedParty.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController().navigate(
                    PartyListFragmentDirections.actionPartyListFragmentToMemberListFragment(it))
                viewModel.navigationDone()
            }
        }
        return binding.root
    }
}