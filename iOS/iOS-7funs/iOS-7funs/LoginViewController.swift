//
//  LoginViewController.swift
//  iOS-7funs
//
//  Created by Apprentice on 6/30/15.
//  Copyright (c) 2015 giantcroissant. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    
    let accountName = "abc"
    let passwrod = "1234"

    @IBOutlet weak var accountText: UITextField!
    @IBOutlet weak var passwordText: UITextField!
    
    @IBAction func loginPressed(sender: AnyObject) {
        
        let checkResult = checkLogin(accountText.text, password: passwordText.text)
        if (checkResult) {
            self.navigationController?.popViewControllerAnimated(true)
        }
    }
    
    func checkLogin(username: String, password: String ) -> Bool {
        if ((username == accountName) && (password == passwrod)) {
            return true
        } else {
            return false
        }
    }
    
    @IBAction func facebookPressed(sender: AnyObject) {
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
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
