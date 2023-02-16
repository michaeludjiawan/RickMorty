package com.test.rickmorty.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.test.rickmorty.data.model.Episode
import com.test.rickmorty.databinding.ViewEpisodeSnapshotBinding

class EpisodeSnapshotView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private val binding: ViewEpisodeSnapshotBinding

    init {
        binding = ViewEpisodeSnapshotBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun init(ep: Episode) {
        binding.tvEpName.text = ep.name
        binding.tvEpNumber.text = ep.episode
        binding.tvEpAirdate.text = ep.airDate
    }
}