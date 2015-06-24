//
//  QuestionDetail.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import Foundation

class QuestionDetail {
    var title: String
    var content: String
    var ownerName: String
    var ownerIconUrl: String
    var responses: [QuestionResponseDetail]
    
    init(title: String, content: String, ownerName: String, ownerIconUrl: String, responses: [QuestionResponseDetail]) {
        self.title = title
        self.content = content
        self.ownerName = ownerName
        self.ownerIconUrl = ownerIconUrl
        self.responses = responses
    }
}