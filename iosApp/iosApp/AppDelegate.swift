//
//  AppDelegate.swift
//  iosApp
//
//  Created by mohab erabi on 17/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI

class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = UIHostingController(rootView: ContentView())
        window?.makeKeyAndVisible()
        return true
    }
}
