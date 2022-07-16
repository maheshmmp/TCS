package com.example.mynewsapplication.utils

import java.text.SimpleDateFormat


fun String?.getDayFromDate(): String {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    return SimpleDateFormat("dd-MM-yyyy").format(date)
}
