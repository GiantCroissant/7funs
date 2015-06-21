//
//  ShowInfoViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/11/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class ShowInfoViewController: UIViewController {

    override func viewWillAppear(animated: Bool) {
        changeNaviationBarTitle()
    }

    func changeNaviationBarTitle() {
        self.navigationController?.navigationBar.topItem?.title = "節目資訊"
    }

}
