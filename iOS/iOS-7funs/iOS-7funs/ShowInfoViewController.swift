//
//  ShowInfoViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/11/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class ShowInfoViewController: UIViewController {

    @IBOutlet weak var barItem: UITabBarItem!

    override func viewWillAppear(animated: Bool) {
        changeNaviationBarTitle("節目資訊")


    }

    func changeNaviationBarTitle(title: String) {
        self.navigationController?.navigationBar.topItem?.title = title
    }

}
