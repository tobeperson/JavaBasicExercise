package com.company.tobeace;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;

public class LeetCodeDemo {


    public boolean backspaceCompare(String S, String T) {
        String S1="";
        String T1="";
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i)=='#'){
                if (S1.length()!=0) S1=S1.substring(0,S1.length()-1);
            }else{
                S1=S1+S.charAt(i);
            }
        }
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i)=='#'){
                if (T1.length()!=0) T1=T1.substring(0,T1.length()-1);
            }else{
                T1=T1+T.charAt(i);
            }
        }
        return S1.equals(T1);
    }

    public boolean backspaceCompare1(String S, String T) {
            if (S == null || T == null) {
                return false;
            }

        /*
            从后向前，遍历并比较两个字符串：
                (1)首先遍历S(或是 先遍历T也行)：
                    1、遇到'#'，就记录个数(sWell++)，让指针前移
                    2、若不是'#'，但sWell大于0(表示还有未抵消的'#')，则抵消当前字符，让指针前移
                    3、若上述两点都不满足，则结束当前循环，进行后续步骤
                (2)遍历T，如上进行操作
                (3)比较当前S和T的字符，若不相等，则返回false
                (4)往复循环如上步骤，直至任何一个字符串遍历完毕
         */
            int sWell = 0;  // 记录S中，还未抵消的#个数
            int tWell = 0;  // 记录T中，还未抵消的#个数
            int indexS = S.length() - 1;
            int indexT = T.length() - 1;
            while (indexS >= 0 || indexT >= 0) {
                //(1)首先遍历S(或是 先遍历T也行)：
                //    1、遇到'#'，就记录个数(sWell++)，让指针前移
                //    2、若不是'#'，但sWell大于0(表示还有未抵消的'#')，则抵消当前字符，让指针前移
                //    3、若上述两点都不满足，则结束当前循环，进行后续步骤
                while (indexS >= 0) {
                    if (S.charAt(indexS) == '#') {
                        sWell++;
                        indexS--;
                    } else if (sWell > 0) {
                        sWell--;
                        indexS--;
                    } else {
                        break;
                    }
                }

                // (2)遍历T，如上进行操作
                while (indexT >= 0) {
                    if (T.charAt(indexT) == '#') {
                        tWell++;
                        indexT--;
                    } else if (tWell > 0) {
                        tWell--;
                        indexT--;
                    } else {
                        break;
                    }
                }

                // (3)比较当前S和T的字符，若不相等，则返回false
                if (indexS >= 0 && indexT >= 0) {
                    if (S.charAt(indexS) != T.charAt(indexT)) {
                        return false;
                    }
                } else {
                    if (indexS >= 0 || indexT >= 0) {
                        return false;
                    }
                }
                indexS--;
                indexT--;
            }

            return true;
        }
    public static void backspaceComparemain(String[] args) {
        Scanner cin=new Scanner(System.in);
        String S= cin.nextLine();
        String T= cin.nextLine();
        LeetCodeDemo test=new LeetCodeDemo();
        System.out.println(test.backspaceCompare(S,T));
    }
        public static class ListNode {
          int val;
          ListNode next;
          ListNode() {

          }
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public static void reorderList(ListNode head){
        ListNode p=null,p1 = head,p2 = head;
        Stack<ListNode> stack=new Stack<>();
        //判空
        if (head==null || head.next==null || head.next.next==null) return;
        while(p2.next!=null && p2.next.next != null){
            p2=p2.next.next;
            p1=p1.next;
        }
        //奇数情况
        if (p2.next==null){
            p1=p1.next;
            while (p1!=null) {stack.push(p1);p1=p1.next;}
            p2=head;
            while(!stack.isEmpty()) {
                p=p2.next;
                p2.next=stack.pop();
                p2=p2.next;
                p2.next=p;
                p2=p2.next;
            }
            p2.next=null;
        }else{
            //偶数情况
            p1=p1.next;
            while (p1!=null) {stack.push(p1);p1=p1.next;}
            p2=head;
            while(!stack.isEmpty()) {
                p=p2.next;
                p2.next=stack.pop();
                p2=p2.next;
                if (!stack.isEmpty()){
                    p2.next=p;
                    p2=p2.next;
                }
            }
            p2.next=null;
        }
    }

    public static void reorderListmain(String[] args) {
        ListNode head = null,root ,p=null;
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
        for (int i = 0; i < list.size(); i++) {
            root = new ListNode(list.get(i),null);
            if (i==0) head=root;else p.next=root;
            p=root;
        }
        root=head;
        while (root!=null){
            System.out.print(root.val+ " ");
            root=root.next;
        }
        System.out.println("");
        reorderList(head);
        root=head;
        while (root!=null){
            System.out.print(root.val+ " ");
            root=root.next;
        }
    }
    public static boolean isLongPressedName(String name, String typed) {
        int point=0;
        if (name.equals("")&& typed.length()>0 || name.length()>typed.length()) return false;
        for (int i = 0; i < name.length(); i++) {
            while(point<typed.length() && name.charAt(i)!=typed.charAt(point)) {
                if (i != 0 && name.charAt(i-1)==typed.charAt(point)) point++; else return false;
            }
            if (point<typed.length() && name.charAt(i)==typed.charAt(point)) {
                point++;
            }else if(name.length()-i>typed.length()-point){
                return false;
            }
        }
        while(point<typed.length() && name.charAt(name.length()-1)==typed.charAt(point)) point++;
        return point == typed.length();
    }

    public static void isLongPressedNamemain(String[] args) {
        System.out.println(isLongPressedName("111","1"));
    }
    public static void rotate(int[][] matrix) {
        if(matrix.length<=1) return;
        Map<Integer,Boolean> map=new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int ii=i,jj=j,value=matrix[i][j];
                while (!map.containsKey(jj*10000+(matrix.length-ii-1))){
                    int temp=matrix[jj][matrix.length-ii-1];
                    matrix[jj][matrix.length-ii-1]=value;
                    System.out.println(value);
                    int tempi=ii;
                    ii=jj;jj=matrix.length-tempi-1;
                    map.put(ii*10000+jj,true);
                    value=temp;
                }
            }
        }
    }
    public static void printmatrix(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[");
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("]");
        }
    }

    public static void rotatemain(String[] args) {
        int[][] arr = new int[][]{{1, 2}, {4, 5}};
        System.out.println(arr.length);//输出行数
        System.out.println(arr[0].length);//输出列数
        printmatrix(arr);
        rotate(arr);
        printmatrix(arr);
    }


    private static  List<List<Integer>> findpermute_first(List<List<Integer>> ans, int[] nums,List<Integer> single, Set hashset) {
        for (int i = 0; i < nums.length; i++) {
            if (! hashset.contains(i)){
                hashset.add(i);
                single.add(nums[i]);
                if (single.size()==nums.length){
                    List<Integer> temp= new ArrayList<>();
                    for (int i1 = 0; i1 < single.size(); i1++) {
                        temp.add(single.get(i1));
                    }
                    ans.add(temp);
                }else {
                    findpermute(ans, nums,single);
                }
                hashset.remove(i);
                single.remove(single.indexOf(nums[i]));
            }
        }
        return ans;
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        findpermute(ans,nums,new ArrayList<>());
        return ans;
    }
    private static  void findpermute(List<List<Integer>> ans, int[] nums,List<Integer> single) {
        if (single.size()==nums.length) ans.add(new ArrayList<>(single));
        for (int num : nums) {
            if (!single.contains(num)) {
                single.add(num);  // 动态维护数组
                findpermute(ans, nums, single); // 继续递归填下一个数
                single.remove((Integer) num);   // 撤销操作,回溯
            }
        }
    }


    private static  void findpermuteUnique_first(List<List<Integer>> ans, int[] nums,List<Integer> single) {
        if (single.size()==nums.length)
        {
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<single.size();i++) temp.add(nums[single.get(i)]);
            int flag=0;
            for (List<Integer> an : ans){
                flag=0;
                for (int i = 0; i < an.size(); i++) if (an.get(i).equals(temp.get(i))) flag++;
                if (flag==temp.size()) break;
            }
            if (flag!=temp.size()) ans.add(temp);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!single.contains(i)) {
                single.add(i);  // 动态维护数组
                findpermuteUnique_first(ans, nums, single); // 继续递归填下一个数
                single.remove((Integer) i);   // 撤销操作,回溯
            }
        }
    }
    private static boolean[] vis ;
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        vis=new boolean[nums.length];
        List<Integer> perm=new ArrayList<>();
        Arrays.sort(nums);
        findpermuteUnique(ans,0,nums,perm);
        return ans;
    }

    private static void findpermuteUnique(List<List<Integer>> ans,int idx, int[] nums,List<Integer> perm) {
        if(idx == nums.length){
            ans.add(new ArrayList<>(perm));
        }
        for (int i = 0; i < nums.length; i++) {
            if(vis[i] || i>0 && nums[i-1]==nums[i] && vis[i-1]) continue;
            perm.add(nums[i]);
            vis[i]=true;
            findpermuteUnique(ans,idx+1,nums,perm);
            perm.remove(idx);  //移除的是序号
            vis[i]=false;
        }
    }
    public static void findpermuteUniquemain(String[] args) {
        int[] nums=new int[]{1,1,3,4};
        List<List<Integer>> ans= permuteUnique(nums);
        for (int i = 0; i < ans.size(); i++) {
            if (! ans.get(i).isEmpty()) System.out.println(Arrays.toString(ans.get(i).toArray()));
        }
    }
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int [] bin =new int [101];
        int [] smaller =new int [nums.length];
       // int [] sortnums=new int[nums.length];
       // System.arraycopy(nums,0,sortnums,0,nums.length);
       // Arrays.sort(sortnums);
        for (int num : nums) {
            bin[num]++;
        }
        int temp=bin[0],p;
        for (int i = 1; i < 101; i++) {
            p=bin[i];
            bin[i]=temp;
            temp=temp+p;
        }
        for (int i = 0; i < nums.length; i++) {
            smaller[i] = nums[i]==0 ? 0 : bin[nums[i]];
        }
        return smaller;
    }

    public static void smallerNumbersThanCurrentmain(String[] args) {
        int [] test=new int[] {1,2,2,2,5,6};
        test= smallerNumbersThanCurrent(test);
        for (int i : test) {
            System.out.print(i+" ");
        }
    }
    public static int searchInsert(int[] nums, int target) {
        int left=0,right= nums.length,mid;
        if (target<=nums[0]) return 0;
        if (target>nums[nums.length-1]) return nums.length;
        while (left + 1 != right){
            mid=(left+right)/2;
          //  System.out.println(left +" " +right+" "+ nums[mid]+" "+mid);
            if (nums[mid]>target){
                right = mid;
            }else if(nums[mid]<target){
                left = mid;
            }else{
                return mid;
            }
        }
        return right;
    }

    public static void searchInsertmain(String[] args) {
        int [] array = new int []{1,3,4,5};
        System.out.println(searchInsert(array,3));
    }
    public static boolean isValidSudoku(char[][] board) {
        int [] bin = new int[10];
        Integer num = null;
        for (int i = 0; i < 9; i++) {
            Arrays.fill(bin,0);
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    num=Integer.parseInt(String.valueOf(board[i][j]));
                    if (bin[num]!=0) return false; else bin[num]++;
                }
            }
            Arrays.fill(bin,0);
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    num = Integer.parseInt(String.valueOf(board[j][i]));
                    if (bin[num] != 0) return false;
                    else bin[num]++;
                }
            }
            Arrays.fill(bin,0);
            int left=( i % 3 ) * 3,right=( i / 3 ) * 3;
            for (int i1 = 0; i1 < 3; i1++) {
                for (int j1 = 0;  j1< 3; ++j1) {
                    if (board[left+i1][right+j1] != '.') {
                        num = Integer.parseInt(String.valueOf(board[left + i1][right + j1]));
                        if (bin[num] != 0) return false;
                        else bin[num]++;
                    }
                }
            }
        }
        return true;
    }

    public static void isValidSudokumain(String[] args) {
        char[][] board=new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(isValidSudoku(board));
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<Integer> combina= new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> freq = new ArrayList<int[]>();
        Arrays.sort(candidates);
        for (int candidate : candidates)
            if (freq.isEmpty() || candidate!=freq.get(freq.size()-1)[0]){
                freq.add(new int[]{candidate,1});
            }else{
                ++freq.get(freq.size()-1)[1];
            }
        combination(target,ans,combina,freq,0);
        return ans;
    }

    private static void combination(int target, List<List<Integer>> ans, ArrayList<Integer> combina,List<int[]> freq, int idx) {
        if (target==0){ ans.add(new ArrayList<>(combina));return; }
        if (idx==freq.size() || target < freq.get(idx)[0])  return;
        // 直接跳过
        combination(target,ans,combina,freq,idx+1);
        // 选择当前数
        int most=Math.min(target/freq.get(idx)[0],freq.get(idx)[1]);
        for (int i = 1; i <= most; i++) {
            combina.add(freq.get(idx)[0]);
            combination( target - freq.get(idx)[0] * i, ans, combina, freq, idx + 1);
        }
        for (int i = 0; i < most; i++) {
            combina.remove(combina.size()-1);
        }
    }

    public static void combinationmain(String[] args) {
        int [] candidates=new int[] {2,5,2,1,2};
        List<List<Integer>> ans=combinationSum(candidates,5);
        for (List<Integer> an : ans) {
            for (Integer integer : an) {
                System.out.print(integer+ " ");
            }
            System.out.println();
        }
    }
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> hashmap=new HashMap<>();
        boolean flag=true;
        for (int i = 0; i < arr.length; i++) {
            if (hashmap.containsKey(arr[i])){
                hashmap.put(arr[i],1);
            }else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        int [] arr=new int[] {1,2,2,1,1,3};
        System.out.println(uniqueOccurrences(arr));
    }
}
