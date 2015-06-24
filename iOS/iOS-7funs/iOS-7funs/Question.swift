//
//  Question.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import RealmSwift

class Question: Object {
    dynamic var id = ""
    dynamic var title = ""
    dynamic var content = ""
    dynamic var ownerName = ""
    dynamic var ownerIconUrl = ""
    dynamic var responseId = ""
    
    override static func primaryKey() -> String? {
        return "id"
    }
}