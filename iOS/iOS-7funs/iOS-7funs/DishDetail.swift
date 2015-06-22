//
//  DishDetail.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/22/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class DishDetail {
    var cook: String
    var dishName: String
    var viewedCount: Int
    var collectedCount: Int
    var imagePath: String
    var video1: String
    var video2: String
    var video3: String
    var video4: String
    
    init(cook: String, dishName: String, viewedCount: Int, collectedCount: Int, imagePath: String, video1: String, video2: String, video3: String, video4: String) {
        self.cook = cook
        self.dishName = dishName
        self.viewedCount = viewedCount
        self.collectedCount = collectedCount
        self.imagePath = imagePath
        self.video1 = video1
        self.video2 = video2
        self.video3 = video3
        self.video4 = video4
    }
}
