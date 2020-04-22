package com.example.demo.util;


import com.example.demo.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * FP树算法实现 Title: UCAS <br>
 * Description: <br>
 * Date: 2014-6-23 <br>
 * Copyright (c) 2014 AILK <br>
 *
 * @author fengbo
 */
public class FPTree {
    private int minSup; // 最小支持度

    // 初始化交易记录
    private final static List<String> transList = new ArrayList<String>(); // 所有交易
    static {
        transList.add("f,a,c,d,g,i,m,p");
        transList.add("a,b,c,f,l,m,o");
        transList.add("b,f,h,j,o");
        transList.add("b,c,k,s,p");
        transList.add("a,f,c,e,l,p,m,n");
    }

    public int getMinSup() {
        return minSup;
    }

    public void setMinSup(int minSup) {
        this.minSup = minSup;
    }

    /**
     * 1.读入事务记录
     *
     * @param filenames
     * @return
     * @author fengbo
     */
    public List<List<String>> readTransData() {
        List<List<String>> records = new LinkedList<List<String>>();
        List<String> record;

        for(String str : transList)
        {
            record = new LinkedList<String>();
            String[] tran = str.split(",");
            for(String t : tran)
            {
                record.add(t);
            }
            records.add(record);
        }
        return records;
    }

    /**
     * 2.构造频繁1项集
     *
     * @param transRecords
     * @return
     * @author fengbo
     */
    public ArrayList<TreeNode> buildF1Items(List<List<String>> transRecords) {
        ArrayList<TreeNode> F1 = null;
        if (transRecords.size() > 0) {
            F1 = new ArrayList<TreeNode>();
            Map<String, TreeNode> map = new HashMap<String, TreeNode>();
            // 计算支持度
            for (List<String> record : transRecords) {
                for (String item : record) {
                    if (!map.keySet().contains(item)) {
                        TreeNode node = new TreeNode(item);
                        node.setCount(1);
                        map.put(item, node);
                    } else {
                        map.get(item).countIncrement(1);
                    }
                }
            }
            // 把支持度大于（或等于）minSup的项加入到F1中
            Set<String> names = map.keySet();
            for (String name : names) {
                TreeNode tnode = map.get(name);
                if (tnode.getCount() >= minSup) {
                    tnode.setName(name);
                    F1.add(tnode);
                }
            }
            Collections.sort(F1);
            return F1;
        } else {
            return null;
        }
    }

    /**
     * 3建立FP树
     *
     * @param transRecords
     * @param F1
     * @return
     * @author fengbo
     */
    public TreeNode buildFPTree(List<List<String>> transRecords, ArrayList<TreeNode> F1) {
        TreeNode root = new TreeNode(); // 创建树的根节点
        for (List<String> transRecord : transRecords) {
            LinkedList<String> record = sortByF1(transRecord, F1);
            TreeNode subTreeRoot = root;
            TreeNode tmpRoot = null;
            if (root.getChildren() != null) {
                while (!record.isEmpty()
                        && (tmpRoot = subTreeRoot.findChild(record.peek())) != null) {
                    tmpRoot.countIncrement(1);
                    subTreeRoot = tmpRoot;
                    record.poll();
                }
            }
            addNodes(subTreeRoot, record, F1);
        }
        return root;
    }

