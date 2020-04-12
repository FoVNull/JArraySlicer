package top.hikki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JArraySlicer<T> {
    private List<T> array=new ArrayList<>();
    private T[] arr;

    public JArraySlicer(T[] arr){
        Collections.addAll(array, arr);
        this.arr=arr;
    }
    public JArraySlicer(List<T> arr){
        this.array= arr;
        this.arr= (T[]) arr.toArray();
    }

    public JArraySlicer(int[] arr) {
        Integer[] temp=new Integer[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(double[] arr) {
        Double[] temp=new Double[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(float[] arr) {
        Float[] temp=new Float[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(long[] arr) {
        Long[] temp=new Long[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(char[] arr) {
        Character[] temp=new Character[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }

    public JArraySlicer(boolean[] arr) {
        Boolean[] temp=new Boolean[arr.length];
        for(int i=0;i<arr.length;++i) temp[i]=arr[i];
        this.arr= (T[]) temp;
        Collections.addAll(array, this.arr);
    }


    public ArrayList<T> arraySlice(String SliceExpression){
        int len=arr.length;
        ArrayList<T> res=new ArrayList<>();
        try {
            ExpressionCheck(SliceExpression);

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
                boolean inverted=false;
                if(begin>end) {
                    begin^=end;end^=begin;begin^=end;
                    inverted=true;
                }
                t=Arrays.copyOfRange(arr,begin,end);
                Collections.addAll(res, t);
                if(inverted) Collections.reverse(res);
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

    private void ExpressionCheck(String exp) throws SlicerException {
        char[] chars=exp.toCharArray();
        int count=0;
        for(char c:chars){
            if(c==':') ++count;
        }

        if(count==0){
            try{
                Integer.parseInt(exp);
            }catch (Exception e){
                throw new SlicerException(e.getMessage());
            }
        }
        String[] parameter=exp.split(":");
        if(count==2&&parameter.length>2){
            int begin=0;int end=arr.length;
            if(Integer.parseInt(parameter[2])<0){
                begin=arr.length-1;end=0;
            }
            if(!parameter[0].equals("")) begin=Integer.parseInt(parameter[0]);
            if(!parameter[1].equals("")) end=Integer.parseInt(parameter[1]);
            if(begin<0) begin=arr.length+begin;
            if(end<0) end=arr.length+end;
            int step=Integer.parseInt(parameter[2]);

            if(step==0){
                throw new SlicerException("步进为0无意义，请勿使用。begin:end:step(step!=0) slice step cannot be zero");
            }

            if(step>0&&begin>end){
                throw new SlicerException("步进为正，起点需要在终点之前。begin:end:step，IF step>0 THEN begin<=end");
            }else if(step<0&&begin<end){
                throw new SlicerException("步进为负，起点需要在终点之后。begin:end:step，IF step<0 THEN begin>=end");
            }
        }
        if(count>2){
            throw new SlicerException("表达式错误。Invalid expression（example : [begin]:[end]:[step])");
        }
    }
}
