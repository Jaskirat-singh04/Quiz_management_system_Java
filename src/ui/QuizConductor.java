package ui;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import ui.DynamicDirLocator;

public class QuizConductor {
    private static double totalMarks = 0,marksObt = 0;
    private static String[] ary = new String[20];   //to store q_ids displayed(not to display again
    private static int i = -1; //array index count
    private String qID,qTitle, op1, op2, op3, op4; //for each question
    private static String correctAnswer,marks;
    public static int correctAns = 0; //COUNT OF CORRECT ANSWERS
    public static String fileName;
    private boolean flag = false; //false ==> question will be scanned
    public String file;
    
    
    
    public static int getNoOfQuestions(String file){  //call first ,returns noof questions and also calculates max. marks
        totalMarks = 0;marksObt =0;  //reseting
        DynamicDirLocator ob = new DynamicDirLocator();
        fileName = ob.getFullPath(file, 2);
        int ques_count = 0;
        File myFile = null;
        try {
            myFile = new File(fileName);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                ques_count++;
                String line = sc.nextLine();
                int ind1 = line.indexOf('_');
                int ind2 = line.indexOf('_', ind1 + 1);
                int ind3 = line.indexOf('_', ind2 + 1);
                int ind4 = line.indexOf('_', ind3 + 1);
                int ind5 = line.indexOf('_', ind4 + 1);
                int ind6 = line.indexOf('_', ind5 + 1);
                int ind7 = line.indexOf('_', ind6 + 1);
                String que_marks = line.substring(ind7 + 1);
                totalMarks = totalMarks + Double.parseDouble(que_marks);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(2001);
        }
        return ques_count;
    }

    public static void initializeAry() {
        i = -1; //reseting
        Arrays.fill(ary, " ");
    }
    
    public QuizConductor(String file){
    	this.file=file;
        DynamicDirLocator ob = new DynamicDirLocator();
        fileName = ob.getFullPath(file, 2);
        String tempID;
        File myFile = new File(fileName);
        try {
            Scanner sc = new Scanner(myFile);
            int ind1;
            while (sc.hasNextLine()) {
                flag = false;
                String line = sc.nextLine();
                //question id
                ind1 = line.indexOf('_');
                tempID = line.substring(0, ind1);
                //array of q_id's(scanned only)
                for (String s : ary) {
                    if (tempID.equals(s)) {
                        flag = true; //already scanned -->skip it
                        break;
                    }
                }

                if (flag == false) {
                    String tempqID;
                    qID = tempID;
                    tempqID = qID;
                    ary[++i] = tempqID;
                    int ind2 = line.indexOf('_', ind1 + 1);
                    qTitle = line.substring(ind1 + 1, ind2);
                    int ind3 = line.indexOf('_', ind2 + 1);
                    op1 = line.substring(ind2 + 1, ind3);
                    int ind4 = line.indexOf('_', ind3 + 1);
                    op2 = line.substring(ind3 + 1, ind4);
                    int ind5 = line.indexOf('_', ind4 + 1);
                    op3 = line.substring(ind4 + 1, ind5);
                    int ind6 = line.indexOf('_', ind5 + 1);
                    op4 = line.substring(ind5 + 1, ind6);
                    int ind7 = line.indexOf('_', ind6 + 1);
                    correctAnswer = line.substring(ind6 + 1, ind7);
                    marks = line.substring(ind7 + 1);
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(2001);
        }
    }
    


    public static void calcMarks(int a) {
        int correct = Integer.parseInt(correctAnswer); //correct option
        double marksAllot = Double.parseDouble(marks); //marks for correct answer
        if (a == correct){
            marksObt += marksAllot;
            correctAns++;
        }
            
    }
    
    public static void resetMarks(){
        marksObt = 0;
        correctAns = 0;
    }

    //getters--->
    public String getId() {
        return qID;
    }

    public String getQuestionTitle() {
        return qTitle;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String getOp4() {
        return op4;
    }

    public String getMarks() { //single ques
        return marks;
    }

    public static double getTotalMarks() { //max marks
        return totalMarks;
    }
    public static double getObtainedMarks(){ //obtained marks
        return marksObt;
    }


}

