/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Radomnum {
    public static String getCard(int i){
       Random rand=new Random();//生成随机数
        String cardNnumer="";
        for(int a=0;a<i;a++){
        cardNnumer+=rand.nextInt(10);//生成i位数字
        }
       return cardNnumer;
    }
}
