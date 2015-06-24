//
//  QuestionResponse.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import RealmSwift

class QuestionResponse: Object {
    dynamic var id = ""
    dynamic var content = ""
    dynamic var ownerName = ""
    dynamic var ownerIconUrl = ""
    dynamic var questionId = ""
    
    override static func primaryKey() -> String? {
        return "id"
    }
}