package com.base.project.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cks on 2017/7/18.
 */

public class ListUtils {


    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> getDifferentListInTwoLists(List<Integer> list1, List<Integer> list2) {
        long st = System.nanoTime();
        List<Integer> diff = new ArrayList<Integer>();
        List<Integer> maxList = list1;
        List<Integer> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }

        // 将List中的数据存到Map中
        Map<Integer, Integer> maxMap = new HashMap<Integer, Integer>(maxList.size());
        for (Integer i : maxList) {
            maxMap.put(i, 1);
        }

        // 循环minList中的值，标记 maxMap中 相同的 数据2
        for (Integer i : minList) {
            // 相同的
            if (maxMap.get(i) != null) {
                maxMap.put(i, 2);
                continue;
            }
            // 不相等的
            diff.add(i);
        }

        // 循环maxMap
        for (Map.Entry<Integer, Integer> entry : maxMap.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }

        LogUtils.v("ListUtils"," total times:"+(System.nanoTime() - st));
        LogUtils.v("ListUtils","getDiffrent list :"+diff.toString());
        return diff;
    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getDifferentStringListInTwoLists(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }

        // 将List中的数据存到Map中
        Map<String, Integer> maxMap = new HashMap<String, Integer>(maxList.size());
        for (String i : maxList) {
            maxMap.put(i, 1);
        }

        // 循环minList中的值，标记 maxMap中 相同的 数据2
        for (String i : minList) {
            // 相同的
            if (maxMap.get(i) != null) {
                maxMap.put(i, 2);
                continue;
            }
            // 不相等的
            diff.add(i);
        }

        // 循环maxMap
        for (Map.Entry<String, Integer> entry : maxMap.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }

        LogUtils.v("ListUtils"," total times:"+(System.nanoTime() - st));
        LogUtils.v("ListUtils","getDiffrent list :"+diff.toString());
        return diff;
    }

    /**
     * 两个list取重复 交集
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> getRepetition(List<Integer> list1,
                                              List<Integer> list2) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer integer : list2) {//遍历list1
            if (list1.contains(integer)) {//如果存在这个数
                result.add(integer);//放进一个list里面，这个list就是交集
            }
        }
        return result;
    }
}