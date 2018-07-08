/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author bitzero
 */
public class ImproperExcessiveAuthenticationAttemptsRestriction {

    public void test() {
        System.out.println("\n\nRunning ImproperExcessiveAuthenticationAttemptsRestriction");
        porous();
        fixed();
    }

    public void porous() {
        System.out.println("\nPorous\n");
        loggedIn = false;
        while (!loggedIn) {
            promptForUsernameAndPwd();
            loggedIn = loginPorous(inputUsername, inputPassword);
        }
    }

    public void fixed() {
        System.out.println("\nFixed\n");
        loggedIn = false;
        while (!loggedIn) {
            promptForUsernameAndPwd();
            loggedIn = loginFixed(inputUsername, inputPassword);
        }
    }

    //Varriables Declarations
    boolean loggedIn = false;
    //The valid usename and password ie root and admin respectively are hardcoded for simplicity
    String username = "root", password = "admin", inputUsername = "", inputPassword = "";
    //Here: set the maximum Attempts and minimum waiting time in Minutes after maximum failed login attempts are reached
    int attempts = 0, maxAttemptss = 2, waitingMinutes = 2;
    LocalTime nextLoginTime = LocalTime.now();
    Scanner scn = new Scanner(System.in);

    //Number of Login attempts not checked thus an attacher can make many trials untill he/she eventually succeds
    public boolean loginPorous(String username, String password) {
        if (authenticate(username, password)) {
            System.out.println("Login Successfull");
            return true;
        }
        System.out.println("Login Failed");
        return false;
    }
    
    //Number of Login attempts are restricted to a predefined number and when the max trials is reached, 
    //the login is haulted for a predefined minutes before another attempt to authenticate.
    //With this an attacher will take longer to try out many passwords plus a lot of waits on the way.
    public boolean loginFixed(String username, String password) {

        if (this.attempts < this.maxAttemptss) {
            if (authenticate(username, password)) {
                System.out.println("Login Successfull");
                attempts = 0;
                return true;
            } else {
                System.out.println("Login Failed");
                //System.out.println("=============login attempts " + attempts);
                attempts++;
            }
        } else {
            if (!eligibleForMoreTrials()) {
                return false;
            }
            nextLoginTime = LocalTime.now().plusMinutes(waitingMinutes);
        }
        return false;
    }

    private boolean authenticate(String username, String password) {
        //System.out.println("Right Username :: " + username + " ::: Entered : " + this.username + " \nRight Password :: " + password + " :::  Entered: " + this.password);
        return username.equals(this.username) && password.equals(this.password);
    }

    private boolean eligibleForMoreTrials() {
        //System.out.println("nextLoginTime ::: " + nextLoginTime);
        if (LocalTime.now().isAfter(this.nextLoginTime)) {
            attempts = 0;
            return true;
        }
        System.out.println("You will have to wait for " + LocalTime.now().until(this.nextLoginTime, ChronoUnit.SECONDS) + " seconds to try again");
        return false;
    }

    public void promptForUsernameAndPwd() {
        System.out.print("Enter Username : ");
        inputUsername = scn.next();
        System.out.print("Enter Password : ");
        inputPassword = scn.next();
        System.out.println("");
    }

}
