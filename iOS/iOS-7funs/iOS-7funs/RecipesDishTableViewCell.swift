//
//  RecipesDishTableViewCell.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/18/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class RecipesDishTableViewCell: UITableViewCell {

    @IBOutlet weak var viewedCount: UILabel!
    //var viewedCount: Int = 0
    //var collectedCount: Int = 0
    //var imagePath: String = ""
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
