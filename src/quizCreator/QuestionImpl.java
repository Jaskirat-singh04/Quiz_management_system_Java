package quizCreator;

import java.util.Scanner;
import java.io.*;
import ui.LoginAndRegistration;
import ui.DynamicDirLocator;

public class QuestionImpl extends LoginAndRegistration {

    private static String quizTitle = "";
    private static String fileName;
    private String questionTitle;
    private int questionId;
    private int questionAnswer;
    private double questionMarks;
    private static int count;

    private String option1, option2, option3, option4;

    public static void setAndSaveQuizTitle(String str) throws QuizAlreadyExist {//use this method before instantiating the class.
        quizTitle = quizTitle.replace(quizTitle, str);
        if (existingQuiz() == true) {
            throw new QuizAlreadyExist();
        } else {
            DynamicDirLocator ob = new DynamicDirLocator();
            //opening the file to save .txt file name
            FileOutputStream write = null;
            File quiz = new File(ob.getFullPath(quizTitle, 2)); //CREATES QUIZ FILE
            File quizScores = new File(ob.getFullPath(quizTitle, 3)); //CREATES SCORECARD FILE CORRESPONDING TO QUIZ
            FileOutputStream fout = null;
            FileOutputStream fout2 = null;
            try {
                 
                write = new FileOutputStream(ob.getFullPath("UserTests_name_and_filenames",2), true);
                String save = quizTitle + "~" + LoginAndRegistration.userid + ","; //userid declared protected in base class
                for (int j = 0; j < save.length(); j++) {
                    write.write(save.charAt(j));
                }
                fout = new FileOutputStream(quiz, true);
                fout2 = new FileOutputStream(quizScores, true);
            } catch (FileNotFoundException e) {
                System.out.println("ERROR : FILE NOT FOUND (method : setAndSaveQuiTitle)!!!");
                System.exit(2001);
            } catch (IOException e) {
                System.out.println("ERROR : ERROR WHILE READING THE FILE (method : setAndSaveQuiTitle)!!!");
                System.exit(2002);
            }
        }
        
    }

    public static void setquizTitle(String name){ //while editing a file only
        quizTitle = name;
        setFileName();
    }
    
    public QuestionImpl(String option1, String option2, String option3, String option4, String questionTitle, int questionId,
             int questionAnswer, double questionMarks) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;

