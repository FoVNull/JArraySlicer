package top.hikki;

public class ExpressionChecker<T> {
    protected void ExpressionCheck(String exp,T[] arr) throws SlicerException {
        char[] chars=exp.toCharArray();
        int count=0;
        for(char c:chars){
            if(c==':') ++count;
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
