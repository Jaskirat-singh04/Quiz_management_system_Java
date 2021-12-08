/*
 *FUNCTIONS OF CLASS - 
            1. prevents editing of quizzes by other usres than admin(creator of the quizzes).
            2. regulates the access to the quizzes(limited user quizzes)
 */
package registrationAndVerification;

import ui.LoginAndRegistration;
import ui.DynamicDirLocator;
import java.util.Scanner;
import java.io.*;

public class AdminVerification {

    public static boolean checkAdmin(String test) { //PREVENTS EDITING
        DynamicDirLocator.workingDir();
        DynamicDirLocator ob = new DynamicDirLocator();
        String currentUser = LoginAndRegistration.userid;

        File myFile = null;
        try {
            myFile = new File(ob.getFullPath("UserTests_name_and_filenames", 2));
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int i = line.indexOf(test);   //SEARCH FOR TEST SPECIFIED
                if (i != -1) {
                    int j = line.indexOf(',', i); //searches of end of this file's details ie. for ,
                    String subString = line.substring(i + test.length() + 1, j);//admin user corresponding to test
                    if (subString.equals(currentUser) == true) {
                        return true; //admin verified
                    } else {
                        return false;
                    }
                } else {
                    return false; //PRELOADED quiz
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(2001);
        }
        return false;
    }

    public static boolean checkAllowedUser(String test) { //prevents access
        DynamicDirLocator.workingDir();
        DynamicDirLocator ob = new DynamicDirLocator();
        String currentUser = LoginAndRegistration.userid;

        File myFile = null;
        try {
            myFile = new File(ob.getFullPath("allowed_users_file", 2));
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int i = line.indexOf(test);    //SEARCH FOR TEST SPECIFIED
                if (i != -1) {
                    i = line.indexOf(LoginAndRegistration.userid);
                    if (i != -1) {
                        sc.close();
                        return true; //allowed
                    }
                    else{
                        sc.close();
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(2001);
        }
        return true; //preloaded or open quiz
    }
}
