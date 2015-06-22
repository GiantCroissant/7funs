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
        
        instructionVC.dishDetail = DishDetail(viewedCount: self.dishDetail.viewedCount, collectedCount: self.dishDetail.collectedCount)
        videoVC.dishDetail = DishDetail(viewedCount: self.dishDetail.viewedCount, collectedCount: self.dishDetail.collectedCount)
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
