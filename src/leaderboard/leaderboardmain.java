package leaderboard;

import ui.QuizConductor;
import ui.DynamicDirLocator;
import ui.LoginAndRegistration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class leaderboardmain extends LoginAndRegistration implements leaderboard {

    private String userid;
    private double obtainmarks;
    private double totalmarks;
    static private int count;
    private static String quizname = "";
    public static String fileName;
    
    public leaderboardmain(String userid, String quiztitle, double obtainmarks, double totalmarks) {

        this.userid = LoginAndRegistration.userid;
        this.obtainmarks = QuizConductor.getObtainedMarks();
        this.totalmarks = QuizConductor.getTotalMarks();
        DynamicDirLocator ob = new DynamicDirLocator();
        quizname = quiztitle;
        fileName = ob.getFullPath(quizname, 3);   //filename to store quiz score

    }

    public void setAndSaveQuizScore() {
        try {
            File scorecard = new File(fileName); //CREATE THE FILE(IF NOT PRESENT)
            if ((isRestrictedQuiz() == true) && (existingUser() == true)) {
                //do nothing (simulates single attempt)
            } else{
                String str = userid + "_" + totalmarks + "_" + obtainmarks + "\n";
                FileWriter myWriter = new FileWriter(fileName, true);
                myWriter.write(str);
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("ERROR : COULDN'T SAVE SCORES(method : setAndSaveQuizScore())!!!");
            System.exit(1001);
        }

    }

    @Override
    public void sortfile() {
        String[] line = new String[30];
        int j = 0;  // j will keep the total count of lines
        try {
            FileReader fw = new FileReader(fileName); //opens the file where marks are stored
            Scanner sc = new Scanner(fw);
            
            while (sc.hasNextLine()) {
                line[j] = sc.nextLine();
                j++;
            }
            float max;
            int pos; //max will fetch the max marks from the line and pos will store the position of the max marks
            String maxline;// store the line where the max marks are at
            for (int i = 0; i < j; i++) {
                maxline = line[i]; //assumes first line is max
                max = Float.parseFloat(line[i].substring(line[i].lastIndexOf('_') + 1));
                pos = i;
                for (int k = i + 1; k < j; k++) {
                    if (Float.parseFloat(line[k].substring(line[k].lastIndexOf('_') + 1)) > max) //checks if any line with greater number of marks are present
                    {
                        maxline = line[k]; //makes the line with max no. of marks as maxline
                        pos = k;
                    }
                }

                line[pos] = line[i];  //swaps the line
                line[i] = maxline;
            }
            try {
                FileWriter fr = new FileWriter(fileName,false);  // writes the sorted list into the file
                int k = 0;
                if (isRestrictedQuiz() == false) { //only save top 5
                    while ( (k < j) && (k < 5) ) {
                        fr.write(line[k] + "\n");
                        k++;
                    }
                } else { //save whole
                    while(k < j){
                        fr.write(line[k] + "\n");
                        k++;
                    }
                }

                fr.close();
            } catch (Exception e) {
                System.out.println("ERROR : UNABLE TO WRITE(method : sortfile())");
                System.exit(1001);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("ERROR : UNABLE TO READ(method : sortfile())");
            System.exit(1002);
        }
    }

    public static void tableData() {
        try {
            File myFile = new File(fileName);
            Scanner sc = new Scanner(new BufferedReader(new FileReader(myFile)));
            int rows = 4;
            int columns = 3;
            int[][] myArray = new int[rows][columns];
            while (sc.hasNextLine()) {
                for (int i = 0; i < myArray.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR : UNABLE TO READ(method : tableData())");
            System.exit(1001);
        }

    }

    private boolean existingUser() { //CHECK FOR EXISTING USER(RESTRICTED QUIZZES

        boolean b = false;
        int i;
        File myFile = new File(fileName);
        Scanner kb = new Scanner(System.in);
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String Icline = line.toLowerCase();
                String IcqueTitle = userid.toLowerCase();
                i = Icline.indexOf(IcqueTitle);
                if (i != -1) {
                    b = true;
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR : FILE WITH SCORES NOT FOUND(method : existingUser())");
            System.exit(1001);
        }
        return b;
    }

    private boolean isRestrictedQuiz() { //for complete scorecard
        DynamicDirLocator.workingDir();
        DynamicDirLocator ob = new DynamicDirLocator();

        File myFile = null;
        try {
            myFile = new File(ob.getFullPath("allowed_users_file", 2));
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int i = line.indexOf(quizname);    //SEARCH FOR TEST SPECIFIED
                if (i != -1) { //if found ==> restricted quiz
                    sc.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR : FILE WITH ALLOWED USERS NOT FOUND(method : isRestrictedQuiz())");
            System.exit(1001);
        }
        return false; //not a restriceted one
    }

    public String getQuizName() {
        return quizname;
    }

    public String getUserid() {
        return userid;
    }

    public double getobtainmarks() {
        return obtainmarks;
    }

    public double Totalmarks() {
        return totalmarks;
    }
}
