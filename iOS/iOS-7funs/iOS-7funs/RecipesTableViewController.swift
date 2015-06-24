//
//  RecipesTableViewController.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/18/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

import Alamofire
import SwiftyJSON

import RealmSwift

class RecipesTableViewController: UITableViewController {

//    let realm = Realm()
    
    var dishes = Realm().objects(Dish)

    override func viewWillAppear(animated: Bool) {
        self.navigationController?.navigationBarHidden = false
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        println("table view did load")
        
        let connected = Reachability.isConnectedToNetwork()
        if connected {
            // Get the data from remote
            println("has internet connection")
            fetchDataFromRemote()
        }
        
    }
    
    func fetchDataFromRemote() {
        // For testing, just using Parse
        let URL = NSURL(string: "https://api.parse.com/1/classes/Dish")!
        let req = NSMutableURLRequest(URL: URL)
        req.HTTPMethod = "GET"
        req.setValue("MuDkfeN8wGUVbfa5naQJSDHHKDGRRrvoKQj71SBv", forHTTPHeaderField: "X-Parse-Application-Id")
        req.setValue("PJnj58X0SUn3eqy3yIVRssYbm5d8c5khtnfu4d4f", forHTTPHeaderField: "X-Parse-REST-API-Key")
        //req.HTTPBody = params.toURLString().dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: true)
        Alamofire.request(req).validate(statusCode: 200 ..< 300).responseJSON() {
            (_, _, data, _) in
            
            // Convert to SwiftyJson format
            let json = JSON(data!)
            
            dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0)) {
                autoreleasepool {
                    let realm = Realm()
                    
                    let currentDishes = Realm().objects(Dish)
                    
                    var newDishJsons = [JSON]()
                    // Compare what in database and fetched from remote, if remote is not in
                    // local, add to the array for later adding
                    for (key, subJson) in json["results"] {
                        let dishId = subJson["objectId"].string!
                        var had = false
                        
                        for dish in currentDishes {
                            if dish.id == dishId {
                                had = true
                            }
                        }
                        if had == false {
                            newDishJsons.append(subJson)
                        }
                    }
                    
                    // This cleans all the data, a bit too destructrive
                    //realm.write {
                    //    realm.deleteAll()
                    //}
                    
                    println(newDishJsons.count)
                    
                    if newDishJsons.count > 0 {
                        realm.beginWrite()
                        for dishJson in newDishJsons {
                            let dish = Dish()
                            
                            dish.id = dishJson["objectId"].string!
                            dish.cook = dishJson["cook"].string!
                            dish.name = dishJson["name"].string!
                            dish.viewedCount = dishJson["viewedCount"].int!
                            dish.collectedCount = dishJson["collectedCount"].int!
                            // Just write down the name here, when later retrieving file, check if file exists or not
                            dish.image = dishJson["image"]["name"].string!
                            
                            dish.video1 = dishJson["video1"].string!
                            dish.video2 = dishJson["video2"].string!
                            dish.video3 = dishJson["video3"].string!
                            dish.video4 = dishJson["video4"].string!
                            
                            realm.add(dish, update: true)
                        }
                        
                        realm.commitWrite()
                    }
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

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Potentially incomplete method implementation.
        // Return the number of sections.
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete method implementation.
        // Return the number of rows in the section.
        
        return dishes.count
    }

    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("DishCell", forIndexPath: indexPath) as! RecipesDishTableViewCell

        // Configure the cell...
        let dish = dishes[indexPath.row]
        cell.viewedCount.text = String(dish.viewedCount)
        cell.collectedCount.text = String(dish.collectedCount)
        
        let dirPaths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true)
        
        let docsDir = dirPaths[0] as! String
        let destinationPath = docsDir.stringByAppendingPathComponent(dish.image)

        cell.dishImage.image = loadImageFromPath(destinationPath)
        //cell.imagePath = dish.image

        return cell
    }
    
    // Should do the refactory code extracting to utility class
    func loadImageFromPath(path: String) -> UIImage? {
        
        let image = UIImage(contentsOfFile: path)
        
        if image == nil {
            
            println("missing image at: \(path)")
        }
        // this is just for you to see the path in case you want to go to the directory, using Finder.
        println("\(path)")
        
        return image
    }

    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "dishDetailSegue" {
            var destinationVC: RecipesDishDetailTabBarController = segue.destinationViewController as! RecipesDishDetailTabBarController
            
            var indexPath = self.tableView!.indexPathForSelectedRow()
            
            let dish = dishes[indexPath!.row]
            destinationVC.dishDetail = DishDetail(
                cook: dish.cook, dishName: dish.name, viewedCount: dish.viewedCount, collectedCount: dish.collectedCount,
                imagePath: dish.image, video1: dish.video1, video2: dish.video2, video3: dish.video3, video4: dish.video4)
            
        }
    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
    }
    */

}
