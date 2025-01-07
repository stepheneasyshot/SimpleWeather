import UIKit
import SystemConfiguration


class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // This method is called when the app is launched.
        // Perform any necessary setup here.
        print("=========>application tetsttstttst<===========")
//        isNetworkAble()
        return true
    }
    
//    func isNetworkAble(){
//        let reachability = Reachability.init(hostname: "baidu.com")
//        if reachability?.connection == .wifi {
//            print("Connected to Wi-Fi")
//        } else if reachability?.connection == .cellular {
//            print("Connected to cellular")
//        } else {
//            print("No network connection")
//        }
//    }

    // Other methods you might use:
    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move to the background.
        // Use this method to pause ongoing tasks, disable timers, and throttle network access.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state on next launch.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was terminated by the system, and was restarted by the user, this method will not be called.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }
}
