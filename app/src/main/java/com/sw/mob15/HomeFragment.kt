package com.sw.mob15

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sw.mob15.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import kotlin.time.measureTime


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.finish1.asLiveData().observe(viewLifecycleOwner) {
            Log.d("helloFlow", "Finish1: $it")
        }
        viewModel.finish2.asLiveData().observe(viewLifecycleOwner) {
            Log.d("helloFlow", "Finish2: $it")
        }
        val r = 10.0
        Log.d("coroutine", "area using formula: ${Math.PI*r*r}")
        Log.d("coroutine", "area using integration: ${areaOfACircle(r)}")
        val timeTook = measureTime {
            areaOfACircle(r)
        }


        lifecycleScope.launch {
            viewModel.counter(20).collect() {
                Log.d("Coroutine","Count:  $it")
                binding.tvCount.text = it.toString()
            }
        }

    }
    var finalArea = 0.0


    suspend fun



    fun areaOfACircle(x1: Double, x2: Double, r:Double): Float {
        val area = 0.0
        val dx = 0.5
        var x = x1
        while (x <= r) {

            val smallArea = Math.sqrt(r * r - x * x) * dx
            area = area + smallArea
            x = x + dx
            Log.d("coroutine", "smallArea: ${smallArea}")

        }
        finalArea = finalArea + area

    }

}