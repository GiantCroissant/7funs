//
//  MainViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/7/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

import Alamofire
import SwiftyJSON

import RealmSwift

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
        
        // Try to do http request here for testing
        
        // Get default realm db
        //let realm = Realm()
        
        // For testing, just using Parse
        let URL = NSURL(string: "https://api.parse.com/1/classes/Dish")!
        let req = NSMutableURLRequest(URL: URL)
        req.HTTPMethod = "GET"
        req.setValue("MuDkfeN8wGUVbfa5naQJSDHHKDGRRrvoKQj71SBv", forHTTPHeaderField: "X-Parse-Application-Id")
        req.setValue("PJnj58X0SUn3eqy3yIVRssYbm5d8c5khtnfu4d4f", forHTTPHeaderField: "X-Parse-REST-API-Key")
        //req.HTTPBody = params.toURLString().dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: true)
        Alamofire.request(req).validate(statusCode: 200 ..< 300).responseJSON() {
            (_, _, data, _) in

            //realm.write {
            //    realm.deleteAll()
            //}
            
            // Convert to SwiftyJson format
            let json = JSON(data!)

            dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0)) {
                autoreleasepool {
                    let realm = Realm()
                    
                    // This cleans all the data, a bit too destructrive
                    realm.write {
                        realm.deleteAll()
                    }
                    
                    realm.beginWrite()
                    //
                    for (key, subJson) in json["results"] {
                        let dish = Dish()
                        dish.id = subJson["objectId"].string!
                        dish.cook = subJson["cook"].string!
                        dish.name = subJson["name"].string!
                        dish.viewedCount = subJson["viewedCount"].int!
                        dish.collectedCount = subJson["collectedCount"].int!
                        // Just write down the name here, when later retrieving file, check if file exists or not
                        dish.image = subJson["image"]["name"].string!
                        
                        // Leave video address blank for now

                        realm.add(dish, update: true)
                        
                    }
                    realm.commitWrite()
                }
            }
            
            // The following is specific to Parse return format
            for (key, subJson) in json["results"] {
                let urlString = subJson["image"]["url"].string
                let fileName = subJson["image"]["name"].string
                let imageUrl = NSURL(string: urlString!)
                self.downloadImage(imageUrl!, fileName: fileName!)
            }
            
            //println(data)
        }
    }
    
    func downloadImage(url:NSURL, fileName:String){
        //println("Started downloading \"\(url.lastPathComponent!.stringByDeletingPathExtension)\".")
        getDataFromUrl(url) { data in
            dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0)) {
                //println("Finished downloading \"\(url.lastPathComponent!.stringByDeletingPathExtension)\".")
                let image = UIImage(data: data!)
                
                // Sotre in to document folder
                let dirPaths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true)
                
                let docsDir = dirPaths[0] as! String
                let destinationPath = docsDir.stringByAppendingPathComponent(fileName)
                println(destinationPath)
                // For testing, assume all images are in the format of jpg
                UIImageJPEGRepresentation(image, 1.0).writeToFile(destinationPath, atomically: true)
            }
        }
    }
    
    func getDataFromUrl(urL:NSURL, completion: ((data: NSData?) -> Void)) {
        NSURLSession.sharedSession().dataTaskWithURL(urL) { (data, response, error) in
            completion(data: data)
        }.resume()
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
