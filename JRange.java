package top.hikki;

import java.util.ArrayList;

public class JRange {
    public static ArrayList<Integer> range(int begin, int end, int step) {
        ArrayList<Integer> arr = new ArrayList<>();

        ExpressionChecker checker = new ExpressionChecker();
        try {
            String expression = begin<end?"0:"+(end-begin)+":"+step:(begin-end)+":0"+":"+step;
            checker.ExpressionCheck(expression, Math.abs(begin-end));

            if(begin < end){
                for(int i=begin;i<end;i+=step) arr.add(i);
            }else{
                for(int i=begin;i>end;i+=step) arr.add(i);
            }
        }catch (SlicerException e){
            e.printStackTrace();
        }

        return arr;
    }
    public static ArrayList<Integer> range(int begin, int end){
        int step = begin<end?1:-1;
        return range(begin,end,step);
    }
}
