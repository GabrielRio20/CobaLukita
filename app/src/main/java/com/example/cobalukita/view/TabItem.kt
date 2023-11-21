package com.example.cobalukita.view

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable ()->Unit

sealed class TabItem(val title: String, val layout: ComposableFun) {
    object BarangSeni: TabItem("Barang Seni", layout = { BarangSeni() })
    object JasaSeni: TabItem("Jasa Seni", layout = { JasaSeni() })
}