//
//  Dish.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/18/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

import RealmSwift

class Dish: Object {
    dynamic var id = ""
    dynamic var cook = ""
    dynamic var name = ""
    dynamic var viewedCount = 0
    dynamic var collectedCount = 0
    dynamic var image = ""
    //    dynamic var videos = ["", "", "", ""]
    //
    //    override static func indexedProperties() -> [String] {
    //        return ["videos"]
    //    }
    
    override static func primaryKey() -> String? {
        return "id"
    }
}
