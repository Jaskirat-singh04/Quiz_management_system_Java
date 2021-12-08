package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import registrationAndVerification.AdminVerification;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import leaderboard.leaderboardmain;

public class MainUserInterface extends JFrame implements ActionListener, ItemListener,TableModelListener {
    
    boolean looping_panels = false;  //checks whether to loop a single panel many times
    int loop_nos = 0, panels_count = 0; //no of loops required , current loop
    
    Panel osCards;     //deck panel
    CardLayout cardLO; //layout for whole frame
    leaderboardmain lb;
    //login page(panel-1)
    JLabel progName,id,pass; JButton login, new_user, forget_pass,exit; JTextField userid; JPasswordField login_pass;
    
    //registration page(panel -2)
    JLabel l_head,l_username,l_first_name,l_surname,l_password,l_ques,l_ans; JButton register, login_page1,forget_pass1,exit1; JTextField username, first_name, surname, password, ques, ans; 
    
    //recovery page(panel -3)
    JLabel r_head,l_f_userid,l_f_ans ; JButton  login_page2, get_ques, recover_pass,exit2,register2; JTextField  f_userid, f_ans;
    
    //home page(panel-4)
    JLabel choose_test,h_head; JButton create_new, edit, logout, start,scorecards,exit3; Choice tests;
    
    //for creating new tests
        //panel-1(quiz details)
        JLabel l_testName,l_ques_nos,l_open_for,l_allowed_users; JButton go, home; JTextField testName; Choice ques_nos,open_for; TextArea allowed_users;
        //panel-2(questions-add)
        JLabel l_ques_no,l_q_id,l_question,l_op1,l_op2,l_op3,l_op4,l_marks,l_correct ;JButton save;JTextField  question, q_id, op1, op2, op3, op4,marks;Choice correct;
    
    //for editing a quiz
        //panel-1(adding)
        JLabel l_add_head,l_question2, l_q_id2, l_op12, l_op22, l_op32, l_op42,l_marks2,l_correct2;JButton add2, delete2, replace2, done2, back2;JTextField question2, q_id2, op12, op22, op32, op42,marks2; Choice correct2;
        //panel-2(deleting)
        JLabel l_del_head,l_q_id3; JButton add3, delete3, replace3, done3, back3;JTextField q_id3;
        //panel-3(replacing)
        JLabel l_rep_head,l_q_id4, l_question4, l_op14, l_op24, l_op34, l_op44,l_marks4,l_correct4;JButton add4, delete4, replace4, done4, back4;JTextField q_id4, question4, op14, op24, op34, op44,marks4;Choice correct4; 
        
    //for quiz conductor
        //panel-1(quiz)
        JLabel l_showQue,l_qno,l_showMarks; JButton submit;Checkbox cb1, cb2, cb3, cb4;CheckboxGroup gp1;
        //panel-2(result)
        JLabel l_res_head,l_totalQues,l_corAns,l_inAns,l_marksObt,l_per; JButton final_home;
        
    //for scorecards 
        JLabel l_score_head,l_test2; JButton score_home; JTable scoresheet;DefaultTableModel tableModel;JTableHeader tableHeader;
        
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    

