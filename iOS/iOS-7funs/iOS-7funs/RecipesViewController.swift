//
//  RecipesViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/11/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class RecipesViewController: UIViewController {

    @IBOutlet weak var playerView: YTPlayerView!
    @IBOutlet weak var dishTable: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        //playerView.
        var myDictOfDict = ["playsinline" : 1]
        //let config
        playerView.loadWithVideoId("q3bUJJWVB5c", playerVars: myDictOfDict)
    }

    override func viewWillAppear(animated: Bool) {
        self.navigationController?.navigationBarHidden = false
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
