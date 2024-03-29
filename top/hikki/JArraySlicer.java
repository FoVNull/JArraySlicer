package top.hikki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JArraySlicer<T> {
    private List<T> array=new ArrayList<>();
    private final T[] arr;
    private final ExpressionChecker checker=new ExpressionChecker();

    public JArraySlicer(T[] arr){
        Collections.addAll(array, arr);
        this.arr=arr;
    }
    public JArraySlicer(List<T> arr){
        this.array= arr;
        this.arr= (T[]) arr.toArray();
    }

    public JArraySlicer(int...arr) {
        Integer[] temp=new Integer[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(double...arr) {
        Double[] temp=new Double[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(float...arr) {
        Float[] temp=new Float[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(long...arr) {
        Long[] temp=new Long[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(char...arr) {
        Character[] temp=new Character[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(boolean...arr) {
        Boolean[] temp=new Boolean[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(byte...arr){
        Byte[] temp = new Byte[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }


    public ArrayList<T> arraySlice(String SliceExpression){
        int len=arr.length;
        ArrayList<T> res=new ArrayList<>();
        try {
            checker.ExpressionCheck(SliceExpression,arr.length);

            char[] chars=SliceExpression.toCharArray();
            int count=0;
            for(char c:chars){
                if(c==':') ++count;
            }

            if(count==0){
                int pos=Integer.parseInt(SliceExpression);
                if(pos>=0) {
                    res.add(arr[pos]);
                }else{
                    res.add(arr[len+pos]);
                }
            }

            String[] parameter=SliceExpression.split(":");
            if(count==1||(count==2&&parameter.length<=2)){//双冒号不填步进，那么默认为1或-1
                int begin=0;int end=len;
                if(parameter.length>0&&!parameter[0].equals("")) begin=Integer.parseInt(parameter[0]);
                if(parameter.length>1) end=Integer.parseInt(parameter[1]);

                if(begin<0) begin=len+begin;
                if(end<0) end=len+end;

                T[] t;

                if(begin>end) {
                    for(int i=begin;i>end;--i){
                        res.add(arr[i]);
                    }
                }else {
                    t = Arrays.copyOfRange(arr, begin, end);
                    Collections.addAll(res, t);
                }
            }

            if(count==2&&parameter.length>2){
                int begin=0;int end=len;
                if(Integer.parseInt(parameter[2])<0){
                    begin=len-1;end=-1;
                }
                if(!parameter[0].equals("")) {
                    begin=Integer.parseInt(parameter[0]);
                    if(begin<0)  begin=len+begin;
                }
                if(!parameter[1].equals("")) {
                    end=Integer.parseInt(parameter[1]);
                    if(end<0)  end=len+end;
                }

                int step=Math.abs(Integer.parseInt(parameter[2]));

                int flag=0;
                if(begin>=end) {
                    for(int i=begin;i>end;--i){
                        if(flag%step==0) res.add(arr[i]);
                        ++flag;
                    }
                }else{
                    for(int i=begin;i<end;++i){
                        if(flag%step==0) res.add(arr[i]);
                        ++flag;
                    }
                }
            }
        }catch (SlicerException sle){
            sle.printStackTrace();
        }
        return res;
    }

}
