
package ui;

import java.io.*;
import quizCreator.*;
import java.util.Scanner;


public class QuizCreator {

    public static String store_exc = "", quiz = "";

    public static boolean QuizTitle(String quizName) { //called on "GO" press
        try {
            quiz = quizName;
            QuestionImpl.setAndSaveQuizTitle(quizName);
            return true;
        } catch (QuestionException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
        }
        return false;

    }
    
     public static void setFilename(String name){ //while editing a file only
        QuestionImpl.setquizTitle(name);
    }

    public static boolean AddQues(String option1, String option2, String option3, String option4, String questionTitle, String questionId, int questionAnswer, String questionMarks) {

        try {
            int q_Id = Integer.parseInt(questionId);
            double marks = Double.parseDouble(questionMarks);
            QuestionImpl ob = new QuestionImpl(option1, option2, option3, option4, questionTitle, q_Id, questionAnswer, marks);
            if ((ob.checkConstId() == true) && (ob.checkConstraints() == true) && (ob.checkInputFormat() == true) && (ob.addQuestion() == true)) {
                return true;
            }
        } catch (QuestionException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
        } catch (NumberFormatException e) {
            store_exc = "ERROR : ENTER VALID INPUTS!!!";
        }
        return false;

    }

    public static boolean DelQues(String qId) {
        
        try {
            int questionId = Integer.parseInt(qId);
            if ((QuestionImpl.checkIdFound(questionId) == true) && (QuestionImpl.deleteQuestion(questionId) == true)) {
                return true;
            }
        } catch (QuestionException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
        } catch (NumberFormatException e) {
            store_exc = "ERROR : ENTER VALID INPUTS!!!";
        }
        return false;
    }
    
    public static boolean ReplaceQues(String option1, String option2, String option3, String option4, String questionTitle, String questionId, int questionAnswer, String questionMarks){
        try{
            int q_Id = Integer.parseInt(questionId);
            double marks = Double.parseDouble(questionMarks);
            QuestionImpl ob = new QuestionImpl(option1, option2, option3, option4, questionTitle, q_Id, questionAnswer, marks);
            if( (ob.checkConstraints() == true) && (ob.checkInputFormat() == true) ){
                if( (DelQues(questionId) == true) && (AddQues(option1,option2,option3,option4,questionTitle,questionId,questionAnswer,questionMarks) == true) ){
                    return true;
                }
            }
        }catch (QuestionException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
        } catch (NumberFormatException e) {
            store_exc = "ERROR : ENTER VALID INPUTS!!!";
        }
        return false;
    }
    
    public static boolean saveAllowedUsers(String str) { //directory(full address of .txt file of allowed users
        
        DynamicDirLocator ob = new DynamicDirLocator();
        FileWriter temp_fout = null;
        FileWriter per_fout = null;
        try {
            temp_fout = new FileWriter(ob.getFullPath("temp",1),false);
            temp_fout.write(str);
            temp_fout.close();
            //reading and copying in arranged manner
            Scanner scn = new Scanner(new File(ob.getFullPath("temp", 1)));
            per_fout = new FileWriter(ob.getFullPath("allowed_users_file",2),true);
            per_fout.write(quiz + "_");
            while(scn.hasNextLine()){
                per_fout.write(scn.nextLine() + ",");
            }
            per_fout.write("\n");
            scn.close(); 
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
            return false;
        } finally {
            try {
                if (temp_fout != null) {
                    temp_fout.close();
                }
                if(per_fout != null){
                    per_fout.close();
                }
            } catch (IOException e2) {
                System.out.println("Error closing output file");
                System.exit(2001);
            }
        }
        return true;
    }
}