        this.questionTitle = questionTitle;
        this.questionId = questionId;
        this.questionAnswer = questionAnswer;
        this.questionMarks = questionMarks;
        setFileName();
    }
    
    private static void setFileName(){
        DynamicDirLocator ob = new DynamicDirLocator();
        fileName = ob.getFullPath(quizTitle,2);  //filename storing questions
    }

    public boolean addQuestion() {
        String ques = questionId + "_" + questionTitle + "_" + option1 + "_" + option2 + "_" + option3 + "_" + option4 + "_" + questionAnswer + "_" + questionMarks + '\n';
        FileWriter fout = null;
        try {
            fout = new FileWriter(fileName, true);
            fout.write(ques);
        } catch (IOException e) {
            System.out.println("I/O Error : " + e + " method(addQuestion)");
            System.exit(2001);
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e2) {
                System.out.println("Error closing output file");
                System.exit(2001);
            }
        }
        return true;
    }

    public static boolean deleteQuestion(int questionId) {
        String fq = findQuestion(questionId);
        try {
            File f1 = new File(fileName);
            File f2 = new File("temp.txt");
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter("temp.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            String s1;
            while ((s1 = br.readLine()) != null) {
                if (s1.equals(fq)); else {
                    bw.write(s1);
                    bw.newLine();
                }
            }
            try {
                br.close();
                fr.close();
            } catch (IOException e1) {
                System.out.println("Error closing input file");
                System.exit(2001);
            }
            try {
                bw.close();
                fw.close();
            } catch (IOException e2) {
                System.out.println("Error closing output file");
                System.exit(2001);
            }
            boolean b = f1.delete();
            b = f2.renameTo(f1);

        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
            System.exit(2001);
        }
        return true;
    }

    private static String findQuestion(int questionId) {
        String id = "^" + questionId + ".*$";
        String str = "";
        boolean flag = false;
        count=0;
        try (FileInputStream fin = new FileInputStream(fileName); Scanner sc = new Scanner(fin)) {
            while (sc.hasNextLine() && flag != true) {
            	str = sc.nextLine();
                if (str.matches(id)) {
                    flag = true;
                }
                if(flag==true) {
                	count=1;
                }
            }
            
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
            System.exit(2001);
        }
        return str;
    }

    private boolean existingQuestion() {

        boolean b = false;
        int i;
        File myFile = new File(fileName);
        Scanner kb = new Scanner(System.in);
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String Icline = line.toLowerCase();
                String IcqueTitle = questionTitle.toLowerCase();
                i = Icline.indexOf(IcqueTitle);
                if (i != -1) {
                    b = true;
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(2001);
        }
        return b;
    }
    
    private boolean existingId(){

        boolean b = false;
        int i;
        File myFile = new File(fileName);
        Scanner kb = new Scanner(System.in);
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String Icline = line.toLowerCase();
                String IcqueTitle = Integer.toString(questionId);
                i = Icline.indexOf(IcqueTitle);
                if (i != -1) {
                    b = true;  //id found
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(2001);
        }
        return b;
    }
    public boolean checkConstId() throws IdAlreadyExist{
        if(existingId() == true) throw new IdAlreadyExist();
        else return true;
    }
    
    public static boolean checkIdFound(int id) throws IdNotFound {
        String str = findQuestion(id);
        if (count==0) {
            throw new IdNotFound();
        }
        return true;
    }

    public boolean checkConstraints() throws QuestionAlreadyExist, IdAlreadyExist, OptionAlreadyExist, InvalidQuestionAnswer, InvalidId {
        if (questionId < 100 || questionId > 999) {
            throw new InvalidId();
        }

        if (existingQuestion() == true) {
            throw new QuestionAlreadyExist();
        }
        
        

        if (option1.equals(option2)) {
            throw new OptionAlreadyExist();
        }

        if (option3.equals(option1) || option3.equals(option2)) {
            throw new OptionAlreadyExist();
        }

        if (option4.equals(option3) || option4.equals(option2) || option4.equals(option1)) {
            throw new OptionAlreadyExist();
        }

        if (questionAnswer > 4 || questionAnswer < 1) {
            throw new InvalidQuestionAnswer();
        }

        return true; //if control reaches here without throwing any exception
    }

    public boolean checkInputFormat() throws WrongInputFormat { //underscores not allowed
        String str =  questionTitle + questionId + questionMarks +option1 + option2+ option3+ option4;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                throw new WrongInputFormat();
            }
        }
        return true;
    }

    private static boolean existingQuiz() {
        DynamicDirLocator ob = new DynamicDirLocator();
        boolean b = false;
        int i;
        File myFile1 = new File(ob.getFullPath("PreTests_name_and_filenames", 2));
        File myFile2 = new File(ob.getFullPath("UserTests_name_and_filenames", 2));
        Scanner kb = new Scanner(System.in);
        try {
            Scanner sc1 = new Scanner(myFile1);
            Scanner sc2 = new Scanner(myFile2);
            while (sc1.hasNextLine()) {
                String line = sc1.nextLine();
                String Icline = line.toLowerCase();
                String IcqueTitle = quizTitle.toLowerCase();
                i = Icline.indexOf(IcqueTitle);
                if (i != -1) {
                    b = true;
                    break;
                }
            }
            while (sc2.hasNextLine()) {
                String line = sc2.nextLine();
                String Icline = line.toLowerCase();
                String IcqueTitle = quizTitle.toLowerCase();
                i = Icline.indexOf(IcqueTitle);
                if (i != -1) {
                    b = true;
                    break;
                }
            }
            sc1.close();
            sc2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(2001);
        }
        return b; //not found
    }
    
    public void setFilename(String name){ //while editing a file only
        DynamicDirLocator ob = new DynamicDirLocator();
    }
}
