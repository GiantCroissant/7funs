//
//  DishDetail.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/22/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class DishDetail {
    var viewedCount: Int
    var collectedCount: Int
    
    init(viewedCount: Int, collectedCount: Int) {
        self.viewedCount = viewedCount
        self.collectedCount = collectedCount
    }
}
