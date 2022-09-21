package yuyin;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class OrganizeText {


    static String[] str = {"？","」", "……","。", "?","【","】","：,"," ","!",":","！","——"};

    static Integer[] pharseNum = {};

    static Integer pageNum = 1800;

    static Integer peiYinPageNum = 300;


    static List<String> manNameList;

    static List<String> womanNameList;

    static void init(){
        List<String> manNameListStr = Arrays.asList("南弦","墨尘","叶轩","夜羽",
                "高玄","博文","霄鸣","封禹","昊余","昌裕","晨健","睿凌","岩朋","承和","江远","阳澈","无洛","黎川",
                "言风","允之","玄飞","鸿墨","赫墨","光歌","正佑","林旭新","铸政","博铸","润绍","铭文","宇文","天文",
                "成槿","文双","靖盛","涵凡","峻鸿","健铸","英润","桦靖","泳桓","白羽","言泽","抒浩","昌拓","葛之",
                "翰玮","青澜","蔚程","楚源","沐泓","漫成","楚瀚","伶圣","泓凌","万濠","丁宇","蓝殿","善誉","科睿",
                "宇勇","夏宸","池路","胜宇","耀哲","熠文","才乐","志伟","哲航","宇潇","俊信","修本","文达","月康",
                "鸿凯","文咏","云耀","浩航","洋轩","鸿彤","飞良","鹏悦","世青","思涛","乐柯","容迈","鑫嘉","昕涆");
        manNameList = manNameListStr.stream().distinct().collect(Collectors.toList());
        List<String> womanNameListStr = Arrays.asList("宋南烟","林雨茹","王诗嘉","叶知旋","奕雯","璇爱","惜玲","璇妍",
                "梦晓思","曦秀","缘枫","如一彤","听蓉","郭怀曼","蕊依","陈芳华","雅馨","珊娜","书瑶","海萍","杨美珊","思丽"
        ,"李琼诗","梦禾","晴雪","雯丹");

        womanNameList = womanNameListStr.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取人员姓名
     * @param sex 1 男人  2 女人
     * @return
     */
    static String getName(Integer sex){
        if(sex==1){
            int size = manNameList.size();
            int randomIndex = new Random().nextInt(size);
            final String name = manNameList.get(randomIndex);
            manNameList.remove(randomIndex);
            return name;
        } else {
            int size = womanNameList.size();
            int randomIndex = new Random().nextInt(size);
            final String name = womanNameList.get(randomIndex);
            womanNameList.remove(randomIndex);
            return name;
        }
    }





    public static void main(String[] args) {

        init();
        String filename = "/Users/jianyang/Documents/workspace/yld/test/src/main/resources/原文.txt";
        String filename2 = "/Users/jianyang/Documents/workspace/yld/test/src/main/resources/原文-整理版.txt";

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
        // 男性
        objectObjectHashMap.put("依想",new String[]{"宋想"});
        objectObjectHashMap.put("毛升",new String[]{"白笛"});
        objectObjectHashMap.put("毛彤",new String[]{"白含"});
        objectObjectHashMap.put("彤彤",new String[]{"含含"});
        objectObjectHashMap.put("林雨茹",new String[]{"吕明婷"});
        objectObjectHashMap.put("秋依枫",new String[]{"周信信"});
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
        
        final String replace11 = replace9.replace("的方", "地方");
        final String replace12 = replace11.replace("套套", "TT");
        final String replace14 = replace12.replace("kg", "千克");
        final String replace15 = replace14.replace("百度", "BD");
        final String replace16 = replace15.replace("出轨", "变心");
        final String replace17 = replace16.replace("劈腿", "变心");
        final String replace18 = replace17.replace("小三", "三儿");
        final String replace19 = replace18.replace("的点", "地点");
        final String replace20 = replace19.replace("本的", "本地");
        final String replace21 = replace20.replace("场的", "场地");
        final String replace22 = replace20.replace("tm", "他喵");
        return replace22;
    }

    static String removePharseNum(String source) {
        //特殊 数字处理
        final String 妖二零 = source.replace("120", "幺二零");
        final String 幺幺零 = 妖二零.replace("110", "幺幺零");
        final String 幺幺玖 = 幺幺零.replace("119", "幺幺玖");
        final String 说到 = 幺幺零.replace("：「", "说到");
        String content = 说到;
        for (Integer integer : pharseNum) {
            content = content.replace(integer.toString(),"");
        }
        return content;
    }


    static String readContent(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        // 第1步","使用File类找到一个文件
        File file = new File(fileName);
        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(file));
            // 第2步","通过子类实例化父类对象
            // 第3步","进行读操作","按行读入
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
        //1","替换特殊标点符号
        for (String s : str) {
            newStr = newStr.replace(s, ",");
        }

        //2","去除换行符
        final String newStr2 = newStr.replace("\r\n", "");
        //3","每隔1700字 增加一个换行符
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
        final String replace6 = replace5.replace("：,", ",");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            fos.write(replace6.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
