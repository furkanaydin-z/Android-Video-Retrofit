package com.fako.videoexam.uı.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fako.videoexam.R
import com.fako.videoexam.databinding.FragmentMainBinding
import com.fako.videoexam.entity.Movies
import com.fako.videoexam.uı.adapter.MovieAdapter
import com.fako.videoexam.uı.viewmodel.MainViewModel


class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)


        viewModel.movieList.observe(viewLifecycleOwner){
            binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            var movieAdapter = MovieAdapter(requireContext(),it)
            binding.recyclerView2.adapter = movieAdapter

        }
        viewModel.movieList.observe(viewLifecycleOwner){
            binding.recyclerViewExtra.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,true)
            var movieAdapter = MovieAdapter(requireContext(),it)
            binding.recyclerViewExtra.adapter = movieAdapter

        }

            binding.videoView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_videoFragment)
            }

        val videoUri: Uri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.androidd)
        binding.videoView.setVideoURI(videoUri)

        binding.videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.setOnVideoSizeChangedListener { _, _, _ ->
            }
        }
        binding.videoView.start()
        binding.videoView.setOnCompletionListener {
            binding.videoView.setVideoURI(videoUri)
            binding.videoView.start()
        }
        binding.ara.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.ara(query)
                return true
            }
        })
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item->
            when (item.itemId) {
                R.id.menuItem1 -> {
                    Navigation.findNavController(binding.bottomNavigationView).navigate(R.id.action_mainFragment_to_paymentFragment)
                    true
                }
                R.id.menuItem2 -> {
                    requireActivity().finishAffinity()
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val viewTemp : MainViewModel by viewModels()
        viewModel = viewTemp
    }

}