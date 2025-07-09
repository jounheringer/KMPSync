//
//  BackgroundSyncManager.swift
//  iosApp
//
//  Created by Joao Victor Heringer on 02/07/25.
//

import ComposeApp
import BackgroundTasks


import Foundation

class BackgroundSyncManager {
    static let shared = BackgroundSyncManager()
    private let taskIdentifier = "com.reringuy.sync.refresh"
    
    func registerBackgroundTask() {
        BGTaskScheduler.shared.register(forTaskWithIdentifier: taskIdentifier, using: nil) { task in
            self.handleAppRefresh(task: task as! BGAppRefreshTask)
        }
    }
    
    func scheduleAppRefresh() {
        let request = BGAppRefreshTaskRequest(identifier: taskIdentifier)
        request.earliestBeginDate = Date(timeIntervalSinceNow: 1 * 60) // 5 mins
        do {
            print(request)
            try BGTaskScheduler.shared.submit(request)
        } catch {
            print("NOT MIAU")
            print("Could not schedule app refresh: \(error)")
        }
    }
    
    private func handleAppRefresh(task: BGAppRefreshTask) {
        scheduleAppRefresh()
    
        let operation = BlockOperation {
            ComposeApp.BackgroundSyncSchedulerWrapper().triggerSync()
        }
        
        task.expirationHandler = {
            operation.cancel()
        }
        
        operation.completionBlock = {
            task.setTaskCompleted(success: !operation.isCancelled)
        }
        
        OperationQueue().addOperation(operation)
        
    }
}
