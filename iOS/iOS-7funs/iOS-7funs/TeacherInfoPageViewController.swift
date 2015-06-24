//
//  TeacherInfoPageViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class TeacherInfoPageViewController: UIPageViewController, UIPageViewControllerDataSource {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    // MARK: - Page View Controller Data Source 

    func pageViewController(pageViewController: UIPageViewController, viewControllerBeforeViewController viewController: UIViewController) -> UIViewController? {

        return nil
    }

    func pageViewController(pageViewController: UIPageViewController, viewControllerAfterViewController viewController: UIViewController) -> UIViewController? {

        return nil
    }

}
