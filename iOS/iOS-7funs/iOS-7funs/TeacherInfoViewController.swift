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
        super.viewWillAppear(animated)
        changeNaviationBarTitle("料理老師")
    }

    func changeNaviationBarTitle(title: String) {
        self.navigationController?.navigationBar.topItem?.title = title
    }

    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)

        cellHeight = teacherCollectionView.frame.height
        teacherDatas = [ "1", "2", "3", "4", "5" ]
        teacherCollectionView.reloadData()
    }

    // MARK: UICollectionViewDataSource Methods
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return teacherDatas.count
    }

    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("TeacherInfoCell", forIndexPath: indexPath) as! TeacherInfoCell

        // TODO: Dynamic Load Data and Upate Cell UI

        return cell
    }

    // MARK: UICollectionViewDelegateFlowLayout Methods
    func collectionView(collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAtIndexPath indexPath: NSIndexPath) -> CGSize {

        let height = self.cellHeight * 0.5
        let width = collectionView.frame.size.width * 0.5
        return CGSizeMake(width, height)
    }

}
