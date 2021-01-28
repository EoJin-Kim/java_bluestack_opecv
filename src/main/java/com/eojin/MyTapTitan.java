package com.eojin;


import java.awt.*;
import java.io.IOException;

public class MyTapTitan {

    private static MyOpenCV myopencv = new MyOpenCV();

    // 블루스택
    static String logo="img/topMenu/logo.png";
    static String logoMini="img/topMenu/logoMini.png";

    // 게임
    static String GameIcon="img/topMenu/taptitan.png";

    MyOpenCV.RelativePoint myGamePoint= new MyOpenCV.RelativePoint(logo,-7,+55);

    MyOpenCV.RelativePoint atackPoint= new MyOpenCV.RelativePoint(logoMini,183,342);

    MyOpenCV.RelativePoint bottomClose= new MyOpenCV.RelativePoint(logoMini,361,425);

    //탭 타이탄 하단 바
    MyOpenCV.RelativePoint bottom1= new MyOpenCV.RelativePoint(logoMini,10,732);
    MyOpenCV.RelativePoint bottom2= new MyOpenCV.RelativePoint(logoMini,83,732);
    MyOpenCV.RelativePoint bottom3= new MyOpenCV.RelativePoint(logoMini,151,732);
    MyOpenCV.RelativePoint bottom4= new MyOpenCV.RelativePoint(logoMini,217,732);
    MyOpenCV.RelativePoint bottom5= new MyOpenCV.RelativePoint(logoMini,286,732);
    MyOpenCV.RelativePoint bottom6= new MyOpenCV.RelativePoint(logoMini,354,732);
    MyOpenCV.RelativePoint bottomWheel= new MyOpenCV.RelativePoint(logoMini,331,529);

    // 하단 바
    MyOpenCV.RelativePoint top1= new MyOpenCV.RelativePoint(logoMini,323,541);
    MyOpenCV.RelativePoint top2= new MyOpenCV.RelativePoint(logoMini,323,598);
    MyOpenCV.RelativePoint top3= new MyOpenCV.RelativePoint(logoMini,323,663);


    void GameStart(int timer) throws AWTException, InterruptedException, IOException
    {
        MyOpenCV.ScreanCapture();
        MyOpenCV.ImgClick(myGamePoint,1);
        MyOpenCV.ScreanCapture();
        MyOpenCV.ImgClick(GameIcon,timer);
    }

    void HeroLevelUp(int timer) throws AWTException, InterruptedException, IOException {
        MyOpenCV.ScreanCapture();
        MyOpenCV.ImgClick(bottomClose,1);
        MyOpenCV.ImgClick(bottom2,1);
        //myopencv.ImgMove(bottomWheel,0);
        //myopencv.Wheel(1);
        MyOpenCV.ImgClick(top1,0);
        MyOpenCV.ImgClick(top2,0);
        MyOpenCV.ImgClick(top3,0);
        MyOpenCV.ImgClick(bottomClose,timer);
    }

    void LevelUp(int timer) throws AWTException, InterruptedException, IOException
    {
        MyOpenCV.ScreanCapture();
        MyOpenCV.ImgClick(bottomClose,1);
        MyOpenCV.ImgClick(bottom1,1);
        MyOpenCV.ImgClick(top1,0);
        MyOpenCV.ImgClick(bottomClose,timer);
    }


}

