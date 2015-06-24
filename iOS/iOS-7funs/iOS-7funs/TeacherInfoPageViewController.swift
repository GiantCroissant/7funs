//
//  TeacherInfoPageViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class TeacherInfoPageViewController: UIPageViewController, UIPageViewControllerDataSource {

    var pageTitles: NSArray!

    override func viewDidLoad() {
        super.viewDidLoad()

        self.dataSource = self
        self.pageTitles = ["Page 1", "Page 2", "Page 3"]

        var startVC = self.contentViewControllerAtIndex(0)
        self.setViewControllers([startVC], direction: .Forward, animated: true, completion: nil)
    }

    func contentViewControllerAtIndex(index: Int) -> TeacherInfoContentViewController {
        if ((self.pageTitles.count == 0) || (index >= self.pageTitles.count)) {
            return TeacherInfoContentViewController()
        }

        var vc = self.storyboard?.instantiateViewControllerWithIdentifier("TeacherInfoContentViewController") as! TeacherInfoContentViewController
        vc.pageIndex = index
        return vc
    }

    // MARK: - Page View Controller Data Source
    func pageViewController(pageViewController: UIPageViewController, viewControllerBeforeViewController viewController: UIViewController) -> UIViewController? {

        var vc = viewController as! TeacherInfoContentViewController
        var index = vc.pageIndex as Int
        if (index == 0 || index == NSNotFound) {
            return nil
        }

        index--
        return self.contentViewControllerAtIndex(index)
    }

    func pageViewController(pageViewController: UIPageViewController, viewControllerAfterViewController viewController: UIViewController) -> UIViewController? {

        var vc = viewController as! TeacherInfoContentViewController
        var index = vc.pageIndex as Int
        if (index == NSNotFound) {
            return nil
        }

        index++

        if (index == self.pageTitles.count) {
            return nil
        }

        return self.contentViewControllerAtIndex(index)
    }

    func presentationCountForPageViewController(pageViewController: UIPageViewController) -> Int {
        return self.pageTitles.count
    }

    func presentationIndexForPageViewController(pageViewController: UIPageViewController) -> Int {
        return 0
    }

}
