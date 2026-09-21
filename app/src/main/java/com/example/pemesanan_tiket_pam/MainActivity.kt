package com.example.pemesanan_tiket_pam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicketScreen()
        }
    }
}

@Composable
fun TicketScreen() {

    // Harga satu tiket
    val hargaTiket = 25000

    // Jumlah tiket
    var jumlahTiket by remember {
        mutableStateOf(1)
    }

    // Total harga
    val total = hargaTiket * jumlahTiket

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
    ) {

        // =========================
        // HEADER
        // =========================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 22.dp,
                        bottomEnd = 22.dp
                    )
                )
                .background(Color(0xFF1688F5)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.ConfirmationNumber,
                contentDescription = "Tiket",
                tint = Color.White,
                modifier = Modifier.size(42.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pemesanan Tiket",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Pesan tiket dengan mudah!",
                color = Color.White,
                fontSize = 13.sp
            )
        }

        // =========================
        // CONTENT
        // =========================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // =========================
            // HARGA TIKET
            // =========================

            TicketCard {

                Text(
                    text = "Harga Tiket",
                    color = Color(0xFF26364D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatRupiah(hargaTiket),
                    color = Color(0xFF1688F5),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "per tiket",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // =========================
            // JUMLAH TIKET
            // =========================

            TicketCard {

                Text(
                    text = "Jumlah Tiket",
                    color = Color(0xFF26364D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Tombol -
                    RoundButton(
                        icon = Icons.Default.Remove,
                        onClick = {
                            if (jumlahTiket > 1) {
                                jumlahTiket--
                            }
                        }
                    )

                    // Jumlah tiket
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0F3F7)),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = jumlahTiket.toString(),
                            color = Color(0xFF26364D),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Tombol +
                    RoundButton(
                        icon = Icons.Default.Add,
                        onClick = {
                            jumlahTiket++
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // =========================
            // TOTAL
            // =========================

            TicketCard {

                Text(
                    text = "Total",
                    color = Color(0xFF26364D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = formatRupiah(total),
                    color = Color(0xFF07883F),
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // =========================
            // RESET
            // =========================

            Button(
                onClick = {
                    jumlahTiket = 1
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEF4444)
                ),
                shape = RoundedCornerShape(9.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.size(6.dp))

                Text(
                    text = "RESET",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


// ==================================================
// CARD
// ==================================================

@Composable
fun TicketCard(
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        content()
    }
}


// ==================================================
// TOMBOL + / -
// ==================================================

@Composable
fun RoundButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1688F5)
        )
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(27.dp)
        )
    }
}


// ==================================================
// FORMAT RUPIAH
// ==================================================

fun formatRupiah(angka: Int): String {

    return "Rp" + String.format(
        "%,d",
        angka
    ).replace(",", ".")
}