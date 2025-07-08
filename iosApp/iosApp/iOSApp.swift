import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        Koin_iosKt.startKoin()
        BackgroundSyncManager.shared.registerBackgroundTask()
        BackgroundSyncManager.shared.scheduleAppRefresh()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
