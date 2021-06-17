package com.example.frame.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码工具类
 * 
 * @author bobo
 * @date 2021/04/16
 */

public class CaptchaUtils {
    /**
     * 
     */
    private static final String RANDOM_STRS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String FONT_NAME = "Fixedsys";
    private static final int FONT_SIZE = 18;
    private Random random = new Random();
    
    /**
     * 图片宽度
     */
    private int width = 80;
    
    /**
     * 图片高度
     */
    private int height = 25;
    
    /**
     * 干扰线条数
     */
    private int lineNum = 20;

    /**
     * 字符串个数
     */
    private int strNum = 4;

    /**
     * 生成随机验证码图片
     * @param randomCode
     * @return
     */
    public BufferedImage getRandomCodeImage(StringBuffer randomCode){
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();

        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width,height);

        g.setColor(getRandColor(110,120));
        for (int i = 0; i < lineNum; i++) {
            drawLine(g);
        }
        g.setFont(new Font(FONT_NAME,Font.ROMAN_BASELINE,FONT_SIZE));
        for (int i = 0; i < strNum; i++) {
            randomCode.append(drawString(g,i));
        }
        g.dispose();
        return image;
    }

    /**
     * 绘制字符串
     * @param g
     * @param i
     * @return
     */
    private String drawString(Graphics g,int i){
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(RANDOM_STRS.length())));
        g.translate(random.nextInt(3),random.nextInt(3));
        g.drawString(rand,13*i,16);
        return rand;
    }

    /**
     * 绘制干扰线
     * @param g
     */
    private void drawLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int x0 = random.nextInt(16);
        int y0 = random.nextInt(16);
        g.drawLine(x,y,x0,y0);
    }

    /**
     * 范围内的随机颜色
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc,int bc){
        int colorNum =255;
        if(fc>colorNum){
            fc = colorNum;
        }
        if(bc>colorNum){
            bc = colorNum;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    /**
     * 获得随机字符
     * @param num
     * @return
     */
    private String getRandomString(int num){
        return String.valueOf(RANDOM_STRS.charAt(num));
    }
}
