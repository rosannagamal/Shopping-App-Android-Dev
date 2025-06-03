package com.example.cs3130_assignment3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.util.Locale

class MainActivity : AppCompatActivity(), SelectedProductFragment.OnAddToCartListener {

    private lateinit var cartFragment: CartFragment
    private lateinit var changeLanguageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        cartFragment = CartFragment()
        val middleFragment = ProductsListFragment()

        fragmentTransaction.add(R.id.topFrame, cartFragment)
        fragmentTransaction.add(R.id.middleFrame, middleFragment)
        fragmentTransaction.commit()

        changeLanguageButton = findViewById(R.id.changeLanguageButton)

        changeLanguageButton.setOnClickListener {
            val locale = Locale("ja")
            Locale.setDefault(locale)

            val config = resources.configuration
            config.setLocale(locale)
            config.setLayoutDirection(locale)

            resources.updateConfiguration(config, resources.displayMetrics)

            val intent = intent
            finish()
            startActivity(intent)
        }
        createNotificationChannel()
        requestNotificationPermission()
    }
    override fun onAddToCart(description: String?, price: String?, imageRes: Int?, quantity: Int) {
        if (description != null && price != null && imageRes != null) {
            cartFragment.addToCart(description, price, imageRes, quantity)
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val requestPermissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {}
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "cart_channel"
            val channelName = "Shopping Cart Notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Notifications for shopping cart updates"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}