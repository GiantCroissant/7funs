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
    
    var player : XCDYouTubeVideoPlayerViewController = XCDYouTubeVideoPlayerViewController(videoIdentifier: "lDcqxj2Xsy4")

    @IBOutlet weak var currentVideo: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        // Hard code the youtube id here for testing, later should be replaced with
        // ids passing along from table view
        //player = XCDYouTubeVideoPlayerViewController(videoIdentifier: "lDcqxj2Xsy4#t=5m22s")
        player.presentInView(currentVideo)
        player.moviePlayer.initialPlaybackTime = 100
        player.moviePlayer.shouldAutoplay = false
        player.moviePlayer.prepareToPlay()
        player.moviePlayer.play()
    }

    @IBAction func changeToView1(sender: AnyObject) {
        let playedTime = player.moviePlayer.currentPlaybackTime
        println(playedTime)
        player.moviePlayer.stop()

        player = XCDYouTubeVideoPlayerViewController(videoIdentifier: "lDcqxj2Xsy4")
        player.presentInView(currentVideo)
        player.moviePlayer.initialPlaybackTime = playedTime
        player.moviePlayer.shouldAutoplay = false
        player.moviePlayer.prepareToPlay()
        player.moviePlayer.play()
    }
    
    @IBAction func changeToView2(sender: AnyObject) {
        let playedTime = player.moviePlayer.currentPlaybackTime
        println(playedTime)
        player.moviePlayer.stop()

        player = XCDYouTubeVideoPlayerViewController(videoIdentifier: "lDcqxj2Xsy4")
        player.presentInView(currentVideo)
        player.moviePlayer.initialPlaybackTime = playedTime
        player.moviePlayer.shouldAutoplay = false
        player.moviePlayer.prepareToPlay()
        player.moviePlayer.play()
    }
    
    @IBAction func changeToView3(sender: AnyObject) {
    }
    
    @IBAction func changeToView4(sender: AnyObject) {
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
