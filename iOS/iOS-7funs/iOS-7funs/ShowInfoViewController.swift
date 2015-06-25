//
//  ShowInfoViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/11/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class ShowInfoViewController: UIViewController {

    @IBOutlet weak var fakeImage: UIView!

    override func viewWillAppear(animated: Bool) {
        changeNaviationBarTitle("節目資訊")
        makeFakeImageCircle()
    }

    func changeNaviationBarTitle(title: String) {
        self.navigationController?.navigationBar.topItem?.title = title
    }

    func makeFakeImageCircle() {
        fakeImage.layer.masksToBounds = true
        fakeImage.layer.cornerRadius = fakeImage.frame.width * 0.5
    }

}
