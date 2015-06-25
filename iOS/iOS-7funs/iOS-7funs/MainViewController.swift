//
//  MainViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/7/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class MainViewController: UIViewController {

    @IBAction func pushShowInfoStoryboards(sender: UIButton) {
        pushStoryboard("ShowInfo")
    }

    @IBAction func pushVideosStoryboards(sender: UIButton) {
        pushStoryboard("Videos")
    }

    @IBAction func pushRecipesStoryboards(sender: UIButton) {
        pushStoryboard("Recipes")
    }

    @IBAction func pushCollectionsStoryboards(sender: UIButton) {
        pushStoryboard("Collections")
    }

    @IBAction func pushQandAStoryboards(sender: UIButton) {
        pushStoryboard("QandA")
    }

    @IBAction func pushExternalLinksStoryboards(sender: UIButton) {
        pushStoryboard("ExternalLinks")
    }

    @IBAction func pushScratchcardStoryboards(sender: UIButton) {
        pushStoryboard("Scratchcard")
    }

    func pushStoryboard(sbName: String) {
        var storyboard = UIStoryboard(name: sbName, bundle: nil)
        var controller = storyboard.instantiateInitialViewController() as! UIViewController
        self.navigationController?.pushViewController(controller, animated: true)
    }
    
    override func viewWillAppear(animated: Bool) {
        hideNavigationBar()
    }

    func hideNavigationBar() {
        self.navigationController?.navigationBarHidden = true
    }

}
