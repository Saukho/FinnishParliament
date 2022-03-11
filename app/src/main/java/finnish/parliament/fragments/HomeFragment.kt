package finnish.parliament.fragments
/*
*08.03.2022
* Sami Sihvonen
* 2110603
*HomeFragment
 */
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import finnish.parliament.R
import finnish.parliament.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.updateMembersButton.setOnClickListener {
            val direction =
                HomeFragmentDirections.actionHomeFragmentToPartyListFragment()
            binding.root.findNavController().navigate(direction)
        }
        return binding.root
    }
}

