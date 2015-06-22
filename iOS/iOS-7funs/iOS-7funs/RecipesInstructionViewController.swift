//
//  RecipesInstructionViewController.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/22/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class RecipesInstructionViewController: UIViewController {
    
    var dishDetail: DishDetail!

    @IBOutlet weak var dishImage: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        let dirPaths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true)
        
        let docsDir = dirPaths[0] as! String
        let destinationPath = docsDir.stringByAppendingPathComponent(dishDetail.imagePath)
        
        dishImage.image = loadImageFromPath(destinationPath)
    }

    func loadImageFromPath(path: String) -> UIImage? {
        
        let image = UIImage(contentsOfFile: path)
        
        if image == nil {
            
            println("missing image at: \(path)")
        }
        println("\(path)") // this is just for you to see the path in case you want to go to the directory, using Finder.
        return image
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
