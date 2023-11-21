package com.example.cobalukita.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cobalukita.model.PaintingData
import com.example.cobalukita.ui.theme.Purple40
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//class PaintingGridLayout {

    @ExperimentalFoundationApi
    @Composable
    fun PaintingGrid(navController: NavController){
        val context = LocalContext.current
        val dataFileString = getJsonDataFromAsset(context, "PaintingList.json")
        val gson = Gson()
        val gridSampleType = object : TypeToken<List<PaintingData>>(){}.type
        val paintingData: List<PaintingData> = gson.fromJson(dataFileString, gridSampleType)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.White)
                    .padding(6.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Paintings",
                    color = Purple40,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.padding(10.dp)
            ){
                items(paintingData){data->
                    PaintingDataGridItem(data, navController)
                }
            }
        }
    }

    @Composable
    fun PaintingDataGridItem(data: PaintingData, navController: NavController){
        Card(modifier = Modifier
            .clickable {
                val itemVal = Gson().toJson(data)
                navController.navigate("grid_detail/$itemVal")
            }
            .padding(10.dp)
            .fillMaxSize(),
            shape = RoundedCornerShape(5.dp)
        ){
            Column(modifier = Modifier) {
                Image(painter = painterResource(
                    id = when(data.id){
                        1L -> com.example.cobalukita.R.drawable.pic1
                        2L -> com.example.cobalukita.R.drawable.pic2
                        3L -> com.example.cobalukita.R.drawable.pic3
                        4L -> com.example.cobalukita.R.drawable.pic4
                        else -> com.example.cobalukita.R.drawable.pic4
                    }
                ),
                    contentDescription = "Grid Image",
                    modifier = Modifier.fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(5.dp)),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = data.nama,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(1.dp))
                Text(
                    text = data.harga,
                    modifier = Modifier.padding(7.dp, 0.dp, 0.dp, 20.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(1.dp))
                Text(
                    text = data.kota,
                    modifier = Modifier.padding(7.dp, 0.dp, 0.dp, 20.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    fun getJsonDataFromAsset(context: Context, data: String):String{
        return context.assets.open(data).bufferedReader().use { it.readText() }
    }

//}