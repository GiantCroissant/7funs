//
//  VideosDishTableViewCell.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/22/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class VideosDishTableViewCell: UITableViewCell {

    @IBOutlet weak var dishImage: UIImageView!
    
    @IBOutlet weak var dishName: UILabel!
    
    @IBOutlet weak var duration: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
