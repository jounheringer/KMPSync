package com.reringuy.sync.utils

import platform.Foundation.NSProcessInfo

actual object PlatformConfig {
    actual val isDebug: Boolean
        get() = NSProcessInfo.processInfo.environment["DEBUG"] == "true"

}