    public MainUserInterface() {
        Font f1 = new Font("aerial black",Font.BOLD,24);//for labels
        Font f2 = new Font("aerial black",Font.BOLD,14);//for labels
        Font f3 = new Font("SansSerif",Font.PLAIN,18);
        setLayout(new FlowLayout());
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~first page----->login page(panel-1)
       
        progName = new JLabel("QUIZ CREATOR");progName.setOpaque(true);progName.setPreferredSize(new Dimension(600,100));progName.setBackground(new Color(0,0,0));progName.setForeground(new Color(255,102,0));progName.setFont(new Font("algerian",Font.BOLD,56));progName.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));progName.setHorizontalAlignment(JLabel.CENTER);
        id = new JLabel("USERNAME");id.setFont(f1);
        pass = new JLabel("PASSWORD");pass.setFont(f1);
        login = new JButton("LOGIN");login.setPreferredSize(new Dimension(100,40));login.setBackground(new Color(0,0,0));login.setForeground(new Color(255,102,0));login.setFont(f1);login.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        new_user = new JButton("NEW USER?");new_user.setBackground(new Color(0,0,0));new_user.setForeground(new Color(255,102,0));new_user.setFont(f2);
        forget_pass = new JButton("FORGET PASSWORD?");forget_pass.setBackground(new Color(0,0,0));forget_pass.setForeground(new Color(255,102,0));forget_pass.setFont(f2);
        exit = new JButton("EXIT");exit.setBackground(new Color(0,0,0));exit.setForeground(new Color(255,102,0));exit.setFont(f2);
        userid = new JTextField(12);userid.setFont(f1);userid.setBackground(new Color(255,204,163));
        login_pass = new JPasswordField(12);login_pass.setEchoChar('*');login_pass.setFont(f1);login_pass.setBackground(new Color(255,204,163));
        //MAIN PANEL
        Panel loginPage = new Panel();
        loginPage.setLayout(new BorderLayout());
        //SUB-PANELS
        Panel top1 = new Panel();
        Panel centre1 = new Panel();
        Panel right1 = new Panel();
        //ADDING COMPONENTS TO SUB-PANELS
        top1.setLayout(new FlowLayout());top1.setBackground(new Color(0,0,0));
        top1.add(progName);
        
        centre1.setLayout(new GridBagLayout());centre1.setBackground(new Color(255,204,153));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;   //place components at start if cells
        gbc.insets = new Insets(20,20,20,20); //spacing bw cells
        gbc.gridx = 0;gbc.gridy = 0;centre1.add(id,gbc);
        gbc.gridx = 1;gbc.gridy = 0;centre1.add(userid,gbc);
        gbc.gridx = 0;gbc.gridy = 1;centre1.add(pass,gbc);
        gbc.gridx = 1;gbc.gridy = 1;centre1.add(login_pass,gbc);
        gbc.gridx = 1;gbc.gridy = 2;centre1.add(login,gbc); 
        
        right1.setLayout(new GridBagLayout());right1.setBackground(new Color(255,102,0));
        gbc.anchor = GridBagConstraints.LINE_START;   //place components at start if cells
        gbc.insets = new Insets(20,20,20,20); //spacing bw cells
        gbc.fill = GridBagConstraints.BOTH; //fill extra space in cell(if comp is small)
        gbc.gridx = 0;gbc.gridy = 0;right1.add(new_user,gbc); 
        gbc.gridx = 0;gbc.gridy = 1;right1.add(forget_pass,gbc); 
        gbc.gridx = 0;gbc.gridy = 2;right1.add(exit,gbc); 
        
        //ADDING SUB-PANELS TO MAIN PANEL
        loginPage.add(top1,BorderLayout.NORTH);
        loginPage.add(centre1,BorderLayout.CENTER);
        loginPage.add(right1,BorderLayout.EAST);
                
        
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~second page~~~~~~>registration page(panel-2)        
        
        l_head = new JLabel("QUIZ CREATOR");l_head.setOpaque(true);l_head.setPreferredSize(new Dimension(600,100));l_head.setBackground(new Color(0,0,0));l_head.setForeground(new Color(255,102,0));l_head.setFont(new Font("algerian",Font.BOLD,56));l_head.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));l_head.setHorizontalAlignment(JLabel.CENTER);
        l_username = new JLabel("USERNAME");l_username.setFont(f2);
        l_first_name = new JLabel("FIRST NAME");l_first_name.setFont(f2);
        l_surname = new JLabel("SURNAME");l_surname.setFont(f2);
        l_password = new JLabel("PASSWORD");l_password.setFont(f2);
        l_ques = new JLabel("RECOVERY QUESTION");l_ques.setFont(f2);
        l_ans = new JLabel("ANSWER");l_ans.setFont(f2);
        register = new JButton("REGISTER");register.setBackground(new Color(0,0,0));register.setForeground(new Color(255,102,0));register.setFont(f1);register.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        login_page1 = new JButton("LOGIN PAGE");login_page1.setBackground(new Color(0,0,0));login_page1.setForeground(new Color(255,102,0));login_page1.setFont(f2);
        forget_pass1 = new JButton("FORGET PASSWORD?");forget_pass1.setBackground(new Color(0,0,0));forget_pass1.setForeground(new Color(255,102,0));forget_pass1.setFont(f2);
        exit1 = new JButton("EXIT");exit1.setBackground(new Color(0,0,0));exit1.setForeground(new Color(255,102,0));exit1.setFont(f2);
        username = new JTextField(20);username.setFont(f3);username.setBackground(new Color(255,204,163));
        password = new JTextField(20);password.setFont(f3);password.setBackground(new Color(255,204,163));
        first_name = new JTextField(20);first_name.setFont(f3);first_name.setBackground(new Color(255,204,163));
        surname = new JTextField(20);surname.setFont(f3);surname.setBackground(new Color(255,204,163));
        ques = new JTextField(20);ques.setFont(f3);ques.setBackground(new Color(255,204,163));
        ans = new JTextField( 20);ans.setFont(f3);ans.setBackground(new Color(255,204,163));

        Panel registerPage = new Panel();
        registerPage.setLayout(new BorderLayout());

        Panel top2 = new Panel();
        Panel centre2 = new Panel();
        Panel right2 = new Panel();

        top2.setLayout(new FlowLayout());top2.setBackground(new Color(0,0,0));
        top2.add(l_head);

        centre2.setLayout(new GridBagLayout());centre2.setBackground(new Color(255,204,153));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.LINE_START;   //place components at start if cells
        gbc2.insets = new Insets(20,20,20,20); //spacing bw cells
        gbc2.gridx = 0;gbc2.gridy = 0;centre2.add(l_username,gbc2);
        gbc2.gridx = 1;gbc2.gridy = 0;centre2.add(username,gbc2);
        gbc2.gridx = 0;gbc2.gridy = 1;centre2.add(l_password,gbc2);
        gbc2.gridx = 1;gbc2.gridy = 1;centre2.add(password,gbc2);
        gbc2.gridx = 0;gbc2.gridy = 2;centre2.add(l_first_name,gbc2);
        gbc2.gridx = 1;gbc2.gridy = 2;centre2.add(first_name,gbc2);
        gbc2.gridx = 0;gbc2.gridy = 4;centre2.add(l_surname,gbc2);
        gbc2.gridx = 1;gbc2.gridy = 4;centre2.add(surname,gbc2);
        gbc2.gridx = 0;gbc2.gridy = 5;centre2.add(l_ques,gbc2);
        gbc2.gridx = 1;gbc2.gridy = 5;centre2.add(ques,gbc2);
        gbc2.gridx = 0;gbc2.gridy = 6;centre2.add(l_ans,gbc2);
        gbc2.gridx = 1;gbc2.gridy = 6;centre2.add(ans,gbc2);
        gbc2.gridx = 1;gbc2.gridy = 7;centre2.add(register,gbc2);

        right2.setLayout(new GridBagLayout());right2.setBackground(new Color(255,102,0));
        gbc2.insets = new Insets(20,20,20,20);
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.gridx = 0;gbc2.gridy = 0;right2.add(login_page1,gbc2);
        gbc2.gridx = 0;gbc2.gridy = 1;right2.add(forget_pass1,gbc2);
        gbc2.gridx = 0;gbc2.gridy = 2;right2.add(exit1,gbc2);

        registerPage.add(top2,BorderLayout.NORTH);
        registerPage.add(centre2,BorderLayout.CENTER);
        registerPage.add(right2,BorderLayout.EAST);
        
        
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~third page~~~~~>recovery page(panel-3)
       
        r_head = new JLabel("QUIZ CREATOR");r_head.setOpaque(true);r_head.setPreferredSize(new Dimension(600,100));r_head.setBackground(new Color(0,0,0));r_head.setForeground(new Color(255,102,0));r_head.setFont(new Font("algerian",Font.BOLD,56));r_head.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));r_head.setHorizontalAlignment(JLabel.CENTER);
        l_f_userid = new JLabel("USERID");l_f_userid.setFont(f1);
        l_f_ans = new JLabel("ANSWER");l_f_ans.setFont(f1);
        login_page2 = new JButton("LOGIN PAGE");login_page2.setBackground(new Color(0,0,0));login_page2.setForeground(new Color(255,102,0));login_page2.setFont(f3);
        get_ques = new JButton("GET QUESTION");get_ques.setPreferredSize(new Dimension(200,35));get_ques.setBackground(new Color(0,0,0));get_ques.setForeground(new Color(255,102,0));get_ques.setFont(f2);get_ques.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        recover_pass = new JButton("GET PASSWORD");recover_pass.setPreferredSize(new Dimension(200,35));recover_pass.setBackground(new Color(0,0,0));recover_pass.setForeground(new Color(255,102,0));recover_pass.setFont(f2);recover_pass.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        register2 = new JButton("NEW USER?");register2.setBackground(new Color(0,0,0));register2.setForeground(new Color(255,102,0));register2.setFont(f3);
        exit2 = new JButton("EXIT");exit2.setBackground(new Color(0,0,0));exit2.setForeground(new Color(255,102,0));exit2.setFont(f3);
        f_userid = new JTextField(12);f_userid.setFont(f1);f_userid.setBackground(new Color(255,204,163));
        f_ans = new JTextField(12);f_ans.setFont(f1);f_ans.setBackground(new Color(255,204,163));

        Panel forgetPassPage = new Panel();
        forgetPassPage.setLayout(new BorderLayout());
        
        Panel top3 = new Panel();
        Panel centre3 = new Panel();
        Panel right3 = new Panel();
        
        top3.setLayout(new FlowLayout());top3.setBackground(new Color(0,0,0));
        top3.add(r_head);
        
        centre3.setLayout(new GridBagLayout());centre3.setBackground(new Color(255,204,153));
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(10,10,10,10); //spacing bw cells
        gbc3.anchor = GridBagConstraints.LINE_START;
        gbc3.gridx = 0;gbc3.gridy = 0;centre3.add(l_f_userid,gbc3);
        gbc3.gridx = 1;gbc3.gridy = 0;centre3.add(f_userid,gbc3);
        gbc3.gridx = 0;gbc3.gridy = 4;centre3.add(l_f_ans,gbc3);
        gbc3.gridx = 1;gbc3.gridy = 4;centre3.add(f_ans,gbc3);
        gbc3.anchor = GridBagConstraints.CENTER;
        gbc3.insets = new Insets(20,20,20,20); //spacing bw cells
        gbc3.gridx = 1;gbc3.gridy = 2;centre3.add(get_ques,gbc3);
        gbc3.gridx = 1;gbc3.gridy = 5;centre3.add(recover_pass,gbc3);
        
        right3.setLayout(new GridBagLayout());right3.setBackground(new Color(255,102,0));
        gbc3.anchor = GridBagConstraints.LINE_START;   //place components at start if cells
        gbc3.insets = new Insets(20,20,20,20); //spacing bw cells
        gbc3.fill = GridBagConstraints.BOTH; //fill extra space in cell(if comp is small)
        gbc3.gridx = 0;gbc3.gridy = 0;right3.add(login_page2,gbc3);
        gbc3.gridx = 0;gbc3.gridy = 1;right3.add(register2,gbc3);
        gbc3.gridx = 0;gbc3.gridy = 2;right3.add(exit2,gbc3);
        
        forgetPassPage.add(top3,BorderLayout.NORTH);
        forgetPassPage.add(centre3,BorderLayout.CENTER);
        forgetPassPage.add(right3,BorderLayout.EAST);

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~fourth page~~~~~~>home page(panel -4)
        
        h_head = new JLabel("QUIZ CREATOR");h_head.setOpaque(true);h_head.setPreferredSize(new Dimension(600,100));h_head.setBackground(new Color(0,0,0));h_head.setForeground(new Color(255,102,0));h_head.setFont(new Font("algerian",Font.BOLD,56));h_head.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));h_head.setHorizontalAlignment(JLabel.CENTER);
        choose_test = new JLabel("SELECT TEST TO ATTEMPT/EDIT/VIEW SCORECARDS");choose_test.setFont(f2);
        logout = new JButton("LOGOUT");logout.setPreferredSize(new Dimension(120,30));logout.setBackground(new Color(0,0,0));logout.setForeground(new Color(255,102,0));logout.setFont(f2);
        start = new JButton("START TEST!");start.setBackground(new Color(0,0,0));start.setForeground(new Color(255,102,0));start.setFont(f1);start.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));start.setPreferredSize(new Dimension(170,40));
        scorecards = new JButton("SCORECARDS");scorecards.setPreferredSize(new Dimension(140,30));scorecards.setBackground(new Color(0,0,0));scorecards.setForeground(new Color(255,102,0));scorecards.setFont(f2);
        create_new = new JButton("CREATE NEW");create_new.setPreferredSize(new Dimension(140,30));create_new.setBackground(new Color(0,0,0));create_new.setForeground(new Color(255,102,0));create_new.setFont(f2);
        edit = new JButton("EDIT");edit.setPreferredSize(new Dimension(140,30));edit.setBackground(new Color(0,0,0));edit.setForeground(new Color(255,102,0));edit.setFont(f2);
        exit3 = new JButton("EXIT");exit3.setPreferredSize(new Dimension(120,30));exit3.setBackground(new Color(0,0,0));exit3.setForeground(new Color(255,102,0));exit3.setFont(f2);
        tests = new Choice(); tests = createTestChoice(tests);tests.setPreferredSize(new Dimension(225,30));tests.setBackground(new Color(255,204,153)); //choices created

        Panel homePage = new Panel();
        homePage.setLayout(new BorderLayout());

        Panel top4 = new Panel();
        Panel centre4 = new Panel();
        Panel sub_centre4_1 = new Panel();
        Panel sub_centre4_2 = new Panel();
        Panel bottom4 = new Panel();

        top4.setLayout(new FlowLayout());top4.setBackground(new Color(0,0,0));
        top4.add(h_head);

        centre4.setLayout(new GridBagLayout());centre4.setBackground(new Color(255,204,153));
        sub_centre4_1.setLayout(new FlowLayout());sub_centre4_1.setBackground(new Color(255,204,153));
        sub_centre4_2.setLayout(new FlowLayout());sub_centre4_2.setBackground(new Color(255,204,153));
        
        sub_centre4_1.add(choose_test);
        sub_centre4_1.add(tests);
        
        sub_centre4_2.add(edit);
        sub_centre4_2.add(start);
        sub_centre4_2.add(scorecards);
        
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.insets = new Insets(15,10,15,10);
        gbc4.anchor = GridBagConstraints.CENTER;
        gbc4.gridx = 0;gbc4.gridy = 0;centre4.add(sub_centre4_1,gbc4);
        gbc4.gridx = 0;gbc4.gridy = 1;centre4.add(sub_centre4_2,gbc4);
        gbc4.gridx = 0;gbc4.gridy = 2;centre4.add(create_new,gbc4);
        

        bottom4.setLayout(new GridBagLayout());bottom4.setBackground(new Color(255,102,0));
        gbc4.insets=new Insets(10,15,10,15);
        gbc4.fill = GridBagConstraints.BOTH;
        gbc4.gridx = 0;gbc4.gridy = 0;bottom4.add(logout,gbc4);
        gbc4.gridx = 1;gbc4.gridy = 0;bottom4.add(exit3,gbc4);

        homePage.add(top4,BorderLayout.NORTH);
        homePage.add(centre4,BorderLayout.CENTER);
        homePage.add(bottom4,BorderLayout.SOUTH);
        
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~fifth page~~~~~~> create-quiz-1//quizDetails page(panel -5)
        
        l_testName = new JLabel("QUIZ NAME");l_testName.setFont(f2);
        l_ques_nos = new JLabel("NUMBER OF QUESTIONS");l_ques_nos.setFont(f2);
        l_open_for = new JLabel("OPEN FOR :      ");l_open_for.setFont(f2);
        l_allowed_users = new JLabel("ENTER THE USERIDs OF ALLOWED USERS BELOW : ");l_allowed_users.setFont(f2);
        go = new JButton("GO!");go.setBackground(new Color(0,0,0));go.setForeground(new Color(255,102,0));go.setFont(f1);go.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        home = new JButton("HOME");home.setBackground(Color.black);home.setForeground(new Color(255,102,0));home.setFont(f2);
        testName = new JTextField( 15);testName.setFont(f3);testName.setBackground(new Color(255,204,153));
        ques_nos = new Choice();ques_nos.add("5");ques_nos.add("6");ques_nos.add("7");ques_nos.add("8");ques_nos.add("9");ques_nos.add("10");ques_nos.add("11");ques_nos.add("12");ques_nos.add("13");ques_nos.add("14");ques_nos.add("15");ques_nos.setBackground(new Color(255,204,153));ques_nos.setPreferredSize(new Dimension(225,30));
        open_for = new Choice();open_for.add("Everyone");open_for.add("Selected Users");open_for.setBackground(new Color(255,204,153));
        allowed_users = new TextArea("",20,30,TextArea.SCROLLBARS_VERTICAL_ONLY);allowed_users.setBackground(new Color(255,204,153));

        Panel new_1_page = new Panel();
        new_1_page.setLayout(new BorderLayout());


        Panel left5 = new Panel();
        Panel right5 = new Panel();

        left5.setLayout(new GridBagLayout());left5.setBackground(new Color(255,204,153));
        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.anchor = GridBagConstraints.LINE_START;
        gbc5.insets = new Insets(10,10,10,10);
        gbc5.gridx = 0;gbc5.gridy = 0;left5.add(l_testName,gbc5);
        gbc5.gridx = 1;gbc5.gridy = 0;left5.add(testName,gbc5);
        gbc5.gridx = 0;gbc5.gridy = 1;left5.add(l_ques_nos,gbc5);
        gbc5.gridx = 1;gbc5.gridy = 1;left5.add(ques_nos,gbc5);
        gbc5.anchor = GridBagConstraints.CENTER;
        gbc5.gridx = 1;gbc5.gridy = 2;left5.add(go,gbc5);
        gbc5.gridx = 0;gbc5.gridy = 2;left5.add(home,gbc5);

        right5.setLayout(new GridBagLayout());right5.setBackground(new Color(255,102,0));
        Panel sub_right5 = new Panel();sub_right5.setLayout(new FlowLayout());right5.setBackground(new Color(255,102,0));
        sub_right5.add(l_open_for);
        sub_right5.add(open_for);
        
        gbc5.anchor = GridBagConstraints.LINE_START;
        gbc5.gridx = 0;gbc5.gridy = 0;right5.add(sub_right5,gbc5);
        gbc5.gridx = 0;gbc5.gridy = 1;right5.add(l_allowed_users,gbc5);
        gbc5.anchor = GridBagConstraints.CENTER;
        gbc5.gridx = 0;gbc5.gridy = 2;right5.add(allowed_users,gbc5);
        

        new_1_page.add(left5,BorderLayout.CENTER);
        new_1_page.add(right5,BorderLayout.EAST);
       
        
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~6th page~~~~~~> create-quiz-2//add questions page(panel -6)----------><<<<<<<looping panel>>>>>
       
        l_ques_no = new JLabel("");l_ques_no.setFont(f1); //AT RUNTIME
        l_q_id = new JLabel("QUESTION ID");l_q_id.setFont(f2);
        l_question = new JLabel("QUESTION");l_question.setFont(f2);
        l_op1 = new JLabel("OPTION 1");l_op1.setFont(f2);
        l_op2 = new JLabel("OPTION 2");l_op2.setFont(f2);
        l_op3 = new JLabel("OPTION 3");l_op3.setFont(f2);
        l_op4 = new JLabel("OPTION 4");l_op4.setFont(f2);
        l_marks = new JLabel("MARKS");l_marks.setFont(f2);
        l_correct = new JLabel("CORRECT OPTION");l_correct.setFont(f2);
        save = new JButton("SAVE");save.setBackground(Color.black);save.setForeground(new Color(255,102,0));save.setFont(f2);
        question = new JTextField( 20);question.setFont(f3);question.setBackground(new Color(255,204,153));
        q_id = new JTextField( 20);q_id.setFont(f3);q_id.setBackground(new Color(255,204,153));
        op1 = new JTextField( 20);op1.setFont(f3);op1.setBackground(new Color(255,204,153));
        op2 = new JTextField(20);op2.setFont(f3);op2.setBackground(new Color(255,204,153));
        op3 = new JTextField( 20);op3.setFont(f3);op3.setBackground(new Color(255,204,153));
        op4 = new JTextField( 20);op4.setFont(f3);op4.setBackground(new Color(255,204,153));
        marks = new JTextField(20);marks.setFont(f3);marks.setBackground(new Color(255,204,153));
        correct = new Choice(); correct = createCorrectChoice(correct);correct.setPreferredSize(new Dimension(225,30));

        Panel new_2_page = new Panel();
        new_2_page.setLayout(new BorderLayout());new_2_page.setBackground(new Color(255,204,153));
        
        Panel des_top1 = new Panel();des_top1.setBackground(new Color(0,0,0));des_top1.setPreferredSize(new Dimension(225,30));
        Panel des_bottom1 = new Panel();des_bottom1.setBackground(new Color(0,0,0));des_bottom1.setPreferredSize(new Dimension(225,30));
        
        Panel centre6 = new Panel();centre6.setLayout(new GridBagLayout());centre6.setBackground(new Color(255,204,153));
        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.anchor = GridBagConstraints.LINE_START;
        gbc6.insets = new Insets(10,20,10,20);
        gbc6.gridx = 0;gbc6.gridy = 0;centre6.add(l_ques_no,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 2;centre6.add(l_q_id,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 2;centre6.add(q_id,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 3;centre6.add(l_question,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 3;centre6.add(question,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 4;centre6.add(l_op1,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 4;centre6.add(op1,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 5;centre6.add(l_op2,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 5;centre6.add(op2,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 6;centre6.add(l_op3,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 6;centre6.add(op3,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 7;centre6.add(l_op4,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 7;centre6.add(op4,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 8;centre6.add(l_marks,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 8;centre6.add(marks,gbc6);
        gbc6.gridx = 0;gbc6.gridy = 9;centre6.add(l_correct,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 9;centre6.add(correct,gbc6);
        gbc6.gridx = 1;gbc6.gridy = 11;centre6.add(save,gbc6);

        new_2_page.add(des_top1,BorderLayout.NORTH);
        new_2_page.add(centre6,BorderLayout.CENTER);
        new_2_page.add(des_bottom1,BorderLayout.SOUTH);
        
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~7th page~~~~~~> edit-quiz-1//add questions page(panel -7)
      
        l_add_head = new JLabel("ADD YOUR QUESTION!!!");l_add_head.setFont(f1);
        l_q_id2 = new JLabel("QUESTION ID");l_q_id2.setFont(f2);
        l_question2 = new JLabel("QUESTION");l_question2.setFont(f2);
        l_op12 = new JLabel("OPTION 1"); l_op12.setFont(f2);
        l_op22 = new JLabel("OPTION 2");l_op22.setFont(f2);
        l_op32 = new JLabel("OPTION 3");l_op32.setFont(f2);
        l_op42 = new JLabel("OPTION 4");l_op42.setFont(f2);
        l_marks2 = new JLabel("MARKS");l_marks2.setFont(f2);
        l_correct2 = new JLabel("CORRECT OPTION"); l_correct2.setFont(f2);
        add2 = new JButton("ADD PAGE");add2.setBackground(new Color(0,0,0));add2.setForeground(new Color(255,102,0));add2.setFont(f2);
        delete2 = new JButton("DELETE PAGE");delete2.setBackground(new Color(0,0,0));delete2.setForeground(new Color(255,102,0));delete2.setFont(f2);
        replace2 = new JButton("REPLACE PAGE");replace2.setBackground(new Color(0,0,0));replace2.setForeground(new Color(255,102,0));replace2.setFont(f2);
        done2 = new JButton("ADD");done2.setBackground(new Color(0,0,0));done2.setForeground(new Color(255,102,0));done2.setFont(f1);done2.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        back2 = new JButton("HOME");back2.setBackground(new Color(0,0,0));back2.setForeground(new Color(255,102,0));back2.setFont(f2);
        question2 = new JTextField(20);question2.setFont(f3);question2.setBackground(new Color(255,204,153));
        q_id2 = new JTextField(20);q_id2.setFont(f3);q_id2.setBackground(new Color(255,204,153));
        op12 = new JTextField(20);op12.setFont(f3);op12.setBackground(new Color(255,204,153));
        op22 = new JTextField(20);op22.setFont(f3);op22.setBackground(new Color(255,204,153));
        op32 = new JTextField(20);op32.setFont(f3);op32.setBackground(new Color(255,204,153));
        op42 = new JTextField(20);op42.setFont(f3);op42.setBackground(new Color(255,204,153));
        marks2 = new JTextField(20);marks2.setFont(f3);marks2.setBackground(new Color(255,204,153));
        correct2 = new Choice();correct2 = createCorrectChoice(correct2);correct2.setPreferredSize(new Dimension(225,30));correct2.setBackground(new Color(255,204,153));


        Panel addPage = new Panel();
        addPage.setLayout(new BorderLayout());

        Panel centre7=new Panel();
        Panel right7=new Panel();

        centre7.setLayout(new GridBagLayout());addPage.setBackground(Color.ORANGE);centre7.setBackground(new Color(255,204,153));
        GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.insets=new Insets(10,10,10,10);
        gbc7.anchor = GridBagConstraints.LINE_START;
        gbc7.gridx = 0;gbc7.gridy = 0;centre7.add(l_add_head,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 1;centre7.add(l_q_id2,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 1;centre7.add(q_id2,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 2;centre7.add(l_question2,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 2;centre7.add(question2,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 3;centre7.add(l_op12,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 3;centre7.add(op12,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 4;centre7.add(l_op22,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 4;centre7.add(op22,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 5;centre7.add(l_op32,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 5;centre7.add(op32,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 6;centre7.add(l_op42,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 6;centre7.add(op42,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 7;centre7.add(l_marks2,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 7;centre7.add(marks2,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 8;centre7.add(l_correct2,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 8;centre7.add(correct2,gbc7);
        gbc7.gridx = 1;gbc7.gridy = 9;centre7.add(done2,gbc7);



        right7.setLayout(new GridBagLayout());right7.setBackground(new Color(255,102,0));
        gbc7.anchor = GridBagConstraints.LINE_START;
        gbc7.insets = new Insets(10,10,10,10); //spacing bw cells
        gbc7.fill = GridBagConstraints.BOTH; //fill extra space in cell(if comp is small)
        gbc7.gridx = 0;gbc7.gridy = 0;right7.add(add2,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 1;right7.add(replace2,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 2;right7.add(delete2,gbc7);
        gbc7.gridx = 0;gbc7.gridy = 3;right7.add(back2,gbc7);

        addPage.add(centre7,BorderLayout.CENTER);
        addPage.add(right7,BorderLayout.EAST);
    
       
       
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~8th page~~~~~~> edit-quiz-2//del questions page(panel -8)
        
        l_del_head = new JLabel("DELETE QUESTION!!!");l_del_head.setFont(f1);
        l_q_id3 = new JLabel("QUESTION ID(TO BE DELETED)");l_q_id3.setFont(f2);
        add3 = new JButton("ADD PAGE");add3.setBackground(new Color(0,0,0));add3.setForeground(new Color(255,102,0));add3.setFont(f2);
        delete3 = new JButton("DELETE PAGE");delete3.setBackground(new Color(0,0,0));delete3.setForeground(new Color(255,102,0));delete3.setFont(f2);
        replace3 = new JButton("REPLACE PAGE");replace3.setBackground(new Color(0,0,0));replace3.setForeground(new Color(255,102,0));replace3.setFont(f2);
        done3 = new JButton("DELETE");done3.setBackground(new Color(0,0,0));done3.setForeground(new Color(255,102,0));done3.setFont(f1);done3.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        back3 = new JButton("HOME");back3.setBackground(new Color(0,0,0));back3.setForeground(new Color(255,102,0));back3.setFont(f2);
        q_id3 = new JTextField(20);q_id3.setFont(f3);q_id3.setBackground(new Color(255,204,153));

        Panel delPage = new Panel();
        delPage.setLayout(new BorderLayout());

        Panel centre8 = new Panel();
        Panel right8 = new Panel();


        centre8.setLayout(new GridBagLayout());centre8.setBackground(new Color(255,204,153));
        GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.insets = new Insets(10,10,10,10);
        gbc8.anchor = GridBagConstraints.LINE_START;
        gbc8.gridx = 0;gbc8.gridy = 0;centre8.add(l_del_head,gbc8);
        gbc8.gridx = 0;gbc8.gridy = 1;centre8.add(l_q_id3,gbc8);
        gbc8.gridx = 1;gbc8.gridy = 1;centre8.add(q_id3,gbc8);
        gbc8.gridx = 1;gbc8.gridy = 2;centre8.add(done3,gbc8);

        right8.setLayout(new GridBagLayout());right8.setBackground(new Color(255,102,0));
        gbc8.anchor = GridBagConstraints.LINE_START;
        gbc8.insets = new Insets(10,10,10,10); //spacing bw cells
        gbc8.fill = GridBagConstraints.BOTH; //fill extra space in cell(if comp is small)
        gbc8.gridx = 0;gbc8.gridy = 0;right8.add(add3,gbc8);
        gbc8.gridx = 0;gbc8.gridy = 1;right8.add(replace3,gbc8);
        gbc8.gridx = 0;gbc8.gridy = 2;right8.add(delete3,gbc8);
        gbc8.gridx = 0;gbc8.gridy = 3;right8.add(back3,gbc8);


        delPage.add(centre8,BorderLayout.CENTER);
        delPage.add(right8,BorderLayout.EAST);
        
        
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~9th page~~~~~~> edit-quiz-3//replace questions page(panel -9)
     
        l_rep_head = new JLabel("REPLACE QUESTION!!!");l_rep_head.setFont(f1);
        l_q_id4 = new JLabel("QUESTION ID(TO BE REPLACED)");l_q_id4.setFont(f2);
        l_question4 = new JLabel("QUESTION");l_question4.setFont(f2);
        l_op14 = new JLabel("OPTION 1");l_op14.setFont(f2);
        l_op24 = new JLabel("OPTION 2");l_op24.setFont(f2);
        l_op34 = new JLabel("OPTION 3");l_op34.setFont(f2);
        l_op44 = new JLabel("OPTION 4");l_op44.setFont(f2);
        l_marks4 = new JLabel("MARKS");l_marks4.setFont(f2);
        l_correct4 = new JLabel("CORRECT OPTION");l_correct4.setFont(f2);
        add4 = new JButton("ADD PAGE");add4.setBackground(new Color(0,0,0));add4.setForeground(new Color(255,102,0));add4.setFont(f2);
        delete4 = new JButton("DELETE PAGE");delete4.setBackground(new Color(0,0,0));delete4.setForeground(new Color(255,102,0));delete4.setFont(f2);
        done4 = new JButton("REPLACE");done4.setBackground(new Color(0,0,0));done4.setForeground(new Color(255,102,0));done4.setFont(f1);done4.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW));
        replace4 = new JButton("REPLACE PAGE");replace4.setBackground(new Color(0,0,0));replace4.setForeground(new Color(255,102,0));replace4.setFont(f2);
        back4 = new JButton("HOME");back4.setBackground(new Color(0,0,0));back4.setForeground(new Color(255,102,0));back4.setFont(f2);
        question4 = new JTextField(20);question4.setFont(f3);question4.setBackground(new Color(255,204,153));
        q_id4 = new JTextField(20);q_id4.setFont(f3);q_id4.setBackground(new Color(255,204,153));
        op14 = new JTextField(20);op14.setFont(f3);op14.setBackground(new Color(255,204,153));
        op24 = new JTextField(20);op24.setFont(f3);op24.setBackground(new Color(255,204,153));
        op34 = new JTextField(20);op34.setFont(f3);op34.setBackground(new Color(255,204,153));
        op44 = new JTextField(20);op44.setFont(f3);op44.setBackground(new Color(255,204,153));
        marks4 = new JTextField(20);marks4.setFont(f3);marks4.setBackground(new Color(255,204,153));
        correct4 = new Choice();correct4 = createCorrectChoice(correct4);correct4.setPreferredSize(new Dimension(225,30));

        Panel replacePage = new Panel();
        replacePage.setLayout(new BorderLayout());

        Panel centre9 = new Panel();
        Panel right9 = new Panel();

        centre9.setLayout(new GridBagLayout());centre9.setBackground(new Color(255,204,153));
        GridBagConstraints gbc9 = new GridBagConstraints();
        gbc9.insets = new Insets(10,10,10,10);
        gbc9.anchor = GridBagConstraints.LINE_START;
        gbc9.gridx = 0;gbc9.gridy = 0;centre9.add(l_rep_head,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 1;centre9.add(l_q_id4,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 1;centre9.add(q_id4,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 2;centre9.add(l_question4,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 2;centre9.add(question4,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 3;centre9.add(l_op14,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 3;centre9.add(op14,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 4;centre9.add(l_op24,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 4;centre9.add(op24,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 5;centre9.add(l_op34,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 5;centre9.add(op34,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 6;centre9.add(l_op44,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 6;centre9.add(op44,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 7;centre9.add(l_marks4,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 7;centre9.add(marks4,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 8;centre9.add(l_correct4,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 8;centre9.add(correct4,gbc9);
        gbc9.gridx = 1;gbc9.gridy = 9;centre9.add(done4,gbc9);


        right9.setLayout(new GridBagLayout());right9.setBackground(new Color(255,102,0));
        gbc9.anchor = GridBagConstraints.LINE_START;
        gbc9.insets = new Insets(10,10,10,10); //spacing bw cells
        gbc9.fill = GridBagConstraints.BOTH; //fill extra space in cell(if comp is small)
        gbc9.gridx = 0;gbc9.gridy = 0;right9.add(add4,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 1;right9.add(replace4,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 2;right9.add(delete4,gbc9);
        gbc9.gridx = 0;gbc9.gridy = 3;right9.add(back4,gbc9);


        replacePage.add(centre9,BorderLayout.CENTER);
        replacePage.add(right9,BorderLayout.EAST);

        
        

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~10th page~~~~~~> quiz condutor-1//exam page(panel -10)<<<<<<<<looping page>>>>>>>>>>
        
       
        l_showQue = new JLabel(""); l_showQue.setFont(f2); //at runtime
        l_qno = new JLabel(""); l_qno.setFont(f2); //at runtime
        JLabel l_spacelabel = new JLabel("                           ");
        l_showMarks= new JLabel("");l_showMarks.setFont(f2); //at runtime
        submit = new JButton("submit");submit.setBackground(new Color(0,0,0));submit.setForeground(new Color(255,102,0));submit.setFont(f2);
        gp1 = new CheckboxGroup(); cb1 = new Checkbox("op1", gp1,true);cb2 = new Checkbox("op2", gp1, false);cb3 = new Checkbox("op3", gp1, false);cb4 = new Checkbox("op4", gp1, false);
        cb1.setFont(f3);cb2.setFont(f3);cb3.setFont(f3);cb4.setFont(f3);
        
        Panel qc_2Page = new Panel();
        qc_2Page.setLayout(new BorderLayout());qc_2Page.setBackground(new Color(255,204,153));
        
        Panel des_top2 = new Panel();des_top2.setBackground(new Color(0,0,0));des_top2.setPreferredSize(new Dimension(225,30));
        Panel des_bottom2 = new Panel();des_bottom2.setBackground(new Color(0,0,0));des_bottom2.setPreferredSize(new Dimension(225,30));
        
        Panel centre10 = new Panel();centre10.setLayout(new GridBagLayout());centre10.setBackground(new Color(255,204,153));
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.insets = new Insets(10,10,10,10);
        gbc11.anchor = GridBagConstraints.LINE_START;
        gbc11.gridx = 1;gbc11.gridy = 0;centre10.add(l_showQue,gbc11);
        gbc11.gridx = 0;gbc11.gridy = 0;centre10.add(l_qno,gbc11);
        gbc11.gridx = 2;gbc11.gridy = 0;centre10.add(l_spacelabel,gbc11);
        gbc11.gridx = 3;gbc11.gridy = 0;centre10.add(l_showMarks,gbc11);
        gbc11.gridx = 1;gbc11.gridy = 1;centre10.add(cb1,gbc11);
        gbc11.gridx = 1;gbc11.gridy = 2;centre10.add(cb2,gbc11);
        gbc11.gridx = 1;gbc11.gridy = 3;centre10.add(cb3,gbc11);
        gbc11.gridx = 1;gbc11.gridy = 4;centre10.add(cb4,gbc11);
        gbc11.anchor = GridBagConstraints.CENTER;
        gbc11.gridx = 1;gbc11.gridy = 5;centre10.add(submit,gbc11);

        qc_2Page.add(des_top2,BorderLayout.NORTH);
        qc_2Page.add(centre10,BorderLayout.CENTER);
        qc_2Page.add(des_bottom2,BorderLayout.SOUTH);

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~11th page~~~~~~> quiz conductor-2//result(panel -11)
        l_res_head = new JLabel("YOUR RESULT...");l_res_head.setFont(f1);
        final_home = new JButton("HOME");final_home.setFont(f3);final_home.setBackground(new Color(0,0,0));final_home.setForeground(new Color(255,102,0));
        //at runtime
        l_totalQues = new JLabel("");l_totalQues.setFont(f3); 
        l_corAns = new JLabel("");l_corAns.setFont(f3);
        l_inAns = new JLabel("");l_inAns.setFont(f3);
        l_marksObt = new JLabel("");l_marksObt.setFont(f3);
        l_per = new JLabel("");l_per.setFont(f3);
        
        Panel qc_3Page = new Panel();
        qc_3Page.setLayout(new BorderLayout());qc_3Page.setBackground(new Color(255,204,153));
        
        Panel des_left1 = new Panel();des_left1.setBackground(new Color(0,0,0));des_left1.setPreferredSize(new Dimension(30,500));
        Panel des_right1 = new Panel();des_right1.setBackground(new Color(0,0,0));des_right1.setPreferredSize(new Dimension(30,500));
        
        Panel centre11 = new Panel();centre11.setLayout(new GridBagLayout());centre11.setBackground(new Color(255,204,153));
        GridBagConstraints gbc12 = new GridBagConstraints();
        gbc12.insets = new Insets(10,10,10,10); //spacing bw cells
        gbc12.fill = GridBagConstraints.BOTH; //fill extra space in cell(if comp is small)
        gbc12.anchor = GridBagConstraints.LINE_START;
        gbc12.gridx = 0;gbc12.gridy = 0;centre11.add(l_res_head,gbc12);
        gbc12.gridx = 0;gbc12.gridy = 1;centre11.add(l_totalQues,gbc12);
        gbc12.gridx = 0;gbc12.gridy = 2;centre11.add(l_corAns,gbc12);
        gbc12.gridx = 0;gbc12.gridy = 3;centre11.add(l_inAns,gbc12);
        gbc12.gridx = 0;gbc12.gridy = 4;centre11.add(l_marksObt,gbc12);
        gbc12.gridx = 0;gbc12.gridy = 5;centre11.add(l_per,gbc12);
        gbc12.anchor = GridBagConstraints.CENTER;
        gbc12.gridx = 0;gbc12.gridy = 6;centre11.add(final_home,gbc12);
        
        qc_3Page.add(des_left1,BorderLayout.WEST);
        qc_3Page.add(centre11,BorderLayout.CENTER);
        qc_3Page.add(des_right1,BorderLayout.EAST);         



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~12th page~~~~~~> highscore tables(panel -12)
        l_score_head = new JLabel("SCORECARD  :  ");l_score_head.setFont(f1);
        l_test2 = new JLabel("");l_test2.setFont(f1);  //runtime (test name)
        score_home = new JButton("HOME");score_home.setFont(f3);score_home.setBackground(new Color(0,0,0));score_home.setForeground(new Color(255,102,0));
        tableModel = new DefaultTableModel(0,3);  // specify number of columns
    	scoresheet = new JTable(tableModel);scoresheet.setFont(f2);scoresheet.setGridColor(new Color(255,204,153));scoresheet.setRowHeight(40);scoresheet.setRowMargin(20);
    	tableHeader = scoresheet.getTableHeader();

    	
        Panel sc_Page = new Panel();
        sc_Page.setLayout(new BorderLayout());sc_Page.setBackground(new Color(255,204,153));
        
        Panel des_left2 = new Panel();des_left2.setBackground(new Color(0,0,0));des_left2.setPreferredSize(new Dimension(30,500));
        Panel des_right2 = new Panel();des_right2.setBackground(new Color(0,0,0));des_right2.setPreferredSize(new Dimension(30,500));
        
        Panel centre12 = new Panel();centre12.setLayout(new GridBagLayout());centre12.setBackground(new Color(255,204,153));
        Panel sub_centre12 = new Panel();sub_centre12.setLayout(new FlowLayout());sub_centre12.setBackground(new Color(255,204,153));
        sub_centre12.add(l_score_head);
        sub_centre12.add(l_test2);
        
        GridBagConstraints gbc13 = new GridBagConstraints();
        gbc13.insets = new Insets(10,10,10,10); //spacing bw cells
        gbc13.anchor = GridBagConstraints.CENTER;
        gbc13.gridx = 0;gbc13.gridy = 0;centre12.add(sub_centre12,gbc13);
        gbc13.gridx = 0;gbc13.gridy = 1;centre12.add(scoresheet,gbc13);
        gbc13.gridx = 0;gbc13.gridy = 2;centre12.add(tableHeader,gbc13);
        gbc13.gridx = 0;gbc13.gridy = 3;centre12.add(scoresheet, gbc13);
        gbc13.anchor = GridBagConstraints.CENTER;
        gbc13.gridx = 0;gbc13.gridy = 4;centre12.add(score_home,gbc13);
        
        JScrollPane centre12_scroll = new JScrollPane(centre12);
        sc_Page.add(des_left2,BorderLayout.WEST);
        sc_Page.add(centre12_scroll,BorderLayout.CENTER);
        sc_Page.add(des_right2,BorderLayout.EAST);
        
//~~~~~~~~~|||||||||||||||||||||||END OF PANELS|||||||||||||||||||||||~~~~~~~~~~~~~~

        //~~~~~~setting osCards (deck panel) to use CardLayout()
        cardLO = new CardLayout();
        osCards = new Panel();
        osCards.setLayout(cardLO);
        
        //~~~~~adding panels to the deck (osCards)
        osCards.add(loginPage, "loginPage");
        osCards.add(registerPage, "registerPage");
        osCards.add(forgetPassPage, "forgetPassPage");
        osCards.add(homePage, "homePage");
        osCards.add(new_1_page, "create_new-1");
        osCards.add(new_2_page, "create_new-2");
        osCards.add(addPage, "addPage");
        osCards.add(delPage, "delPage");
        osCards.add(replacePage, "replacePage");
        osCards.add(qc_2Page, "qc_secondPage");
        osCards.add(qc_3Page, "qc_thirdPage");
        osCards.add(sc_Page, "scorecard");
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        //~~~~~add cards to main Frame
        add(osCards);
        //~~~~~~add(register) Listeners to panels components(buttons,choices,radio buttons)
        exit3.addActionListener(this);exit.addActionListener(this);forget_pass1.addActionListener(this);exit1.addActionListener(this);exit2.addActionListener(this);register2.addActionListener(this);submit.addActionListener(this);final_home.addActionListener(this);score_home.addActionListener(this);scorecards.addActionListener(this);login.addActionListener(this);new_user.addActionListener(this);forget_pass.addActionListener(this);register.addActionListener(this);login_page1.addActionListener(this);login_page2.addActionListener(this);get_ques.addActionListener(this);recover_pass.addActionListener(this);logout.addActionListener(this);start.addActionListener(this);create_new.addActionListener(this);edit.addActionListener(this);go.addActionListener(this);home.addActionListener(this);ques_nos.addItemListener(this);save.addActionListener(this);correct.addItemListener(this);tests.addItemListener(this);add2.addActionListener(this);delete2.addActionListener(this);replace2.addActionListener(this);done2.addActionListener(this);back2.addActionListener(this);add3.addActionListener(this);delete3.addActionListener(this);replace3.addActionListener(this);done3.addActionListener(this);back3.addActionListener(this);add4.addActionListener(this);delete4.addActionListener(this);replace4.addActionListener(this);done4.addActionListener(this);back4.addActionListener(this);correct2.addItemListener(this);correct4.addItemListener(this);cb1.addItemListener(this);cb2.addItemListener(this);cb3.addItemListener(this);cb4.addItemListener(this);
        tableModel.addTableModelListener(this);
        //~~~~anonymous inner class for exit
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ae) {
                System.exit(101);
            }
        });
    } //constructor closed
    
    //~~~~~~handling button pressed events
    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (looping_panels == true) {
            if (str == "SAVE" && (panels_count < loop_nos)) { //to add questions
                if(QuizCreator.AddQues(op1.getText(), op2.getText(), op3.getText(), op4.getText(), question.getText(), q_id.getText(), correct.getSelectedIndex() + 1, marks.getText()) == true){
                    JOptionPane.showMessageDialog(this,"QUESTION ADDED SUCCESSFULLY!","",JOptionPane.INFORMATION_MESSAGE);
                    l_ques_no.setText("QUESTION " + (panels_count + 2) + ":");
                    cardLO.show(osCards, "create_new-2");
                    if(panels_count == (loop_nos-1)){
                        panels_count = 0; //reset
                        looping_panels = false; //reset
                        loop_nos = 0;
                        JOptionPane.showMessageDialog(this,"QUIZ CREATED SUCCESSFULLY!","",JOptionPane.INFORMATION_MESSAGE);
                        tests = createTestChoice(tests); //choices created(RESCANNING)
                        cardLO.show(osCards, "homePage"); //---->save and move to home
                    }
                    panels_count++;
                }else{
                    JOptionPane.showMessageDialog(this,QuizCreator.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                }
            }else if( (str == "submit") && (panels_count < loop_nos) ){
                //CALCULATING MARKS
                String ob = (gp1.getSelectedCheckbox()).getLabel();
                if(ob.equals(cb1.getLabel()) == true){
                    QuizConductor.calcMarks(1); 
                }
                else if(ob.equals(cb2.getLabel()) == true){
                    QuizConductor.calcMarks(2); 
                }
                else if(ob.equals(cb3.getLabel()) == true){
                    QuizConductor.calcMarks(3);
                }
                else if(ob.equals(cb4.getLabel()) == true){
                    QuizConductor.calcMarks(4);
                }
                else{//not selected--->left
                    QuizConductor.calcMarks(0);
                } 
                //setting up the next page(displaying questions)
                QuizConductor q1 = new QuizConductor(tests.getSelectedItem());
                l_showQue.setText(q1.getQuestionTitle());
                l_qno.setText("QUESTION " + (panels_count + 2) + " : ");
                l_showMarks.setText("MARKS : " + q1.getMarks());
                cb1.setLabel(q1.getOp1());
                cb2.setLabel(q1.getOp2());
                cb3.setLabel(q1.getOp3()); 
                cb4.setLabel(q1.getOp4()); 
                cardLO.show(osCards,"qc_secondPage");
                if(panels_count == (loop_nos - 1) ){
                    JOptionPane.showMessageDialog(this,"QUIZ COMPLETED!!","DONE",JOptionPane.INFORMATION_MESSAGE);
                    
                    l_totalQues.setText("TOTAL QUESTIONS     :   " + loop_nos);
                    l_corAns.setText("CORRECT                     :   " + QuizConductor.correctAns);
                    l_inAns.setText("INCORRECT                  :   " + (loop_nos - (QuizConductor.correctAns)));
                    l_marksObt.setText("MARKS OBTAINED       :   " + QuizConductor.getObtainedMarks() + "/" + QuizConductor.getTotalMarks());
                    l_per.setText("PERCENTAGE              :   " + ( (QuizConductor.getObtainedMarks()/QuizConductor.getTotalMarks())* 100) + " %");
                    
                    cardLO.show(osCards,"qc_thirdPage");
                    //SAVING RESULTS
                    lb = new leaderboardmain (LoginAndRegistration.userid, tests.getSelectedItem(), QuizConductor.getObtainedMarks(), QuizConductor.getTotalMarks());
                    lb.setAndSaveQuizScore();
                    lb.sortfile();
                    //.....
                    looping_panels = false;  //get out of loop--->result page
                    QuizConductor.resetMarks();  //reset the obtained marks
                    panels_count = 0;loop_nos = 0;    //reseting both of them   <<<<<looping ends>>>>>>>>
                }
                panels_count++;
            }
        } else {
            switch (str) {
                case "LOGIN": {
                    if (LoginAndRegistration.login(userid.getText(), login_pass.getText()) == true) {
                        cardLO.show(osCards, "homePage");
                        JOptionPane.showMessageDialog(this,"Welcome " + LoginAndRegistration.fullname + "!","",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        cardLO.show(osCards, "loginPage");
                        JOptionPane.showMessageDialog(this,LoginAndRegistration.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "LOGIN PAGE": {
                    cardLO.show(osCards, "loginPage");
                }
                break;
                case "NEW USER?": {
                    cardLO.show(osCards, "registerPage");
                }
                break;
                case "FORGET PASSWORD?": {
                    cardLO.show(osCards, "forgetPassPage");
                }
                break;
                case "EXIT": {
                    System.exit(101);
                }
                break;
                case "REGISTER": {
                    if (LoginAndRegistration.registration(username.getText(), password.getText(), first_name.getText(), surname.getText(), ques.getText(), ans.getText()) == true) {
                        cardLO.show(osCards, "loginPage");
                        JOptionPane.showMessageDialog(this,"registerd successfully","",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        cardLO.show(osCards, "registerPage");
                        JOptionPane.showMessageDialog(this,LoginAndRegistration.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "GET QUESTION": {
                    if (LoginAndRegistration.getRecoveryQues(f_userid.getText()) == true) {
                        JOptionPane.showMessageDialog(this,LoginAndRegistration.ques,"RECOVERY QUESTION : ",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        cardLO.show(osCards, "forgetPassPage");
                        JOptionPane.showMessageDialog(this,LoginAndRegistration.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "GET PASSWORD": {
                    if (LoginAndRegistration.getRecoveryPassword(f_ans.getText()) == true) {
                        JOptionPane.showMessageDialog(this,"your password : " + LoginAndRegistration.pass,"Password",JOptionPane.INFORMATION_MESSAGE);
                        cardLO.show(osCards, "loginPage");
                    } else {
                        cardLO.show(osCards, "forgetPassPage");
                        JOptionPane.showMessageDialog(this,LoginAndRegistration.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
                case "START TEST!": {
                    if(AdminVerification.checkAllowedUser(tests.getSelectedItem()) == true){
                        QuizConductor.initializeAry();
                        panels_count = 0; //reseting(to prevent any error)
                        loop_nos = QuizConductor.getNoOfQuestions(tests.getSelectedItem()); //No of questions
                        JOptionPane.showMessageDialog(this,"GOOD LUCK!!!" + "\ntest : " + tests.getSelectedItem() + "\nquestions : " + loop_nos + "\nmax marks : " + QuizConductor.getTotalMarks(),"TEST DETAILS",JOptionPane.INFORMATION_MESSAGE);
                        
                        QuizConductor q1 = new QuizConductor(tests.getSelectedItem());
                        //SETTING up the nEXt PANEL (displaYing Questions)
                        l_showQue.setText(q1.getQuestionTitle());
                        l_qno.setText("QUESTION 1 : ");
                        l_showMarks.setText("MARKS : " + q1.getMarks());
                        cb1.setLabel(q1.getOp1());
                        cb2.setLabel(q1.getOp2());
                        cb3.setLabel(q1.getOp3());
                        cb4.setLabel(q1.getOp4());
                        looping_panels = true;
                    
                        cardLO.show(osCards, "qc_secondPage");
                    }else{
                        JOptionPane.showMessageDialog(this,"YOU DON'T HAVE ACCESS TO THIS TEST!!!","ACCESS DENIED!",JOptionPane.ERROR_MESSAGE);
                    }
                  }
                break;
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>      
                case "CREATE NEW": {
                    cardLO.show(osCards, "create_new-1");
                }
                break;
                case "GO!": {
                    if(QuizCreator.QuizTitle(testName.getText()) == true){ //quiz title
                        if(open_for.getSelectedIndex() == 1) QuizCreator.saveAllowedUsers(allowed_users.getText());
                        panels_count = 0; // reseting(to prevent any errro)
                        loop_nos = ques_nos.getSelectedIndex() + 5; //amount of ques
                        looping_panels = true; //looping a panel (loop_nos-1) times (1st panel not included)
                        l_ques_no.setText("QUESTION 1 : ");
                        cardLO.show(osCards, "create_new-2");
                    }
                    else{//if quiz name already exists
                        cardLO.show(osCards, "create_new-1");
                        JOptionPane.showMessageDialog(this,QuizCreator.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
                case "EDIT": {
                    if(AdminVerification.checkAdmin(tests.getSelectedItem()) == true){
                        QuizCreator.setFilename(tests.getSelectedItem()); //file choosen
                        cardLO.show(osCards, "addPage");
                    }else{
                        JOptionPane.showMessageDialog(this,"ERROR : EITHER YOU ARE TRYING TO EDIT A QUIZ NOT CREATED BY YOU OR A PRELOADED ONE!!!","ACCESS DENIED!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "ADD PAGE": {
                    cardLO.show(osCards, "addPage");
                }
                break;
                case "DELETE PAGE": {
                    cardLO.show(osCards, "delPage");
                }
                break;
                case "REPLACE PAGE": {
                    cardLO.show(osCards, "replacePage");
                }
                break;
                case "ADD" : {
                    if(QuizCreator.AddQues(op12.getText(), op22.getText(), op32.getText(), op42.getText(), question2.getText(), q_id2.getText(), correct2.getSelectedIndex() + 1, marks2.getText()) == true){
                        JOptionPane.showMessageDialog(this,"QUESTION ADDED SUCCESSFULLY!","",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,QuizCreator.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "DELETE" : {
                    if(QuizCreator.DelQues(q_id3.getText()) == true){
                        JOptionPane.showMessageDialog(this,"QUESTION DELETED SUCCESSFULLY!","",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,QuizCreator.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                case "REPLACE" :{
                    if((QuizCreator.ReplaceQues(op14.getText(), op24.getText(), op34.getText(), op44.getText(), question4.getText(), q_id4.getText(), correct4.getSelectedIndex()+1, marks4.getText()) == true)){
                        JOptionPane.showMessageDialog(this,"QUESTION REPLACED SUCCESSFULLY!","",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,QuizCreator.store_exc,"ERROR!",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
                case "SCORECARDS" : {
                	String columns[] =  { "    User ID    "," Total Marks "," Obtained " };
                	tableModel.setColumnIdentifiers(columns);
                	
                        scoresheet.setModel(tableModel);
                	String line; int lineCount=0;
                	BufferedReader reader;
                	DynamicDirLocator ob = new DynamicDirLocator();
                	String fileName = ob.getFullPath(tests.getSelectedItem(),3);
                	    try{       
                	        reader = new BufferedReader(new FileReader(fileName));
                	        while((line = reader.readLine()) != null) 
                	        {
                	           tableModel.addRow(line.split("_"));
                	           lineCount++;
                	        }
                                l_test2.setText(tests.getSelectedItem());
                	        cardLO.show(osCards, "scorecard");
                	        reader.close();

 
                	     }
                	    catch(IOException e){
                	        JOptionPane.showMessageDialog(this, "UNABLE TO SHOW SCORECARD!!!","ERROR!",JOptionPane.ERROR_MESSAGE);
                	        e.printStackTrace();
                	    }
                	   
                }
                break;
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>      
                case "HOME": {
                    tests = createTestChoice(tests); //choices created
                    cardLO.show(osCards, "homePage");
                    tableModel.setRowCount(0);  //RESETING TABle
                }
                break;
                default: { //log out
                    JOptionPane.showMessageDialog(this,"LOGGED OUT SUCCESSFULLY!","GOOD BYE!",JOptionPane.PLAIN_MESSAGE);
                    cardLO.show(osCards, "loginPage");
                }

            }//switch ends
        }//else ends
    }//handler ends

    public void itemStateChanged(ItemEvent ie) { //getSelectedIndex() is defined in this
    }//handler ends


    public Choice createCorrectChoice(Choice correct) {
        for (int i = 1; i < 5; i++) {
            correct.add("" + i);
        }
        return correct;
    }

    public Choice createTestChoice(Choice Tests) {
        Tests.removeAll();
        DynamicDirLocator ob= new DynamicDirLocator();
        String read_title = "";
        boolean store = true; //true after encountering ',' and false at '~'
        FileInputStream open = null;
        //FileInputStream open2 = null;
        try {
            open = new FileInputStream(ob.getFullPath("PreTests_name_and_filenames", 2)); //opening the filE
            int i; //to store characters  
            for (int j = 0; j < 2; j++) {
                do {
                    i = open.read(); //reading characters (integral values)
                    if ((char) i == '~') {
                        store = false;
                        Tests.add(read_title);
                        read_title = read_title.replace(read_title, "");
                    } else if ((char) i == ',') {
                        store = true;
                    } else if (store == true) {
                        read_title = read_title + (char) i;  //storing the username
                    }
                } while (i != -1); //end of file
                if (j == 0) {
                    read_title = read_title.replace(read_title, "");
                    open = new FileInputStream(ob.getFullPath("UserTests_name_and_filenames", 2));
                }
            }
        }//try
        catch (FileNotFoundException e) {
            System.out.println("ERROR : FILE WITH USER DETAILS NOT FOUND(method: createTestChoice)!!! ");
        } catch (IOException e) {
            System.out.println("ERROR : UNABLE TO READ THE FILE(method: createTestChoice)!!!");
        } finally {
            try {
                if (open != null) {
                    open.close(); //file is opened ===>close
                }
            } catch (IOException e) {
                System.out.println("ERROR : UNABLE TO CLOSE FILE(method: createTestChoice)!!!");
            }
        }
        return Tests; //not matched
    }
    
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                DynamicDirLocator.workingDir();   //SAVES THE WORKING DIRECTORY OF PROGRAM
                MainUserInterface ob = new MainUserInterface();
                //Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();  //size of screen(system dependent)
                //ob.setSize(screen.width,screen.height); 
                ob.setSize(1000,650);
                ob.setResizable(false);
                ob.setVisible(true);
                ob.setTitle("test manager");
        
            }
        });
        
    }
    
    @Override
    public void tableChanged(TableModelEvent te) {

    }
}
