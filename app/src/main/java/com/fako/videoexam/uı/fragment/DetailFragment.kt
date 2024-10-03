package com.fako.videoexam.uı.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fako.videoexam.R
import com.fako.videoexam.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {
    private lateinit var binding:FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)

        val backPress = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPress)
        binding.imageBack.setOnClickListener {
            Snackbar.make(binding.root, "Do you want to go back?", Snackbar.LENGTH_SHORT)
                .setAction("Yes") {
                    findNavController().popBackStack()
                }.setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setActionTextColor(Color.BLACK)
                .show()
        }

        val bundle: DetailFragmentArgs by navArgs()
        val transfer = bundle.movie
        binding.textNameDetail.text = transfer.ad
        binding.textPriceDetail.text = transfer.fiyat.toString()

        val url = "http://kasimadalan.pe.hu/filmler_yeni/resimler/${transfer.resim}"
        Glide.with(requireContext()).load(url).override(400,650).into(binding.imageDetail)

        binding.buttonDetail.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_videoFragment)
        }

        return binding.root
    }
}