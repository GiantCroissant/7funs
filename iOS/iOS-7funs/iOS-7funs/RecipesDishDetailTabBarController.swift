//
//  RecipesDishDetailTabBarController.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/22/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class RecipesDishDetailTabBarController: UITabBarController {
    
    var dishDetail: DishDetail!

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        //println(dishDetail!.viewedCount)
        //println(dishDetail!.collectedCount)
        
        let vcs = self.viewControllers
        let instructionVC = vcs![0] as! RecipesInstructionViewController
        let videoVC = vcs![1] as! RecipesVideoViewController
        
        instructionVC.dishDetail = DishDetail(
            cook: self.dishDetail.cook, dishName: self.dishDetail.dishName, viewedCount: self.dishDetail.viewedCount, collectedCount: self.dishDetail.collectedCount,
            imagePath: self.dishDetail.imagePath, video1: self.dishDetail.video1, video2: self.dishDetail.video2, video3: self.dishDetail.video3, video4: self.dishDetail.video4)
        videoVC.dishDetail = DishDetail(
            cook: self.dishDetail.cook, dishName: self.dishDetail.dishName, viewedCount: self.dishDetail.viewedCount, collectedCount: self.dishDetail.collectedCount,
            imagePath: self.dishDetail.imagePath, video1: self.dishDetail.video1, video2: self.dishDetail.video2, video3: self.dishDetail.video3, video4: self.dishDetail.video4)
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
