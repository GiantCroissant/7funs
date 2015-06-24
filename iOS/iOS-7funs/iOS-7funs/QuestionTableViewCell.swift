//
//  QuestionTableViewCell.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class QuestionTableViewCell: UITableViewCell {

    @IBOutlet weak var replyerImage: UIImageView!
    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var timeStamp: UILabel!
    @IBOutlet weak var replyCount: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
