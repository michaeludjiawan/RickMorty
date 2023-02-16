package com.test.rickmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.test.rickmorty.R
import com.test.rickmorty.databinding.FragmentHomeBinding
import com.test.rickmorty.ui.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            if (!characters.isNullOrEmpty()) {
                characterAdapter.addItems(characters)
            } else {
                Toast.makeText(context, getString(R.string.empty_list_message), Toast.LENGTH_LONG).show()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbHomeLoading.isVisible = isLoading
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        characterAdapter = CharacterAdapter(Glide.with(this))

        binding.rvCharacterList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}