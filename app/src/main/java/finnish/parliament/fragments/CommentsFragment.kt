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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import finnish.parliament.MyApp
import finnish.parliament.R
import finnish.parliament.data.adapters.CommentsListAdapter
import finnish.parliament.data.viewmodels.CommentsViewModel
import finnish.parliament.databinding.FragmentCommentsListBinding

class CommentsFragment : Fragment() {

    private lateinit var binding: FragmentCommentsListBinding
    private lateinit var viewModel: CommentsViewModel
    private lateinit var commentsAdapter: CommentsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
                             ): View {
        val application = requireNotNull(activity).application
        val dataSource  = CommentsFragmentArgs.fromBundle(requireArguments()).addedComments
        val viewModelFactory = CommentsViewModel.CommentsViewModelFactory(dataSource, application)


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments_list, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CommentsViewModel::class.java)
        commentsAdapter = CommentsListAdapter()

        binding.commentList.adapter = commentsAdapter
        binding.commentList.layoutManager = LinearLayoutManager(MyApp.appContext)

        viewModel.comment.observe(viewLifecycleOwner) {
            commentsAdapter.submitList(it)
        }

        return binding.root
    }}