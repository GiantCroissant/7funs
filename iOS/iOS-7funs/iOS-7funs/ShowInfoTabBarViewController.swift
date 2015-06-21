//
//  ShowInfoTabBarViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/21/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class ShowInfoTabBarViewController: UITabBarController {

    override func viewWillAppear(animated: Bool) {
        hideNavigarionBar()
    }

    func hideNavigarionBar() {
        self.navigationController?.navigationBarHidden = false
    }
}
