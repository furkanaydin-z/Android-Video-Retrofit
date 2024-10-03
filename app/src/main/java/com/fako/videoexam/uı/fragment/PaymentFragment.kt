package com.fako.videoexam.uı.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.fako.videoexam.R
import com.fako.videoexam.databinding.FragmentPaymentBinding
import com.fako.videoexam.databinding.FragmentVideoBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentFragment : Fragment() {
    private lateinit var binding:FragmentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
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

        //now i didnt use databinding


        binding.buttonPayment.setOnClickListener {
            val n = binding.inputTextName.text.toString()
            val c = binding.inputTextCard.text.toString()
            val d =binding.inputTextDate.text.toString()
            val cvv = binding.inputTextCvv.text.toString()

            CoroutineScope(Dispatchers.Main).launch {
                binding.progressBar.visibility = View.VISIBLE
                delay(3000)
                binding.progressBar.visibility = View.GONE
            Snackbar.make(binding.root,"Ödeme işlemi yapıldı",Snackbar.LENGTH_SHORT).show()
                Log.e("payment", "$n-$c,$d,$cvv")
            }
        }
        return binding.root
    }
}