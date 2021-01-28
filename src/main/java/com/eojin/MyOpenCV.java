package com.eojin;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyOpenCV {

    static String Screenshot = "img/screenshot.jpg";


    static class RelativePoint
    {
        String imgPath;
        int x,y;
        RelativePoint(String imgPath,int x,int y)
        {
            this.imgPath = imgPath;
            this.x = x;
            this.y = y;
        }
    }
    static public java.awt.Point MousePoint()
    {
        PointerInfo mPointer = MouseInfo.getPointerInfo();
        return mPointer.getLocation();
    }
    static public java.awt.Point ImgRelativePoint(String img_path) throws IOException, AWTException {
        ScreanCapture();
        java.awt.Point mPointer = MousePoint();
        java.awt.Point imgPoint = ImgPoint(img_path);
        //System.out.println(mPointer);
        //System.out.println(imgPoint);
        java.awt.Point relativePoint = new java.awt.Point((int) (mPointer.getX()-imgPoint.x),(int)(mPointer.getY()-imgPoint.y));
        return relativePoint;
    }

    static public void Click(java.awt.Point imgPoint,int Xpls,int Ypls) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove((int)imgPoint.getX()+Xpls, (int)imgPoint.getY()+Ypls);
        //System.out.println("click x : "+((int)imgPoint.getX()+Xpls)+", y : "+((int)imgPoint.getY()+Ypls));
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    static public void Wheel(int timer) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.setAutoDelay(100);
        Thread.sleep(2000);
        robot.mouseWheel(1000);
        Thread.sleep(timer*1000);
    }


    static public void ImgClick(String img_path, int timer) throws IOException, AWTException, InterruptedException {

        java.awt.Point matchLoc = ImgPoint(img_path);
        //Mat template=Imgcodecs.imread(img_path);
        Click(matchLoc, 0, 0);
        Thread.sleep(timer*1000);
        //System.out.println(matchLoc.x+","+matchLoc.y);
    }

    static public void ImgClick(RelativePoint rltvpt, int timer) throws IOException, AWTException, InterruptedException {

        java.awt.Point matchLoc = ImgPoint(rltvpt.imgPath);
        //Mat template=Imgcodecs.imread(img_path);
        Click(matchLoc, rltvpt.x, rltvpt.y);
        Thread.sleep(timer*1000);
        //System.out.println(matchLoc.x+","+matchLoc.y);
    }
    static public void ImgMove(String img_path,int timer) throws IOException, AWTException, InterruptedException {

        java.awt.Point matchLoc = ImgPoint(img_path);
        Robot robot = new Robot();
        robot.mouseMove((int)matchLoc.getX(), (int)matchLoc.getY());
        Thread.sleep(timer*1000);
        //System.out.println(matchLoc.x+","+matchLoc.y);
    }


    static public void ImgMove(RelativePoint rltvpt,int timer) throws IOException, AWTException, InterruptedException {

        java.awt.Point matchLoc = ImgPoint(rltvpt.imgPath);
        //Mat template=Imgcodecs.imread(img_path);
        Robot robot = new Robot();
        robot.mouseMove((int)matchLoc.getX()+rltvpt.x, (int)matchLoc.getY()+rltvpt.y);
        Thread.sleep(timer*1000);
        //System.out.println(matchLoc.x+","+matchLoc.y);
    }
    static public java.awt.Point ImgPoint(String img_path)
    {
        Mat source= Imgcodecs.imread(Screenshot);
        Mat template=Imgcodecs.imread(img_path);
        Mat outputImage=new Mat();
        int machMethod= Imgproc.TM_CCOEFF;
        Imgproc.matchTemplate(source, template, outputImage, machMethod);
        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        org.opencv.core.Point matchLoc=mmr.maxLoc;
        java.awt.Point imgPoint = new java.awt.Point((int)(matchLoc.x+template.cols()/2),(int)(matchLoc.y+ template.rows()/2));
        return imgPoint;
    }
    static public void ScreanCapture() throws AWTException, IOException {
        Robot robot = new Robot();
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rectangle = new Rectangle(resolution);
        BufferedImage bi = robot.createScreenCapture(rectangle);
        ImageIO.write(bi, "jpg", new File(Screenshot));
    }
}
