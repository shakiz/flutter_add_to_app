package com.example.myaddtoappapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myaddtoappapplication.databinding.ActivityMainBinding
import com.example.myaddtoappapplication.model.UserInfo
import com.google.gson.Gson
import io.flutter.FlutterInjector
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var flutterViewEngine: FlutterViewEngine
    private lateinit var flutterEngine: FlutterEngine
    private lateinit var flutterView : FlutterView

    private val methodChannelName = "get_data_channel"
    private lateinit var navController: NavController
    private val ENGINE_ID = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        initFlutterEngine()
        binding.fab.setOnClickListener { view ->
            launchFlutterModuleWithCachedEngine()
        }

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, methodChannelName).setMethodCallHandler { call, result ->
            if (call.method == "getDataFromNative") {
                // Perform platform-specific operations and obtain the result
                val data = getDataFromNative()
                // Send the result back to Flutter
                result.success(data)
            } else if (call.method == "getUserInfoFromNative"){
                val data: UserInfo = getUserInfoFromNative()
                val gson = Gson()
                val demoJson = gson.toJson(data)
                result.success(demoJson)
            } else if (call.method == "exitFlow"){
                navigateToSecondFragment()
                result.success("exit")
            } else {
                result.notImplemented()
            }
        }

        flutterView = FlutterView(this)
        binding.contentMain.llTest.addView(flutterView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        flutterViewEngine = FlutterViewEngine(flutterEngine)
        flutterViewEngine.attachToActivity(this)
        flutterViewEngine.attachFlutterView(flutterView)
    }

    private fun initFlutterEngine(){
        flutterEngine = FlutterEngine(applicationContext)
        setWithInitialRoute()

        // Forcefully start showCell entryPoint
//        flutterEngine.dartExecutor.executeDartEntrypoint(
//            DartExecutor.DartEntrypoint(
//                FlutterInjector.instance().flutterLoader().findAppBundlePath(),
//                "showCell")
//        )

        // Start executing Dart code to pre-warm the FlutterEngine
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)
    }

    private fun launchFlutterModuleWithCachedEngine(){
        startActivity(
            FlutterActivity
                .withCachedEngine(ENGINE_ID)
                .build(this)
        )
    }

    private fun launchFlutterModuleWithInitialRouteAndNewEngine(){
        startActivity(
            FlutterActivity
                .withNewEngine()
                .initialRoute("/screen_d")
                .build(this)
        )
    }

    private fun setWithInitialRoute(){
        flutterEngine.navigationChannel.setInitialRoute("/")
    }

    private fun getDataFromNative(): String {
        // Perform platform-specific operations to fetch the data
        return "Data from Native"
    }

    private fun getUserInfoFromNative(): UserInfo {
        // Perform platform-specific operations to fetch the data
        return UserInfo("Test User", 27)
    }

    private fun navigateToSecondFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        flutterViewEngine.detachActivity()
    }

    override fun onUserLeaveHint() {
        flutterViewEngine.onUserLeaveHint()
        super.onUserLeaveHint()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        flutterViewEngine.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        flutterViewEngine.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}