package com.eojin;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
//import net.dv8tion.jda.core.events.channel.
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.IOException;

 class TListener extends ListenerAdapter {


    MyOpenCV myOpenCV = new MyOpenCV();
    MyTapTitan myTapTitan = new MyTapTitan();
    //MyProcess myProcess = new MyProcess();
    // status = 0 게임 시작전
    // status = 1 게임 중
    private final int status =0;
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        //TextChannel tc = event.getTextChannel();
        Message msg = event.getMessage();
        MessageChannel mc = msg.getChannel();

        // 봇 명령어 인식안하기
        if(user.isBot()) return;

        try {

            // 블루스택 시행중인지 체크
            boolean check = MyProcess.ProcessCheck();
            if(!check)
            {
                mc.sendMessage("블루스택이 실행중이 아닙니다. " + user.getAsMention()).queue();
                return;
            }

            // 게임 조작 리스너
            if (msg.getContentRaw().equalsIgnoreCase("hello")) {
                mc.sendMessage("Hello, " + user.getAsMention()).queue();
            } else if (msg.getContentRaw().equalsIgnoreCase("capture")) {
                MyOpenCV.ScreanCapture();
                mc.sendMessage("Capture Complete " + user.getAsMention()).queue();
            } else if (msg.getContentRaw().equalsIgnoreCase("gamestart")) {
                myTapTitan.GameStart(5);
                mc.sendMessage("Taptitan Start " + user.getAsMention()).queue();
            } else if (msg.getContentRaw().equalsIgnoreCase("heroup")) {
                myTapTitan.HeroLevelUp(0);
                mc.sendMessage("Hero Level Up " + user.getAsMention()).queue();
            } else if (msg.getContentRaw().equalsIgnoreCase("levelup")) {
                myTapTitan.LevelUp(0);
                mc.sendMessage("Level Up " + user.getAsMention()).queue();
            }
        } catch (AWTException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}