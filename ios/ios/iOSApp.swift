import SwiftUI
import common

@main
struct iOSApp: App {
    
    init() {
        KoinCupertinoKt.doInitKoinCupertino()
    }
    
	var body: some Scene {
		WindowGroup {
            CountriesView()
		}
	}
}
