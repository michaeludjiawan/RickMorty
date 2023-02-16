package com.test.rickmorty.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.test.rickmorty.R
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.databinding.FragmentCharacterDetailBinding
import com.test.rickmorty.ui.common.BaseFragment
import org.koin.android.ext.android.inject

class CharacterDetailFragment : BaseFragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initToolbar(getString(R.string.toolbar_title_detail), true)

        initData()
        initObservers()
    }

    private fun initData() {
        val character = arguments?.getParcelable<Character>(INTENT_CHARACTER)
        if (character != null) {
            viewModel.setData(character)
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObservers() {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            populateUI(character)
        }

        viewModel.episodes.observe(viewLifecycleOwner) { episodes ->
            binding.llDetailEpisodeContainer.removeAllViews()

            if (!episodes.isNullOrEmpty()) {
                episodes.forEach { ep ->
                    val epView = EpisodeSnapshotView(requireContext())
                    epView.init(ep)
                    binding.llDetailEpisodeContainer.addView(epView)
                }
            } else {
                val msgView = TextView(requireContext())
                msgView.text = getString(R.string.empty_episode_msg)
                binding.llDetailEpisodeContainer.addView(msgView)
            }
        }
    }

    private fun populateUI(character: Character) {
        Glide.with(this).load(character.avatarUrl)
            .placeholder(R.color.black)
            .into(binding.ivDetailImage)

        binding.tvDetailName.text = character.name
        binding.tvDetailSpecies.text = character.species
        binding.tvDetailStatus.text = character.status
        binding.tvDetailGender.text = character.gender
        binding.tvDetailOrigin.text = character.originData.name
        binding.tvDetailLocation.text = character.locationData.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val INTENT_CHARACTER = "intent_character"

        fun newInstance(character: Character): CharacterDetailFragment {
            return CharacterDetailFragment().also { fragment ->
                fragment.arguments = Bundle().also { bundle ->
                    bundle.putParcelable(INTENT_CHARACTER, character)
                }
            }
        }
    }
}