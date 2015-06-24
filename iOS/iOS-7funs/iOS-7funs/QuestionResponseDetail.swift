//
//  QuestionResponseDetail.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import Foundation

class QuestionResponseDetail {
    var content: String
    var ownerName: String
    var ownerIconUrl: String
    
    init(content: String, ownerName: String, ownerIconUrl: String) {
        self.content = content
        self.ownerName = ownerName
        self.ownerIconUrl = ownerIconUrl
    }
}