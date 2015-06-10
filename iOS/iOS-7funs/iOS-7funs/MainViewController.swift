//
//  MainViewController.swift
//  iOS-7funs
//
//  Created by Bryan Lin on 6/7/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit
import Alamofire
import RealmSwift

class Dog: Object {
    dynamic var name = ""
    dynamic var owner: Person?
}

class Person: Object {
    dynamic var id = 0
    dynamic var name = ""
    dynamic var birthdate = NSDate(timeIntervalSince1970: 1)
    let dogs = List<Dog>()

    override static func primaryKey() -> String? {
        return "id"
    }
}

class MainViewController: UIViewController {

    @IBAction func showInfo(sender: UIButton) {

        var storyboard = UIStoryboard(name: "ShowInfo", bundle: nil)

        var controller = storyboard.instantiateInitialViewController() as! UIViewController

        self.navigationController?.pushViewController(controller, animated: true)
    }


    override func viewDidLoad() {
        super.viewDidLoad()

        let author = Person()
        author.id = 11
//        author.id = NSUUID().UUIDString
//        author.name = JSON.("")

        let realm = Realm()

        realm.write {
            realm.add(author, update: true)
        }

        realm.write {
            author.name = "Thomas Pynchon"
        }


        let theAuthor = realm.objects(Person)
        println(theAuthor)
    }

    func usageTest() {
        makingARequest()
        responseHandling()
        responseStringHandler()
        chainedResponseHandlers()
    }

    func makingARequest() {
        Alamofire.request(.GET, "http://httpbin.org/get")
    }

    func responseHandling() {
        Alamofire.request(.GET, "http://httpbin.org/get", parameters: ["foo": "bar"])
            .response { (request, response, data, error) in

                println("--------------------------------------------------")
                println("                  responseHandling                ")
                println("--------------------------------------------------")
                println(request)
                println(response)
                println(error)
        }
    }

    func responseStringHandler() {
        Alamofire.request(.GET, "http://httpbin.org/get")
            .responseString { (_, _, string, _) in

                println("--------------------------------------------------")
                println("                  responseStringHandler           ")
                println("--------------------------------------------------")
                println(string)
        }
    }

    func responseJSONHandler() {
        Alamofire.request(.GET, "http://httpbin.org/get")
            .responseJSON { (_, _, JSON, _) in
                println("--------------------------------------------------")
                println("                  responseJSONHandler             ")
                println("--------------------------------------------------")
                println(JSON)
        }
    }

    func chainedResponseHandlers() {
        Alamofire.request(.GET, "http://httpbin.org/get")
            .responseString { (_, _, string, _) in
                println("--------------------------------------------------")
                println("                  chainedResponseHandlers 1       ")
                println("--------------------------------------------------")
                println(string)
            }
            .responseJSON { (_, _, JSON, _) in
                println("--------------------------------------------------")
                println("                  chainedResponseHandlers 2       ")
                println("--------------------------------------------------")
                println(JSON)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func viewWillAppear(animated: Bool) {
        self.navigationController?.navigationBarHidden = true
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
