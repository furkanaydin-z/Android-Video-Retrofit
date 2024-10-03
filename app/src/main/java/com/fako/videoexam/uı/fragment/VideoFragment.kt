package com.fako.videoexam.uı.fragment

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.fako.videoexam.R
import com.fako.videoexam.databinding.FragmentVideoBinding
import com.google.android.material.snackbar.Snackbar

class VideoFragment : Fragment() {
    private lateinit var binding:FragmentVideoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater,container,false)

        val backPress = object : OnBackPressedCallback(true) { // true ile geri tuşunu etkinleştiriyoruz
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPress)

        binding.imageView.setOnClickListener {
            Snackbar.make(binding.root, "Do you want to go back?", Snackbar.LENGTH_SHORT)
                .setAction("Yes") {
                    findNavController().popBackStack()
                }.setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setActionTextColor(Color.BLACK)
                .show()
        }


        val videoUri: Uri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.androidd)

        // VideoView'e URI'yi ayarla
        binding.videoView2.setVideoURI(videoUri)

        // Medya denetleyiciyi ayarla (play, pause, stop gibi işlemler için)
        val mediaController = MediaController(requireContext())
        binding.videoView2.setMediaController(mediaController)
        mediaController.setAnchorView(binding.videoView2)

        // Videoyu başlat
        binding.videoView2.start()


        return binding.root
    }
}