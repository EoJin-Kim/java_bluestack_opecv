package com.eojin;
/////////////
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import org.opencv.core.*;
import org.opencv.core.Core;
import javax.security.auth.login.LoginException;

public class Main {

    static {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
    }

    public static JDA jda;

    public static void main(String[] args){
        JDABuilder jb = new JDABuilder(AccountType.BOT);
        jb.setAutoReconnect(true);
        //jb.setStatus(OnlineStatus.ONLINE);
        jb.setToken("ODAzMjU1MTM2MjY4MDU4NjQ0.YA7HpA.BYYG26uSg-IbjoHghyykZAQMP_A");
        jb.addEventListener(new TListener());
        //jb.addEventListener()
        try {
            jda = jb.buildBlocking();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}