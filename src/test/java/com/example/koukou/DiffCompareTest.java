package com.example.koukou;

import com.example.koukou.basic.diff_match_patch;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author
 * @Date 2022/7/5 10:32
 * @Version 1.0
 * @Description 文件差异比对
 **/
public class DiffCompareTest {
    public void getDifferent(String fileUrl1, String fileUrl2) throws IOException {
        diff_match_patch dmp = new diff_match_patch();
        try {
            // 读取文件file1和file2的文本内容
            String file1 = readFileContent(fileUrl1);
            String file2 = readFileContent(fileUrl2);
            // 得到file1和file2的所有空格的index
            List<Integer> beList = rememberSpacing(file1);
            List<Integer> afList = rememberSpacing(file2);
            // 去除file1和file2的所有空格
            file1 = file1.replace(" ", "");
            file2 = file2.replace(" ", "");
            // 比较不同
            LinkedList<diff_match_patch.Diff> t = dmp.diff_main(file1, file2, true);

            for (diff_match_patch.Diff diff : t) {
                System.out.println(diff.toString());
            }

            StringBuffer s1 = new StringBuffer();
            StringBuffer s2 = new StringBuffer();
            Integer indexBe = 0;
            Integer indexAf = 0;
            for (diff_match_patch.Diff diff : t) {
                //比对之前
                StringBuffer diffTextBe = new StringBuffer(diff.text);
                //比对之后
                StringBuffer diffTextAf = new StringBuffer(diff.text);
                //如果比对结果是EQUAL[保持不变]
                if (diff.operation.toString().equalsIgnoreCase("DELETE") || diff.operation.toString().equalsIgnoreCase("INSERT")) {
                    indexBe = appendString2("DELETE", diff, s1, s2, beList, indexBe);
                    indexAf = appendString2("INSERT", diff, s2, s1, afList, indexAf);
                    s1.append(diffTextBe);
                    s2.append(diffTextAf);
                    indexBe += diffTextBe.length();
                    indexAf += diffTextAf.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param fileName
     * @return 返回file的文本内容，并对每一行的内容去除首位空白符
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static String readFileContent(String fileName) throws IOException, FileNotFoundException {
        File file = new File(fileName);
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("GBK")));
        String content = "";
        StringBuilder sb = new StringBuilder();
        while (content != null) {
            content = bf.readLine();
            if (content == null) {
                break;
            }
            // 删除读取的行的首位空白符
            sb.append(content.trim());
        }
        bf.close();
        return sb.toString();
    }

    /**
     *
     * @param str
     * @return 返回字符串str的所有空格的index索引集合
     */
    private static List<Integer> rememberSpacing(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (' ' == str.charAt(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public static Integer appendString2(String type, diff_match_patch.Diff diff, StringBuffer sbOne,
                                        StringBuffer sbTwo, List<Integer> list, Integer i) {
        Integer result = i;
        if (type.equals(diff.operation.toString())) {
            StringBuffer sb = new StringBuffer(diff.text);
            for (Integer o : list) {
                if (o >= i && o < i + sb.length()) {
                    sb.insert(o - i, ' ');
                }
            }
            sbOne.append("<span style='color:red'>").append(sb).append("</span>");
            System.out.println(sbOne);
            result = i + sb.length();
        }
        return result;
    }

    @Test
    public void getDifferent() throws IOException {
//        String file1 = "D:\\金证实习\\a.xml";
//        String file2 = "D:\\金证实习\\b.xml";
//        getDifferent(file1, file2);
    }
}
