package com.leo.labtinkoff.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.leo.labtinkoff.R
import com.leo.labtinkoff.databinding.FragmentRandomDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomDataFragment : Fragment() {

    private val binding: FragmentRandomDataBinding by lazy {
        FragmentRandomDataBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<RandomDataViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.gifsData().observe(viewLifecycleOwner){
            gifsLoad(binding.gifsImage, it.gifURL)
        }

        binding.nextGifs.setOnClickListener {
            viewModel.gifsData().observe(viewLifecycleOwner) {

                if (it == null) {
                    binding.progressBar.progress
                } else {
                    gifsLoad(binding.gifsImage, it.gifURL)
                }
            }
        }

        return binding.root
    }

    private fun gifsLoad(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .asGif()
                .load(imgUri)
                .transform(FitCenter(), RoundedCorners(10))
                .error(R.drawable.error_icon)
                .into(imgView)
        }
    }

}