package com.eojin;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyProcess
{
    //블루스택 프로세스 찾기 명령어
    static String command = "tasklist /fi \"imagename eq Bluestacks.exe\"";
    // 블루스텍 이름
    static String process_name = "Bluestacks.exe";
    public static boolean ProcessCheck() throws IOException {
        boolean chk = false;

        Process process = Runtime.getRuntime().exec(command);
        Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
        while (scanner.hasNext())
        {
            if(scanner.nextLine().contains(process_name)) {
                //System.out.println(scanner.nextLine());
                chk = true;
                return chk;
            }
        }
        return chk;
    }
}
