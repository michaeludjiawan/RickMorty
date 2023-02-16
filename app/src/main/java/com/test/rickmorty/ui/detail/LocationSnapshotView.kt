package com.test.rickmorty.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.test.rickmorty.data.model.Location
import com.test.rickmorty.databinding.ViewLocationSnapshotBinding

class LocationSnapshotView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private val binding: ViewLocationSnapshotBinding

    init {
        binding = ViewLocationSnapshotBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun init(loc: Location) {
        binding.tvLocName.text = loc.name
        binding.tvLocType.text = loc.type
        binding.tvLocDimension.text = loc.dimension
    }
}