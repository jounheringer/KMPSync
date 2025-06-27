package com.reringuy.sync

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform