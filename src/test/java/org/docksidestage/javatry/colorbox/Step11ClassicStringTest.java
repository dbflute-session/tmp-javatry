/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.javatry.colorbox.base.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author ha jaehyoeng
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor color = colorBox.getColor();
        String colorName = color.getColorName();
        int answer = colorName.length();
        log(answer, colorName);

//        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
//        ColorBox colorBox = colorBoxList.get(0);
//        BoxColor boxColor = colorBox.getColor();
//        String colorName = boxColor.getColorName();
//        int answer = colorName.length();
//        log(answer, colorName); // also show name for visual check
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStr = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    int currentLength = ((String) content).length();
                    String strContent = (String)content;
                    if (maxStr == null || maxStr.length() < currentLength) {
                        maxStr = strContent;
                    }
                }
            }
        }
        log(maxStr != null ? maxStr : "*Not found string content");
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStr = null;
        String minStr = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    int currentLength = ((String) content).length();
                    String strContent = (String)content;
                    if (maxStr == null || maxStr.length() < currentLength) {
                        maxStr = strContent;
                    }else if (minStr == null || minStr.length() > currentLength) {
                        minStr = strContent;
                    }
                }
            }
        }
        log(maxStr != null ? maxStr.length()-minStr.length() : "*Not found string content");
    }

    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {

    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ArrayList<Integer> intArr = new ArrayList();         // 문자열을 저장할 배열
        int sum = 0;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();        // 뭐가 들어올지 모르니깐 일단 오브젝트로 때려넣는 모습
                if (content instanceof String) {            // 그 중에 자료형이 문자열인것을 고른다.
                    intArr.add(((String) content).length());// 문자열의 길이를 배열에 저장한다.
                }
            }
        }

        for (Integer integer : intArr) {
            sum += integer;                                 // 그 숫자들을 다 더해 넣는다.
        }

        System.out.println(sum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ArrayList<Integer> colorList = new ArrayList<>();
        int saveInt = 0;

        for (ColorBox colorBox : colorBoxList) {
            colorList.add(colorBox.getColor().getColorName().length());
        }

        for (int i = 0 ; i < colorList.size() - 1; i++) {
            for (int j = i ; j < colorList.size() ; j++) {
                if (colorList.get(i) > colorList.get(j)) {
                    continue;
                } else if (colorList.get(i) < colorList.get(j)) {
                    saveInt = colorList.get(i);
                    colorList.set(i, colorList.get(j));
                    colorList.set(j, saveInt);
                }
            }
        }

        for (int i = 0 ; i < colorList.size() ; i++) {
            if (colorList.get(0) == colorBoxList.get(i).getColor().getColorName().length()) {
                System.out.println(colorBoxList.get(i).getColor().getColorName());
            }
        }
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String str = "Water";
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                if(boxSpace.toString().length() >= str.length()
                        && boxSpace.toString().substring(0, str.length()).equals(str)) {
                    System.out.println(colorBox.getColor().getColorName());
                }
            }
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String str = "front";
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                if(boxSpace.toString().length() >= str.length()
                        && boxSpace.toString().substring(boxSpace.toString().length()-5, boxSpace.toString().length()).equals(str)) {
                    System.out.println(colorBox.getColor().getColorName());
                }
            }
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String str = "front";
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                if(boxSpace.toString().length() >= str.length()
                        && boxSpace.toString().substring(boxSpace.toString().length()-5, boxSpace.toString().length()).equals(str)) {
                    System.out.println(boxSpace.toString().length()-4);
                }
            }
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String str = "ど";
        int countDo = 0;
        int saveIndex = 0;
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                if (boxSpace.toString().contains(str)){
                     for (int i = 0 ; i < boxSpace.toString().length() ; i++) {
                         if (boxSpace.toString().substring(i, i+1).equals(str)) {
                             countDo++;
                             saveIndex = i+1;
                         }
                     }
                     if (countDo >= 2) {
                         System.out.println(saveIndex);
                     }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String str = "front";
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                if(boxSpace.toString().length() >= str.length()
                        && boxSpace.toString().substring(boxSpace.toString().length()-5, boxSpace.toString().length()).equals(str)) {
                    System.out.println(boxSpace.toString().charAt(boxSpace.toString().length()-1));
                }
            }
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String str = "Water";
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                if(boxSpace.toString().length() >= str.length()
                        && boxSpace.toString().substring(0, str.length()).equals(str)) {
                    System.out.println(boxSpace.toString().charAt(boxSpace.toString().length()-1));
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String saveStr = "";
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                if (boxSpace.toString().contains("o")){
                    saveStr = boxSpace.toString();
                }
            }
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
    }
}
