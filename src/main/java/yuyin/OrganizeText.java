package yuyin;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OrganizeText {


    static String[] str = {"？","」", "「", "……","。", "?"};

    static Integer[] pharseNum = {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};

    static Integer pageNum = 1700;


    public static void main(String[] args) {

        String filename = "/Users/jianyang/Documents/workspace/yld/test/src/main/resources/他曾经为了另⼀个⼥孩翻遍了整座城市.txt";
        String filename2 = "/Users/jianyang/Documents/workspace/yld/test/src/main/resources/他曾经为了另⼀个⼥孩翻遍了整座城市-整理版.txt";

        //读取本地文件中的内容
        String content = readContent(filename);
        final String trim = content.trim();
        final String replace = trim.replace("<p>", "");
        final String replace1 = replace.replace("</p>", "");
        //去除章节
        final String s = removePharseNum(replace1);
        // 替换标点符号
        final String content2 = replaceSymbol(s);
        // 替换人名
        final HashMap<String, String[]> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("晓晨",new String[]{"魏延"});
        objectObjectHashMap.put("王芳雪",new String[]{"秦初初"});
        final String s1 = replaceName(content2, objectObjectHashMap);

        // 替换多音字
        final String s2 = replaceDuoYin(s1);

        // 将文件写出
        outPut(s2,filename2);
    }

    private static String replaceDuoYin(String s1) {
        final String replace = s1.replace("行", "型");
        final String replace1 = replace.replace("烟", "淹");
        final String replace2 = replace1.replace("看着", "看者");
        final String replace3 = replace2.replace("家长", "家涨");
        final String replace4 = replace3.replace("修⻓", "修常");
        final String replace5 = replace4.replace("恒⻓", "恒常");
        final String replace6 = replace5.replace("⻓久", "常久");
        final String replace7 = replace6.replace("纹身", "文深");
        final String replace8 = replace7.replace("⽶⻓", "米常");
        final String replace9 = replace8.replace("着火", "沼火");
        final String replace10 = replace9.replace("地", "的");
        final String replace11 = replace10.replace("的方", "地方");
        return replace11;
    }

    static String removePharseNum(String source) {
        //特殊 数字处理
        final String 妖二零 = source.replace("120", "幺二零");
        final String 幺幺零 = 妖二零.replace("110", "幺幺零");
        final String 幺幺玖 = 幺幺零.replace("119", "幺幺玖");
        String content = 幺幺玖;
        for (Integer integer : pharseNum) {
            content = content.replace(integer.toString(),"");
        }
        return content;
    }


    static String readContent(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        // 第1步、使用File类找到一个文件
        File file = new File(fileName);
        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(file));
            // 第2步、通过子类实例化父类对象
            // 第3步、进行读操作、按行读入
            BufferedReader bf = new BufferedReader(input);
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            bf.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    static String replaceSymbol(String text) {
        String newStr = text;
        //1、替换特殊标点符号
        for (String s : str) {
            newStr = newStr.replace(s, ",");
        }

        //2、去除换行符
        final String newStr2 = newStr.replace("\r\n", "");
        //3、每隔2200字 增加一个换行符
        final StringBuffer stringBuffer = new StringBuffer();
        int num = newStr2.length() / pageNum;
         for (int i = 0; i < num + 1; i++) {
            String substring = "";
            stringBuffer.append("\r\n").append("=============第" + (i + 1) + "篇==============").append("\r\n");
            if (i == num) {
                substring = newStr2.substring(i * pageNum, newStr2.length() - 1);
            } else {
                substring = newStr2.substring(i * pageNum, (i + 1) * pageNum);
            }
            stringBuffer.append(substring).append("\r\n").append("\r\n");
        }


        return stringBuffer.toString();
    }

    /**
     * 名字替换
     * @param sourceContent 原文件
     * @param hashMap 名字集合
     * @return
     */
    static String replaceName(String sourceContent, HashMap<String, String[]> hashMap){
        final Set<Map.Entry<String, String[]>> entries = hashMap.entrySet();
        String newContent = sourceContent;
        for (Map.Entry<String, String[]> entry : entries) {
            final String newName = entry.getKey();
            final String[] oldNames = entry.getValue();
            for (String oldName : oldNames) {
                newContent = newContent.replace(oldName,newName);
            }
        }

        return newContent;
    }

    static void outPut(String content,String fileName){
        final String trim = content.trim();
        final String replace = trim.replace(",,", ",");
        final String replace1 = replace.replace(", ,", ",");
        final String replace2 = replace1.replace("，", ",");
        final String replace3 = replace2.replace(",,,", ",");
        final String replace4 = replace3.replace("⸺", ",");
        final String replace5 = replace4.replace(",,", ",");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            fos.write(replace5.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
