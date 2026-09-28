package com.netipmanager

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {

    private val vpnRequestCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VpnScreen {
                val intent = VpnService.prepare(this)

                if (intent != null) {
                    startActivityForResult(intent, vpnRequestCode)
                } else {
                    startService(
                        Intent(this, NetIpVpnService::class.java)
                    )
                }
            }
        }
    }
}

@Composable
fun VpnScreen(onConnect: () -> Unit) {
    Button(onClick = onConnect) {
        Text("Connect VPN")
    }
}
