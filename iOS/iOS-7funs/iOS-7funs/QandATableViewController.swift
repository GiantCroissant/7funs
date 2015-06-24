//
//  QandATableViewController.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/24/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

import Alamofire
import SwiftyJSON

import RealmSwift

class QandATableViewController: UITableViewController {
    
    var questions = Realm().objects(Question)

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        let connected = Reachability.isConnectedToNetwork()
        if connected {
            // Get the data from remote
            println("has internet connection")
            fetchQuestionDataFromRemote()
            fetchQuestionResponseDataFromRemote()
        }
    }
    
    func fetchQuestionDataFromRemote() {
        // For testing, just using Parse
        let URL = NSURL(string: "https://api.parse.com/1/classes/Question")!
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
                    
                    let currentQuestions = Realm().objects(Question)
                    
                    var newQuestionJsons = [JSON]()
                    // Compare what in database and fetched from remote, if remote is not in
                    // local, add to the array for later adding
                    for (key, subJson) in json["results"] {
                        let questionId = subJson["objectId"].string!
                        var had = false
                        
                        for question in currentQuestions {
                            if question.id == questionId {
                                had = true
                            }
                        }
                        if had == false {
                            newQuestionJsons.append(subJson)
                        }
                    }
                    
                    println(newQuestionJsons.count)
                    
                    if newQuestionJsons.count > 0 {
                        realm.beginWrite()
                        for questionJson in newQuestionJsons {
                            //let dish = Dish()
                            let question = Question()
                            
                            question.id = questionJson["objectId"].string!
                            question.title = questionJson["title"].string!
                            question.content = questionJson["content"].string!
                            question.ownerName = questionJson["ownerName"].string!
                            question.ownerIconUrl = questionJson["ownerIconUrl"].string!
                            question.responseId = questionJson["responseId"].string!
                            
                            realm.add(question, update: true)
                        }
                        
                        realm.commitWrite()
                    }
                }
            }
            
            // The following is specific to Parse return format
            //for (key, subJson) in json["results"] {
            //    let urlString = subJson["image"]["url"].string
            //    let fileName = subJson["image"]["name"].string
            //    let imageUrl = NSURL(string: urlString!)
            //    self.downloadImage(imageUrl!, fileName: fileName!)
            //}
            
            //println(data)
        }
    }
    
    func fetchQuestionResponseDataFromRemote() {
        // For testing, just using Parse
        let URL = NSURL(string: "https://api.parse.com/1/classes/QuestionResponse")!
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
                    
                    let currentQuestionResponses = Realm().objects(QuestionResponse)
                    
                    var newQuestionResponseJsons = [JSON]()
                    // Compare what in database and fetched from remote, if remote is not in
                    // local, add to the array for later adding
                    for (key, subJson) in json["results"] {
                        let questionResponseId = subJson["objectId"].string!
                        var had = false
                        
                        for questionResponse in currentQuestionResponses {
                            if questionResponse.id == questionResponseId {
                                had = true
                            }
                        }
                        if had == false {
                            newQuestionResponseJsons.append(subJson)
                        }
                    }
                    
                    println(newQuestionResponseJsons.count)
                    
                    if newQuestionResponseJsons.count > 0 {
                        realm.beginWrite()
                        for questionResponseJson in newQuestionResponseJsons {
                            //let dish = Dish()
                            let questionResponse = QuestionResponse()
                            
                            questionResponse.id = questionResponseJson["objectId"].string!
                            questionResponse.content = questionResponseJson["content"].string!
                            questionResponse.ownerName = questionResponseJson["ownerName"].string!
                            questionResponse.ownerIconUrl = questionResponseJson["ownerIconUrl"].string!
                            questionResponse.questionId = questionResponseJson["questionId"].string!
                            
                            realm.add(questionResponse, update: true)
                        }
                        
                        realm.commitWrite()
                    }
                }
            }
            
            // The following is specific to Parse return format
            //for (key, subJson) in json["results"] {
            //    let urlString = subJson["image"]["url"].string
            //    let fileName = subJson["image"]["name"].string
            //    let imageUrl = NSURL(string: urlString!)
            //    self.downloadImage(imageUrl!, fileName: fileName!)
            //}
            
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
        return questions.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
//        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath) as! UITableViewCell
//
//        // Configure the cell...
//
//        return cell
        let cell = tableView.dequeueReusableCellWithIdentifier("QuestionCell", forIndexPath: indexPath) as! QuestionTableViewCell
        
        // Configure the cell...
        let question = questions[indexPath.row]
        cell.name.text = String(question.ownerName)
        cell.title.text = String(question.title)
        cell.timeStamp.text = "10 minutes"
        cell.replyCount.text = "5"
        //cell.viewedCount.text = String(dish.viewedCount)
        //cell.collectedCount.text = String(dish.collectedCount)
        
        let dirPaths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true)
        
        let docsDir = dirPaths[0] as! String
        //let destinationPath = docsDir.stringByAppendingPathComponent(dish.image)
        
        //cell.dishImage.image = loadImageFromPath(destinationPath)
        //cell.imagePath = dish.image
        
        return cell
    }
    
    func getDataFromUrl(urL:NSURL, completion: ((data: NSData?) -> Void)) {
        NSURLSession.sharedSession().dataTaskWithURL(urL) { (data, response, error) in
            completion(data: data)
            }.resume()
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "questionResponseSegue" {
            var destinationVC = segue.destinationViewController as! QandAResponseTableViewController
            
            var indexPath = self.tableView!.indexPathForSelectedRow()
            
            let question = questions[indexPath!.row]
            
            let predicate = NSPredicate(format: "questionId = %@", question.id)
            var queryResults = Realm().objects(QuestionResponse).filter(predicate)
            
            var questionResponseDetails = [QuestionResponseDetail]()
            for result in queryResults {
                let qrd = QuestionResponseDetail(
                    content: result.content, ownerName: result.ownerName, ownerIconUrl: result.ownerIconUrl)
                
                questionResponseDetails.append(qrd)
            }
            
            let qd = QuestionDetail(title: question.title, content: question.title, ownerName: question.ownerName, ownerIconUrl: question.ownerIconUrl, responses: questionResponseDetails)
            
            destinationVC.questionDetail = qd
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
