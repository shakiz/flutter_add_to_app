package com.example.myaddtoappapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myaddtoappapplication.databinding.FragmentSecondBinding
import io.flutter.FlutterInjector
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private lateinit var flutterViewEngine: FlutterViewEngine
    private lateinit var flutterEngine: FlutterEngine
    private lateinit var flutterView : FlutterView
    private val ENGINE_ID = "2"

    private val methodChannelName = "get_data_channel"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFlutterEngine()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun initFlutterEngine(){
        flutterEngine = FlutterEngine(requireContext())
        setWithInitialRoute()

        // Forcefully start showCell entryPoint
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterInjector.instance().flutterLoader().findAppBundlePath(),
                "showCell")
        )

        FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, methodChannelName).setMethodCallHandler { call, result ->

        }

        flutterView = FlutterView(requireContext())
        binding.llTest.addView(flutterView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        flutterViewEngine = FlutterViewEngine(flutterEngine)
        flutterViewEngine.attachToActivity(requireActivity())
        flutterViewEngine.attachFlutterView(flutterView)
    }

    private fun setWithInitialRoute(){
        flutterEngine.navigationChannel.setInitialRoute("/")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        flutterViewEngine.detachActivity()
        flutterEngine.destroy()
        flutterView.detachFromFlutterEngine()
        _binding = null
    }
}