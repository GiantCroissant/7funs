//
//  TeacherInfoViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/21/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class TeacherInfoViewController: UIViewController {

    override func viewWillAppear(animated: Bool) {
        changeNaviationBarTitle()
    }

    func changeNaviationBarTitle() {
        self.navigationController?.navigationBar.topItem?.title = "料理老師"
    }

}
