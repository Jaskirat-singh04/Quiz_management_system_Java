/* ||||| FACILITATES THE DYNAMIC AND SYSTEM INDEPENDENT USE OF DATABASE FILES  |||||| 
 * USE THIS CLASS TO DYNAMICALLY SPECIFY THE FILE PATHS.
              |||||| HOW TO USE |||||
    > RUN THE STATIC METHOD workingDir() ONLY ONCE AT THE START OF PROGRAM,TO GET THE ROOT DIRECTORY
    > FOLLOWING THAT INSTANTIATE AN OBJECT AND CALL THE METHOD getFullPath() 
 */
package ui;


public class DynamicDirLocator {

    private final String db_files = "\\src\\db_files\\";
    private final String tests = "\\src\\db_files\\tests\\";
    private final String scorecards = "\\src\\db_files\\scorecards\\";
    private static String rootDir = "";

    public static void workingDir() {
        rootDir = System.getProperty("user.dir");
    }

    public String getFullPath(String filename, int folder) { //1.db_files , 2.tests , 3.scorecards
        String fullPath = "";
        switch (folder) {
            case 1:
                fullPath = rootDir + db_files + filename + ".txt";
                break;
            case 2:
                fullPath = rootDir + tests + filename + ".txt";
                break;
            case 3:
                fullPath = rootDir + scorecards + filename + ".txt";
                break;
        }
        return fullPath;
    }
   
}
