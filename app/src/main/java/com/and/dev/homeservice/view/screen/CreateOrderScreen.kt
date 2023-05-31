package com.and.dev.homeservice.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.and.dev.homeservice.CustomButton
import com.and.dev.homeservice.R
import com.and.dev.homeservice.ui.theme.HomeServiceTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


class CreateOrderScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val itemId = intent.getIntExtra("itemId", -1)
            val itemName = intent.getStringExtra("itemName")
            val navController = rememberNavController()



            HomeServiceTheme {


                NavHost(navController, startDestination = "createOrderProblemDetailsScreen") {
                    composable("createOrderProblemDetailsScreen") {
                        if (itemName != null) {
                            CreateOrderProblemDetailsScreen(
                                navController = navController,
                                itemId = itemId,
                                itemName = itemName
                            )
                        } else {
                            CreateOrderProblemDetailsScreen(
                                navController = navController, itemId = itemId, itemName = "Service"
                            )
                        }
                    }
                    composable("createOrderDone") {
                        CreateOrderDone()
                    }
                }


            }
        }
    }

    @Composable
    fun CreateOrderProblemDetailsScreen(
        navController: NavController,
        itemId: Int,
        itemName: String
    ) {


        val problemState = remember { mutableStateOf(TextFieldValue()) }
        val locationState = remember { mutableStateOf(TextFieldValue()) }

        val singapore = LatLng(1.35, 103.87)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }




        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                            startX = 0f,
                            endX = Float.POSITIVE_INFINITY
                        ),
                        shape = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50)
                    ),
            ) {

                IconButton(onClick = { navigateToHome(context = this@CreateOrderScreen) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(start = 6.dp, bottom = 14.dp, top = 20.dp)
                            .align(alignment = Alignment.CenterStart)
                    )
                }

                Text(
                    text = itemName, style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ), modifier = Modifier.align(alignment = Alignment.Center)
                )
            }



            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                    .border(
                        BorderStroke(1.dp, Color(0xFF868686)), shape = RoundedCornerShape(6.dp)
                    ),
            ) {

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.image_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                            .align(alignment = Alignment.CenterStart)
                    )

                    Text(
                        text = "Image Problem", style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Cursive,
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        ), modifier = Modifier.align(alignment = Alignment.Center)

                    )
                }

            }

            Text(
                text = "image must be no more than 2 MB Max 5 Image", style = TextStyle(
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Start,
                    color = Color.Gray
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                TextField(
                    value = problemState.value,
                    onValueChange = { problemState.value = it },
                    label = {
                        Text(
                            text = "More Details About Problem …",
                            modifier = Modifier.padding(2.dp),
                            fontFamily = FontFamily.Cursive,

                            color = Color.Gray,
                            fontSize = 14.sp

                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.White)
                        .border(
                            border = BorderStroke(1.dp, Color(0xff346EDF)),
                            shape = RoundedCornerShape(8.dp),
                        ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Start
                    )
                )


            }



            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = singapore),
                    title = "London",
                    snippet = "Marker in Big Ben"
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                TextField(
                    value = locationState.value,
                    onValueChange = { locationState.value = it },
                    label = {
                        Text(
                            text = "Location Address Details …",
                            modifier = Modifier.padding(2.dp),
                            fontFamily = FontFamily.Cursive,
                            color = Color.Gray,
                            fontSize = 14.sp

                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.White)
                        .border(
                            border = BorderStroke(1.dp, Color(0xff346EDF)),
                            shape = RoundedCornerShape(8.dp),
                        ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Start
                    )
                )


            }

            CustomButton(
                onClick = { navController.navigate("CreateOrderDone") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
                    .height(64.dp),
                gradientColors = listOf(
                    Color(0xff346EDF), Color(0xff6FC8FB)
                )
            ) {
                Text(
                    text = "Create Order",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Cursive,

                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }


        }


    }

    @Composable
    fun CreateOrderDone() {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.create_done_img),
                    contentDescription = "Create done img",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 64.dp),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = "ORDER Done!",
                    color = Color(0xff0E9CF9),
                    fontSize = 23.sp,
                    fontFamily = FontFamily.Cursive,

                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "The ORDER has been sent. A technician will contact you",
                    color = Color(0xFF636363),
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Cursive,

                    textAlign = TextAlign.Center,
                )



                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    CustomButton(
                        onClick = { navigateToHome(this@CreateOrderScreen) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(64.dp)
                            .align(alignment = Alignment.BottomStart)
                            .padding(8.dp),
                        gradientColors = listOf(Color(0xff346EDF), Color(0xff6FC8FB))
                    ) {
                        Text(
                            text = "Go to Home",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Cursive,

                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


            }

        }

    }


    override fun onBackPressed() {
    }
}

private fun navigateToHome(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {

    }
    context.startActivity(intent)
}



