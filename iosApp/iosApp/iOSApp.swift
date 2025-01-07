import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    init() {
       NapierProxyKt.iosInitLog()
    }

    var body: some Scene {
        WindowGroup {
           ContentView().edgesIgnoringSafeArea(.all)
        }
    }
}
