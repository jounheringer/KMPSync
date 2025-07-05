import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        InitKoinKt.doInitKoin()
        BackgroundSyncManager.shared.registerBackgroundTask()
        BackgroundSyncManager.shared.scheduleAppRefresh()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