    /**
     * 3.1把事务数据库中的一条记录按照F1（频繁1项集）中的顺序排序
     *
     * @param transRecord
     * @param F1
     * @return
     * @author fengbo
     */
    public LinkedList<String> sortByF1(List<String> transRecord, ArrayList<TreeNode> F1) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String item : transRecord) {
            for (int i = 0; i < F1.size(); i++) {
                TreeNode tnode = F1.get(i);
                if (item.equals(tnode.getName())) {
                    map.put(item, i);
                }
            }
        }
        ArrayList<Entry<String, Integer>> al = new ArrayList<Entry<String, Integer>>(map.entrySet());
        Collections.sort(al, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
                return arg0.getValue() - arg1.getValue();
            }
        });
        LinkedList<String> rest = new LinkedList<String>();
        for (Entry<String, Integer> entry : al) {
            rest.add(entry.getKey());
        }
        return rest;
    }

    /**
     * 3.2 把若干个节点作为指定指定节点的后代插入树中
     *
     * @param ancestor
     * @param record
     * @param F1
     * @author fengbo
     */
    public void addNodes(TreeNode ancestor, LinkedList<String> record, ArrayList<TreeNode> F1) {
        if (record.size() > 0) {
            while (record.size() > 0) {
                String item = record.poll();
                TreeNode leafnode = new TreeNode(item);
                leafnode.setName(item);
                leafnode.setCount(1);
                leafnode.setParent(ancestor);
                ancestor.addChild(leafnode);

                for (TreeNode f1 : F1) {
                    if (f1.getName().equals(item)) {
                        while (f1.getNextSameNode() != null) {
                            f1 = f1.getNextSameNode();
                        }
                        f1.setNextSameNode(leafnode);
                        break;
                    }
                }

                addNodes(leafnode, record, F1);
            }
        }
    }

    /**
     * 4. 从FPTree中找到所有的频繁模式
     *
     * @param root
     * @param F1
     * @return
     * @author fengbo
     */
    public Map<List<String>, Integer> findFP(TreeNode root, ArrayList<TreeNode> F1) {
        Map<List<String>, Integer> fp = new HashMap<List<String>, Integer>();
        Iterator<TreeNode> iter = F1.iterator();
        while (iter.hasNext()) {
            TreeNode curr = iter.next();
            // 寻找cur的条件模式基CPB，放入transRecords中
            List<List<String>> transRecords = new LinkedList<List<String>>();
            TreeNode backnode = curr.getNextSameNode();
            while (backnode != null) {
                int counter = backnode.getCount();
                List<String> prenodes = new ArrayList<String>();
                TreeNode parent = backnode;
                // 遍历backnode的祖先节点，放到prenodes中
                while ((parent = parent.getParent()).getName() != null) {
                    prenodes.add(parent.getName());
                }
                while (counter-- > 0) {
                    transRecords.add(prenodes);
                }
                backnode = backnode.getNextSameNode();
            }

            // 生成条件频繁1项集
            ArrayList<TreeNode> subF1 = buildF1Items(transRecords);
            // 建立条件模式基的局部FP-tree
            TreeNode subRoot = buildFPTree(transRecords, subF1);

            // 从条件FP-Tree中寻找频繁模式
            if (subRoot != null) {
                Map<List<String>, Integer> prePatterns = findPrePattern(subRoot);
                if (prePatterns != null) {
                    Set<Entry<List<String>, Integer>> ss = prePatterns.entrySet();
                    for (Entry<List<String>, Integer> entry : ss) {
                        entry.getKey().add(curr.getName());
                        fp.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return fp;
    }

    /**
     * 4.1 从一棵FP-Tree上找到所有的前缀模式
     *
     * @param root
     * @return
     * @author fengbo
     */
    public Map<List<String>, Integer> findPrePattern(TreeNode root) {
        Map<List<String>, Integer> patterns = null;
        List<TreeNode> children = root.getChildren();
        if (children != null) {
            patterns = new HashMap<List<String>, Integer>();
            for (TreeNode child : children) {
                // 找到以child为根节点的子树中的所有长路径（所谓长路径指它不是其他任何路径的子路径）
                LinkedList<LinkedList<TreeNode>> paths = buildPaths(child);
                if (paths != null) {
                    for (List<TreeNode> path : paths) {
                        Map<List<String>, Integer> backPatterns = combination(path);
                        Set<Entry<List<String>, Integer>> entryset = backPatterns.entrySet();
                        for (Entry<List<String>, Integer> entry : entryset) {
                            List<String> key = entry.getKey();
                            int c1 = entry.getValue();
                            int c0 = 0;
                            if (patterns.containsKey(key)) {
                                c0 = patterns.get(key).byteValue();
                            }
                            patterns.put(key, c0 + c1);
                        }
                    }
                }
            }
        }

        // 过滤掉那些小于MinSup的模式
        Map<List<String>, Integer> rect = null;
        if (patterns != null) {
            rect = new HashMap<List<String>, Integer>();
            Set<Entry<List<String>, Integer>> ss = patterns.entrySet();
            for (Entry<List<String>, Integer> entry : ss) {
                if (entry.getValue() >= minSup) {
                    rect.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return rect;
    }

    /**
     * 4.1.1 找到从指定节点（root）到所有可达叶子节点的路径
     *
     * @param root
     * @return
     * @author fengbo
     */
    public LinkedList<LinkedList<TreeNode>> buildPaths(TreeNode root) {
        LinkedList<LinkedList<TreeNode>> paths = null;
        if (root != null) {
            paths = new LinkedList<LinkedList<TreeNode>>();
            List<TreeNode> children = root.getChildren();
            if (children != null) {
                // 在从树上分离单条路径时，对分叉口的节点，其count也要分到各条路径上去
                // 条件FP-Tree是多枝的情况
                if (children.size() > 1) {
                    for (TreeNode child : children) {
                        int count = child.getCount();
                        LinkedList<LinkedList<TreeNode>> ll = buildPaths(child);
                        for (LinkedList<TreeNode> lp : ll) {
                            TreeNode prenode = new TreeNode(root.getName());
                            prenode.setCount(count);
                            lp.addFirst(prenode);
                            paths.add(lp);
                        }
                    }
                }
                // 条件FP-Tree是单枝的情况
                else {
                    for (TreeNode child : children) {
                        LinkedList<LinkedList<TreeNode>> ll = buildPaths(child);
                        for (LinkedList<TreeNode> lp : ll) {
                            lp.addFirst(root);
                            paths.add(lp);
                        }
                    }
                }
            } else {
                LinkedList<TreeNode> lp = new LinkedList<TreeNode>();
                lp.add(root);
                paths.add(lp);
            }
        }
        return paths;
    }

    /**
     * 4.1.2 生成路径path中所有元素的任意组合，并记下每一种组合的count--其实就是组合中最后一个元素的count， 因为我们的组合算法保证了树中
     *
     * @param path
     * @return
     * @author fengbo
     */
    public Map<List<String>, Integer> combination(List<TreeNode> path) {
        if (path.size() > 0) {
            // 从path中移除首节点
            TreeNode start = path.remove(0);
            // 首节点自己可以成为一个组合，放入rect中
            Map<List<String>, Integer> rect = new HashMap<List<String>, Integer>();
            List<String> li = new ArrayList<String>();
            li.add(start.getName());
            rect.put(li, start.getCount());

            Map<List<String>, Integer> postCombination = combination(path);
            if (postCombination != null) {
                Set<Entry<List<String>, Integer>> set = postCombination.entrySet();
                for (Entry<List<String>, Integer> entry : set) {
                    // 把首节点之后元素的所有组合放入rect中
                    rect.put(entry.getKey(), entry.getValue());
                    // 首节点并上其后元素的各种组合放入rect中
                    List<String> ll = new ArrayList<String>();
                    ll.addAll(entry.getKey());
                    ll.add(start.getName());
                    rect.put(ll, entry.getValue());
                }
            }

            return rect;
        } else {
            return null;
        }
    }

    /**
     * 打印频繁1项集
     *
     * @param F1
     * @author fengbo
     */
    public void printF1(List<TreeNode> F1) {
        System.out.println("F-1 set: ");
        for (TreeNode item : F1) {
            System.out.print(item.getName() + ":" + item.getCount() + "\t");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * 打印FP-Tree
     *
     * @param root
     * @author fengbo
     */
    public void printFPTree(TreeNode root) {
        printNode(root);
        List<TreeNode> children = root.getChildren();
        if (children != null && children.size() > 0) {
            for (TreeNode child : children) {
                printFPTree(child);
            }
        }
    }

    /**
     * 打印树上单个节点的信息
     *
     * @param node
     * @author fengbo
     */
    public void printNode(TreeNode node) {
        if (node.getName() != null) {
            System.out.print("Name:" + node.getName() + "\tCount:" + node.getCount() + "\tParent:"
                    + node.getParent().getName());
            if (node.getNextSameNode() != null)
                System.out.print("\tNextSameNode:" + node.getNextSameNode().getName());
            System.out.print("\tChildren:");
            node.printChildrenName();
            System.out.println();
        } else {
            System.out.println("FPTreeRoot");
        }
    }

    /**
     * 打印最终找到的所有频繁模式集
     *
     * @param patterns
     */
    public void printFreqPatterns(Map<List<String>, Integer> patterns) {
        System.out.println();
        System.out.println("最小支持度：" + this.getMinSup());
        System.out.println("满足最小支持度的所有频繁模式集如下：");
        Set<Entry<List<String>, Integer>> ss = patterns.entrySet();
        for (Entry<List<String>, Integer> entry : ss) {
            List<String> list = entry.getKey();
            for (String item : list) {
                System.out.print(item + " ");
            }
            System.out.print("\t" + entry.getValue());
            System.out.println();
        }
    }

}
