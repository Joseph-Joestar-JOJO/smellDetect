package com.ruoyi.utility;


import com.ruoyi.system.domain.Projcommit;
import it.unisa.bsic.bean.Author;
import it.unisa.bsic.bean.Change;
import it.unisa.bsic.bean.Commit;
import it.unisa.bsic.bean.Committer;
import it.unisa.bsic.bean.FileBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Git {

    public static String generateLog(File directory, boolean masterOnly){
        String log = "";
        if(masterOnly){
            log = generateLogFileTagOnly(directory);
        } else {
            log = generateLogFile(directory);
        }
        return log;
    }

    public static List<Commit> extractCommit(File directory, boolean masterOnly){
        String log = "";
        if(masterOnly){
            log = generateLogFileTagOnly(directory);
        } else {
            log = generateLogFile(directory);
        }

        List<Commit> commits = generateCommits(directory, log);

        return commits;
    }

    public static List<Projcommit> extractCommitSimple(File directory, boolean TagOnly, int projid, String projname){
        String log = "";
        if(TagOnly){
            log = generateLogFileTagOnly(directory);
        } else {
            log = generateLogFile(directory);
        }
        List<Projcommit> commits = generateCommitsSimple(directory, log, projid, projname);
        return commits;
    }

    public static List<Commit> extractProxyCommit(File directory, boolean TagOnly){
        String log = "";
        if(TagOnly){
            log = generateLogFileTagOnly(directory);
        } else {
            log = generateLogFile(directory);
        }
        List<Commit> commits = generateCommits(directory, log);

        return commits;
    }


    public static String generateLogFileTagOnly(File directory){
        String dir = directory.getAbsolutePath()+"/.git";
        ArrayList<String> tagList = gitShowTag(directory);
        String log = "";
        for (String tag : tagList) {
            String[] cmd = new String[]{
                    "git",
                    "--git-dir",
                    dir,
                    "log",
                    "--first-parent",
                    "--reverse",
                    "--format=%H;%h;%an;%ae;%at;%cn;%ce;%ct;%s;%b-?end?",
                    tag
            };
            String line;
            try {
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = in.readLine()) != null) {
                    log += line;
                }
                in.close();
                log = formatLogContent(log);
            } catch (Exception e) {
                System.out.println("Error during generating log file");
                e.printStackTrace();
            }
        }
        return log;
    }

    public static String generateLogFileMasterOnly(File directory){
        //String cmd = "git --git-dir "+directory.getAbsolutePath().replace(" ", "\\ ")+"/.git log --format=%H;%h;%an;%ae;%at;%cn;%ce;%ct;%s;%b-?end?";

        String dir = directory.getAbsolutePath()+"/.git";
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "log",
                "--first-parent",
                "--reverse",
                "--format=%H;%h;%an;%ae;%at;%cn;%ce;%ct;%s;%b-?end?"
        };
        String line;
        String log="";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
            while ((line = in.readLine()) != null) {
                log += line;
            }
            in.close();
            log = formatLogContent(log);
        } catch (Exception e) {
            System.out.println("Error during generating log file");
            e.printStackTrace();
        }
        return log;
    }



    public static String generateLogFile(File directory){
        //String cmd = "git --git-dir "+directory.getAbsolutePath().replace(" ", "\\ ")+"/.git log --format=%H;%h;%an;%ae;%at;%cn;%ce;%ct;%s;%b-?end?";

        String dir = directory.getAbsolutePath()+"/.git";
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "log",
                "--reverse",
                "--format=%H;%h;%an;%ae;%at;%cn;%ce;%ct;%s;%b-?end?"
        };
        String line;
        String log="";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
            while ((line = in.readLine()) != null) {
                log += line;
            }
            in.close();
            log = formatLogContent(log);
        } catch (Exception e) {
            System.out.println("Error during generating log file");
            e.printStackTrace();
        }
        return log;
    }


    public static List<Projcommit> generateTagCommit(File directory, String log, int projid, String projname){
        List<Projcommit> listOfCommitsSimple = new ArrayList<Projcommit>();
        int index = 0;
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            System.out.println("commit: "+index++);
            String line = scanner.nextLine();
            String[] commitInfo = line.split(";");
            Projcommit commit = new Projcommit();
            commit.setCommithash(commitInfo[0]);
            commit.setAuthoremail(commitInfo[6]);
            commit.setCommitauthor(commitInfo[2]);
            commit.setCommitdate(commitInfo[4]);
            commit.setProjid(projid);
            commit.setProjname(projname);
            listOfCommitsSimple.add(commit);
        }
        scanner.close();
        return listOfCommitsSimple;
    }

    public static List<Projcommit> generateCommitsSimple(File directory, String log, int projid, String projname){
        List<Projcommit> listOfCommitsSimple = new ArrayList<Projcommit>();
        int index = 0;
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            System.out.println("commit: "+index++);
            String line = scanner.nextLine();
            String[] commitInfo = line.split(";");
            Projcommit commit = new Projcommit();
            commit.setCommithash(commitInfo[0]);
            commit.setAuthoremail(commitInfo[6]);
            commit.setCommitauthor(commitInfo[2]);
            commit.setCommitdate(commitInfo[4]);
            commit.setProjid(projid);
            commit.setProjname(projname);
            listOfCommitsSimple.add(commit);
        }
        scanner.close();
        return listOfCommitsSimple;
    }

    public static List<Commit> generateCommits(File directory, String log){
        List<Commit> listOfCommits = new ArrayList<Commit>();
        int index = 0;
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            System.out.println("commit: "+index++);
            String line = scanner.nextLine();
            String[] commitInfo = line.split(";");
            if(commitInfo.length==10){
                Commit commit = new Commit();
                commit.setCommitHash(commitInfo[0]);
                commit.setAbbreviateCommitHash(commitInfo[1]);
                Author author = new Author();
                author.setName(commitInfo[2]);
                author.setEmail(commitInfo[3]);
                commit.setAuthor(author);
                commit.setAuthorTime(Integer.parseInt(commitInfo[4]));
                Committer committer = new Committer();
                committer.setName(commitInfo[5]);
                committer.setEmail(commitInfo[6]);
                commit.setCommitter(committer);
                commit.setCommitterTime(Integer.parseInt(commitInfo[7]));
                commit.setSubject(commitInfo[8]);
                commit.setBody(commitInfo[9]);

                //generateChanges
                commit.setChanges(generateChanges(directory, commit));

                listOfCommits.add(commit);
            }else if(commitInfo.length==9){
                Commit commit = new Commit();
                commit.setCommitHash(commitInfo[0]);
                commit.setAbbreviateCommitHash(commitInfo[1]);
                Author author = new Author();
                author.setName(commitInfo[2]);
                author.setEmail(commitInfo[3]);
                commit.setAuthor(author);
                commit.setAuthorTime(Integer.parseInt(commitInfo[4]));
                Committer committer = new Committer();
                committer.setName(commitInfo[5]);
                committer.setEmail(commitInfo[6]);
                commit.setCommitter(committer);
                commit.setCommitterTime(Integer.parseInt(commitInfo[7]));
                commit.setSubject(commitInfo[8]);
                commit.setBody(""); //9 campi. Non ha il body

                //generateChanges
                commit.setChanges(generateChanges(directory, commit));

                listOfCommits.add(commit);
            } else if(commitInfo.length>9){
                Commit commit = new Commit();
                commit.setCommitHash(commitInfo[0]);
                commit.setAbbreviateCommitHash(commitInfo[1]);
                Author author = new Author();
                author.setName(commitInfo[2]);
                author.setEmail(commitInfo[3]);
                commit.setAuthor(author);
                commit.setAuthorTime(Integer.parseInt(commitInfo[4]));
                Committer committer = new Committer();
                committer.setName(commitInfo[5]);
                committer.setEmail(commitInfo[6]);
                commit.setCommitter(committer);
                commit.setCommitterTime(Integer.parseInt(commitInfo[7]));
                commit.setSubject(commitInfo[8]);

                //Pi霉 di 10 campi
                String body = "";
                for(int i=9; i<commitInfo.length; i++){
                    body += commitInfo[i];
                }
                commit.setBody(body); //Modificato

                //generateChanges
                commit.setChanges(generateChanges(directory, commit));

                listOfCommits.add(commit);
            }
        }
        scanner.close();
        return listOfCommits;
    }

    public static List<Change> touchedFile(File directory, Commit commit){
        //String cmd = "git --git-dir "+directory.getAbsolutePath()+"/.git show --pretty=format: --name-only "+commit.getCommitHash();

        List<Change> listOfChanges = new ArrayList<Change>();

        String line;
        boolean skip=true; //skip first \n line
        String dir = directory.getAbsolutePath()+"/.git";

        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "show",
                "--pretty=format:",
                "--name-only",
                commit.getCommitHash()
        };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );

            while ((line = in.readLine()) != null) {
                if(skip){
                    skip=false;
                    continue;
                }

                Change change = new Change();
                FileBean file = new FileBean();
                file.setPath(line);
                change.setFile(file);

                //calculateTouchedLines
                //calculateTouchedLines(directory, commit, change);

                listOfChanges.add(change);
            }
            in.close();

        } catch (Exception e) {
            System.out.println("Error during generating log file");
            e.printStackTrace();
        }

        return listOfChanges;
    }

    public static List<Change> generateChanges(File directory, Commit commit){
        //String cmd = "git --git-dir "+directory.getAbsolutePath()+"/.git show --pretty=format: --name-only "+commit.getCommitHash();

        List<Change> listOfChanges = new ArrayList<Change>();

        String line;
        //TODO Check skip!
        boolean skip=false; //skip first \n line


        String dir = directory.getAbsolutePath()+"/.git";

        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "show",
                "--pretty=format:",

                "--name-only",
                commit.getCommitHash()
        };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );

            while ((line = in.readLine()) != null) {
                if(skip){
                    skip=false;
                    continue;
                }

                Change change = new Change();
                FileBean file = new FileBean();
                file.setPath(line);
                change.setFile(file);

                //calculateTouchedLines
                calculateTouchedLines(directory, commit, change);

                listOfChanges.add(change);
            }
            in.close();

        } catch (Exception e) {
            System.out.println("Error during generating log file");
            e.printStackTrace();
        }

        return listOfChanges;
    }

    public static void calculateTouchedLines(File directory, Commit commit, Change change){
        //String cmd = "git --git-dir "+directory.getAbsolutePath()+"/.git diff --word-diff -w --unified="+Integer.MAX_VALUE+" "+commit.getCommitHash()+"^:"+change.getFile().getPath().replace(" ", "\\ ")+" "+commit.getCommitHash()+":"+change.getFile().getPath().replace(" ", "\\ ");

        String line;
        boolean start=false;
        int lineNumber=0;
        List<Integer> addedlines = new ArrayList<Integer>();
        List<Integer> removedlines= new ArrayList<Integer>();
        List<Integer> modifiedlines= new ArrayList<Integer>();
        String MOD_PATTERN="^.+(\\[-|\\{\\+).*$";
        String ADD_PATTERN="^\\{\\+.*\\+\\}$";
        String REM_PATTERN="^\\[-.*-\\]$";

        String dir = directory.getAbsolutePath()+"/.git";
        String unified = "--unified="+Integer.MAX_VALUE;
        String file1 = commit.getCommitHash()+"^:"+change.getFile().getPath();
        String file2 = commit.getCommitHash()+":"+change.getFile().getPath();
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "diff",
                "--word-diff",
                "-w",
                unified,
                file1,
                file2
        };

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );

            while ((line = in.readLine()) != null) {
                if(start){
                    lineNumber++;
                    if(line.matches(MOD_PATTERN)){
                        modifiedlines.add(lineNumber);
                    } else if(line.matches(ADD_PATTERN)){
                        addedlines.add(lineNumber);
                    } else if(line.matches(REM_PATTERN)){
                        removedlines.add(lineNumber);
                    } else if(line.equals("+}")){ 	//Deal with the ^M character that is treated as newline (but not in git)
                        addedlines.add(--lineNumber);
                    } else if(line.equals("-]")){ 	//Deal with the ^M character that is treated as newline (but not in git)
                        removedlines.add(--lineNumber);
                    } else if(line.equals("+)")){ 	//Deal with the ^M character that is treated as newline (but not in git)
                        modifiedlines.add(--lineNumber);
                    }
                }
                else if(line.startsWith("@")){
                    start=true;
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error during calculating touched lines");
            e.printStackTrace();
        }

        change.setModifiedlines(modifiedlines);
        change.setAddedlines(addedlines);
        change.setRemovedlines(removedlines);

    }

    public static void gitReset(File directory, String commitHash){
        String line;
        String output = "";
        String commitID = commitHash;
        String dir = directory.getAbsolutePath()+"/.git";

        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "reset",
                "--hard",
                commitID
        };

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            //TODO Useless reading output
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );

            while ((line = in.readLine()) != null) {
                output = line.split(" ")[0];
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error during git-checkout");
            e.printStackTrace();
        }

    }

    public static void gitCheckout(File directory, String commitHash, File workingDirectory){
        String line;
        String output = "";
        String commitID = commitHash;
        String dir = directory.getAbsolutePath()+"/.git";
        String workDir = workingDirectory.getAbsolutePath();
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "--work-tree",
                workDir,
                "checkout",
                commitID
        };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
            while ((line = in.readLine()) != null) {
                output = line.split(" ")[0];
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error during git-checkout");
            e.printStackTrace();
        }
    }

    public static void gitCheckout(File directory, Commit commit, File workingDirectory){
        String line;
        String output = "";
        String commitID = commit.getCommitHash();
        String dir = directory.getAbsolutePath()+"/.git";
        String workDir = workingDirectory.getAbsolutePath();
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "--work-tree",
                workDir,
                "checkout",
                commitID
        };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            //TODO Useless reading output
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );

            while ((line = in.readLine()) != null) {
                output = line.split(" ")[0];
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error during git-checkout");
            e.printStackTrace();
        }
    }

    public static String gitBlame(File directory, Commit commit, int lineNumber, String fileName){
        String line;
        String commitToReturn = "";

        String commitID = commit.getCommitHash()+"^";
        String dir = directory.getAbsolutePath()+"/.git";
        String optionLineNumber = lineNumber+",+1";
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "blame",
                "-l",
                "-s",
                "-w",
                "-L",
                optionLineNumber,
                commitID,
                "--",
                fileName
        };

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );

            while ((line = in.readLine()) != null) {
                commitToReturn = line.split(" ")[0];
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error during git-blame");
            e.printStackTrace();
        }
        return commitToReturn;
    }

    public static ArrayList<String> gitShowTagCommit(File directory){
        //String cmd = "git --git-dir "+directory.getAbsolutePath()+"/.git show "+commit.getCommitHash()+":"+file.getPath();
        String line;
        String content="";
        String dir = directory.getAbsolutePath()+"/.git";
        ArrayList<String> tagCommitList = new ArrayList<>();
        ArrayList<String> tagList = gitShowTag(directory);
        for (String tag : tagList)
        {
            String[] cmd = new String[] {
                    "git",
                    "--git-dir",
                    dir,
                    "log",
                    "--pretty=oneline",
                    tag
            };
            try {
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
                if ((line = in.readLine()) != null) {
                    tagCommitList.add(line.split(" ")[0]);
                }
                in.close();
                p.destroy();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Error during git-tag: "+dir);
            }
        }
        return tagCommitList;
    }

    public static ArrayList<String> gitShowTag(File directory){
        String line;
        String content="";
        String dir = directory.getAbsolutePath()+"/.git";
        ArrayList<String> tagList = new ArrayList<>();
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "tag",
                "--sort=taggerdate"
        };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
            while ((line = in.readLine()) != null) {
//                if(line.startsWith("v")){
                    tagList.add(line);
//                }
            }
            in.close();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error during git-tag: "+dir);
        }
        return tagList;
    }

    public static String gitShow(File directory, String file, String commitID){
        //String cmd = "git --git-dir "+directory.getAbsolutePath()+"/.git show "+commit.getCommitHash()+":"+file.getPath();

        String line;
        String content="";

        String dir = directory.getAbsolutePath()+"/.git";
        String filePath = commitID+":"+file;

        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "show",
                filePath
        };

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
            while ((line = in.readLine()) != null) {
                content+=line;
                content+="\n";
            }
            in.close();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error during git-show: "+dir+"-"+filePath);
        }

        return content;
    }


    public static String gitShow(File directory, FileBean file, Commit commit){
        //String cmd = "git --git-dir "+directory.getAbsolutePath()+"/.git show "+commit.getCommitHash()+":"+file.getPath();

        String line;
        String content="";

        String dir = directory.getAbsolutePath()+"/.git";
        String filePath = commit.getCommitHash()+":"+file.getPath();

        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "show",
                filePath
        };

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
            while ((line = in.readLine()) != null) {
                content+=line;
                content+="\n";
            }
            in.close();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error during git-show: "+dir+"-"+filePath);
        }

        return content;
    }

    private static int getNumberOfLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }

    private static String formatLogContent(String content){
        String step1 = content.replace("\n", " ");
        String step2 = step1.replace("-?end?", "\n");
        return step2;
    }

    public static void clean(File directory){
        String dir = directory.getAbsolutePath()+"/.git";
        String content="";
        String line="";
        String[] cmd = new String[] {
                "git",
                "--git-dir",
                dir,
                "gc"
        };

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
            while ((line = in.readLine()) != null) {
                content+=line;
                content+="\n";
            }
            in.close();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
