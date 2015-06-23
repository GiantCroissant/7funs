//
//  RecipesVideoViewController.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/21/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class RecipesVideoViewController: UIViewController {
    
    var dishDetail: DishDetail!
    
    @IBOutlet weak var playerView: YTPlayerView!

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        // Hard code the youtube id here for testing, later should be replaced with
        // ids passing along from table view
        
        //println(dishDetail.video1)
        //println(dishDetail.video2)
        //println(dishDetail.video3)
        //println(dishDetail.video4)
        
        let setting = ["playsinline" : 1]
        playerView.loadWithVideoId(dishDetail.video1, playerVars: setting)
    }

    @IBAction func changeToView1(sender: AnyObject) {
        let playedTime = playerView.currentTime()
        println(playedTime)
        playerView.cueVideoById(dishDetail.video1, startSeconds: playedTime, suggestedQuality: YTPlaybackQuality.Auto)
        playerView.playVideo()
    }
    
    @IBAction func changeToView2(sender: AnyObject) {
        let playedTime = playerView.currentTime()
        println(playedTime)
        playerView.cueVideoById(dishDetail.video2, startSeconds: playedTime, suggestedQuality: YTPlaybackQuality.Auto)
        playerView.playVideo()
    }
    
    @IBAction func changeToView3(sender: AnyObject) {
        let playedTime = playerView.currentTime()
        println(playedTime)
        playerView.cueVideoById(dishDetail.video3, startSeconds: playedTime, suggestedQuality: YTPlaybackQuality.Auto)
        playerView.playVideo()
    }
    
    @IBAction func changeToView4(sender: AnyObject) {
        let playedTime = playerView.currentTime()
        println(playedTime)
        playerView.cueVideoById(dishDetail.video4, startSeconds: playedTime, suggestedQuality: YTPlaybackQuality.Auto)
        playerView.playVideo()
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
