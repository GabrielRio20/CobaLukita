package com.example.cobalukita.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cobalukita.R
import com.example.cobalukita.ui.theme.CobaLukitaTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CobaLukitaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainContent(){
    val list = listOf(TabItem.BarangSeni, TabItem.JasaSeni)
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        Profile()
        Tabs(list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState)
    }
}

@Preview(
    showBackground = true
)
@Composable
fun Profile(){
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.claude_monet),
            contentDescription = "Logo Jetpack Compose",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(100.dp))
                .scale(1.2f, 1.2f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = stringResource(R.string.nama),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Welcome to Dicoding!",
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState){

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = {tabPosition ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPosition[pagerState.currentPage]),
                height = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }){
        tabs.forEachIndexed{index, tabItem ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                    pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                   Text(tabItem.title)
                },
                icon = { /*TODO*/ })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs:List<TabItem>, pagerState: PagerState){
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].layout()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BarangSeni(){
    val navHostController = rememberNavController()



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        NavHost(navController = navHostController,
//            startDestination = "painting_data"
//        ){
//            composable("painting_data"){
//                PaintingGrid(navController = navHostController)
//            }
//        }
    }
}

@Composable
fun JasaSeni(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text(text = "Jasa Seni", style = TextStyle(color = Color.Black, fontSize = 20.sp))
    }
}