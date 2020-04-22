package com.example.demo.util;

import com.example.demo.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FPtreeTest {

    @Test
    void testFPtree() {
        FPTree fptree = new FPTree();
        fptree.setMinSup(3);
        List<List<String>> transRecords = fptree.readTransData();
        System.out.println(transRecords);
        System.out.println("读取数据完毕！--------------------------------------------");
        ArrayList<TreeNode> F1 = fptree.buildF1Items(transRecords);
        fptree.printF1(F1);
        System.out.println("找出频繁1项集完毕！----------------------------------------");
        TreeNode treeroot = fptree.buildFPTree(transRecords, F1);
        fptree.printFPTree(treeroot);
        System.out.println("创建树完毕！----------------------------------------------");
        Map<List<String>, Integer> patterns = fptree.findFP(treeroot, F1);
        fptree.printFreqPatterns(patterns);
        System.out.println("找出频繁项完毕---------------------------------------------！");
    }

}
