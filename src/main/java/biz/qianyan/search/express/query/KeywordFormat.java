/**
 * 
 */
package biz.qianyan.search.express.query;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jock
 */
public class KeywordFormat {

    private static String regEx = "";
    private static List<String> blacklist = new ArrayList<String>();
    private static String file = "/data/search/WEB-INF/blackword.txt";

    public static void add(String keyword) {
        try {
            RandomAccessFile re = new RandomAccessFile(file, "rw");
            re.seek(re.length());
            re.write(keyword.getBytes("GBK"));
            re.writeBytes("\n");
            re.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFile() {
        String s = "";
        regEx = "";
        try {
            RandomAccessFile re = new RandomAccessFile(file, "r");
            String str = null;
            while ((str = re.readLine()) != null) {
                str = new String(str.getBytes("iso8859-1"), "GBK").trim();
                blacklist.add(str);
                regEx += str + "|";
                s = s + str + "\n";
            }
            regEx.substring(0, regEx.length() - 1);
            re.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    static {
        readFile();
    }

    public static String removeBlackword(String keyword) {
        if (regEx.trim().equals("")) {
            return keyword;
        }
        String c = keyword.trim();
        for (String str : blacklist) {
            if (c.equals(str))
                return "";
        }

        return c;
    }

    public static String convertKeyword(String keyword) {
        keyword = removeBlackword(keyword).trim();
        if (keyword.equals("")) {
            return keyword;
        }
        keyword = moveAND(keyword);
        keyword = moveOR(keyword);
        boolean breakFlag = false;
        boolean firstOpt = true;

        StringBuffer strTmp = new StringBuffer(keyword.trim());
        StringBuffer ret = new StringBuffer();
        String strOpt = "";
        int iSize = strTmp.length();
        char preChar = 0;
        char tmpChar = 0;
        for (int i = 0; i < iSize; i++) {
            tmpChar = strTmp.charAt(i);
            int charType = Character.getType(tmpChar);
            // 判断是否是操作符
            if (charType == 9 || charType == 5 || charType == 2 || charType == 1) {
                ret.append(strOpt);
                if (preChar == 32 && strOpt.equals("")) {
                    ret.append(" AND ");
                }
                strOpt = "";
                preChar = 0;
                ret.append(tmpChar);
                breakFlag = false;
                firstOpt = false;
            } else {
                // 是否在查询字符串的起始位置
                if (!firstOpt && !breakFlag) {
                    // 保留第一个空格
                    if (tmpChar == 32) {
                        preChar = tmpChar;
                        continue;
                    }
                    // 如果是 - 并且前面不是空格 直接添加到查询字符串
                    /*
                     * if (preChar != 32 && tmpChar == 45) { ret.append(tmpChar); continue; }
                     */
                    // 如果是 | 并且前面不是空格 直接添加到查询字符串
                    if (tmpChar == 124) {
                        strOpt = " OR ";
                        breakFlag = true;
                        preChar = 0;
                        continue;
                    }
                    // 如果是 - 并且前面是空格
                    if (preChar == 32 && tmpChar == 45) {
                        strOpt = " - ";
                        breakFlag = true;
                        preChar = 0;
                        continue;
                    }

                    strOpt = " && ";
                    breakFlag = true;
                }
            }
        }
        // 2003-12-15 modify
        return new String(ret.toString());
    }

    private static String moveAND(String keyword) {
        String tmpStr = keyword.toLowerCase();
        String ret = "";
        int pos = tmpStr.indexOf(" and ");
        if (pos == -1)
            return keyword;
        while (pos >= 0) {
            ret = ret + keyword.substring(0, pos) + " ";
            keyword = keyword.substring(pos + 5, keyword.length());
            tmpStr = tmpStr.substring(pos + 5, tmpStr.length());
            pos = tmpStr.indexOf(" and ");
        }

        return ret + keyword;
    }

    private static String moveOR(String keyword) {
        String tmpStr = keyword.toLowerCase();
        String ret = "";
        int pos = tmpStr.indexOf(" or ");
        if (pos == -1)
            return keyword;
        while (pos >= 0) {
            ret = ret + keyword.substring(0, pos) + " | ";
            keyword = keyword.substring(pos + 4, keyword.length());
            tmpStr = tmpStr.substring(pos + 4, tmpStr.length());
            pos = tmpStr.indexOf(" or ");
        }

        return ret + keyword;
    }
}
