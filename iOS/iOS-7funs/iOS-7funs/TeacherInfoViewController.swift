//
//  TeacherInfoViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/21/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class TeacherInfoViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {

    @IBOutlet weak var teacherCollectionView: UICollectionView!

    var teacherDatas = [String]()
    var cellHeight: CGFloat!

    override func viewWillAppear(animated: Bool) {
        changeNaviationBarTitle("料理老師")
    }

    func changeNaviationBarTitle(title: String) {
        self.navigationController?.navigationBar.topItem?.title = title
    }

    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)

        // after autolayout
        cellHeight = teacherCollectionView.frame.height

        // query database finished
        teacherDatas = [ "1", "2", "3", "4", "5" ]

        // refresh UI
        teacherCollectionView.reloadData()
    }

    // MARK: UICollectionViewDataSource Methods
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return teacherDatas.count
    }

    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("TeacherInfoCell", forIndexPath: indexPath) as! TeacherInfoCell

        //        if (tableData[indexPath.row] == "-1") {
        //            cell.label.text = ""
        //            cell.imageView.image = nil
        //
        //        } else {
        //            cell.label.text = tableData[indexPath.row]
        //            cell.imageView.image = UIImage(named: "gamtlemen")
        //        }

        return cell
    }

    // TODO: UICollectionViewDelegate Methods



    // MARK: UICollectionViewDelegateFlowLayout Methods
    func collectionView(collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAtIndexPath indexPath: NSIndexPath) -> CGSize {

        let barHeight = self.navigationController?.navigationBar.frame.size.height
        let statusHeight = UIApplication.sharedApplication().statusBarFrame.size.height
        let tabHeight = self.tabBarController?.tabBar.frame.height

        var height: CGFloat = collectionView.frame.height * 0.5
        if cellHeight == nil {
            println("dynamic change cell size first time")

        } else {
            println("dynamic change cell size after reloadData")
            height = self.cellHeight * 0.5
        }

        let width = collectionView.frame.size.width * 0.5
        return CGSizeMake(width, height)
    }

}
