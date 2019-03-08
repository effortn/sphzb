package com.en.sphzb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 案件处理工具类，主要处理案情描述
 * create by en
 * at 2019/3/2 13:32
 **/
public class CasesUtil {

    // 身份证正则表达式
    private static final Pattern SFZ_PATTERN = Pattern.compile("\\d{18}");

    // 手机号正则表达式
    private static final Pattern SJH_PATTERN = Pattern.compile("\\d{11}");

    // 时间正则表达式
    private static final Pattern SJ_PATTERN = Pattern.compile("\\d{2,4}.\\d{1,2}.\\d{1,2}.(\\d{1,2}.)?(\\d{1,2}.)?(\\d{1,2}.)?");

    // 车牌号正则表达式
    private static final Pattern CP_PATTERN = Pattern.compile("([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川" +
            "宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|" +
            "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}" +
            "[A-HJ-NP-Z0-9挂学警港澳]{1})");

    // 路正则表达式
    private static final Pattern LK_PATTERN = Pattern.compile(".路");

    // 无用字符串
    private static final String[] USElESS_STRING = {"报警人", "手机号", "报警", "称", "在", "被", "民警", "的", "其", "许",
            "路", "普陀", "静安", "浦东", "新区", "青浦", "区", "徐汇", "长宁", "闵行", "宝山", "嘉定", "金山", "松江", "崇明",
            "奉贤", "虹口", "杨浦", "黄浦"};

    /**
     * 删除案件案情无效的信息：身份证、手机号、
     * create by en
     * at 2019/3/2 13:57
     **/
    public static String deletePattern(String caseDesprition) {
        String result;
        Matcher matcher = SFZ_PATTERN.matcher(caseDesprition);
        result = matcher.replaceAll(" ");
        matcher = SJH_PATTERN.matcher(result);
        result = matcher.replaceAll(" ");
        matcher = SJ_PATTERN.matcher(result);
        result = matcher.replaceAll(" ");
        for (int i = 0; i < USElESS_STRING.length; i++) {
            result = result.replaceAll(USElESS_STRING[i], " ");
        }
        return result;
    }

    /**
     * 替换案件案情无效的信息：身份证、手机号、
     * create by en
     * at 2019/3/2 13:57
     **/
    public static String replacePattern(String caseDesprition) {
        String result;
        Matcher matcher = SFZ_PATTERN.matcher(caseDesprition);
        result = matcher.replaceAll("222222*********6114");
        matcher = SJH_PATTERN.matcher(result);
        result = matcher.replaceAll("111****111");
        matcher = SJ_PATTERN.matcher(result);
        result = matcher.replaceAll("某年某月某日");
        matcher = CP_PATTERN.matcher(result);
        result = matcher.replaceAll("****");
        matcher = LK_PATTERN.matcher(result);
        result = matcher.replaceAll("某路");
        return result;
    }

    public static void main(String[] args) {
//        double l = 1 / 100 * 100;
        String s = replacePattern("2018年1月3日0时04分许，青浦交警支队民警在本市青浦区沪渝高速北侧60公里约800米处执勤时，拦下一辆牌号为苏E83WS3的小     2018年7月30日11时22分许，报警人周玲（女，汉族，初中文化，1991年9月1日出生，" +
                "身份证号码：341181199109011664，户籍地址：安徽省天长市汊涧镇三塘村晏庄队9号，" +
                "现住址：上海市闵行区永杰路439弄11号101室，联系方式：15805508783）报警称：当日11时许，" +
                "其妹妹周晴（女，2007年11月6日出生，安徽人）" +
                "独自前往上海市闵行区永杰路439弄18号104室小区超市内购买商品时，在超市内被一名老年男子抓摸其妹妹周晴的胸部。");
        System.out.println(s);
    }

}
