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

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func viewWillAppear(animated: Bool) {
        self.navigationController?.navigationBarHidden = true
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
