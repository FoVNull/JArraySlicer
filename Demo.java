import top.hikki.JArraySlicer;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String...GL) {
        int[] arr={0,1,2,3,4,5,6,7,8,9};
        double[] doubleArr={0.2,1.4,2.3,3.1,4.55,5.8,6.34};
        List<Integer> list= Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        String[] strArr={"aa","bb","cc","dd","ee"};
        /*
        支持:基本类型数组，Object[],java.util.List 类型的切片
         */
        JArraySlicer<Integer> slicer=new JArraySlicer<>(arr);
        JArraySlicer<Double> doubleSlicer=new JArraySlicer<>(doubleArr);
        JArraySlicer<Integer> listSlicer=new JArraySlicer<>(list);
        JArraySlicer<String> strSlicer=new JArraySlicer<>(strArr);

        System.out.println(slicer.arraySlice("-1"));
        //[9]
        System.out.println(doubleSlicer.arraySlice("::-1"));
        //[6.34, 5.8, 4.55, 3.1, 2.3, 1.4, 0.2]
        System.out.println(listSlicer.arraySlice(":3"));
        //[0, 1, 2]
        System.out.println(strSlicer.arraySlice("-3:-1"));
        //[cc, dd]
        System.out.println(slicer.arraySlice("::"));
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        /*
        #1 当表达式无冒号时  "x"
            x>=0 取 array[x]  x<0 取从右往左数的第x个
         */
        //=======================================================================
        /*
        #2 单冒号 (begin:end)，或双冒号未指定步进 (begin:end:)
         */

        //end>=begin 取切片[begin,end);默认步进为+1
        System.out.println(slicer.arraySlice("1:5"));
        //[1, 2, 3, 4]

        // end<begin 同样取切片[begin,end);默认步进为-1
        System.out.println(slicer.arraySlice("5:1"));
        //[5, 4, 3, 2]
        //*这条与python中的切片不同，请注意 python3: print(array[5:1]) => []，它默认步进为+1

        //begin或end<0
        System.out.println(slicer.arraySlice("-3:-1"));
        //[7, 8]       "-3:-1" => [倒数第三个,倒数第一个)

        // ":end","begin:"默认等价于 "0:end","begin:array.length"
        System.out.println(slicer.arraySlice("-3::"));
        //[7, 8, 9]
        System.out.println(slicer.arraySlice(":-1"));
        //[0, 1, 2, 3, 4, 5, 6, 7, 8]

        //=======================================================================

        /*
        #3 双冒号有步进 begin:end:step
        原则上严格按照步进执行。切片范围是 [begin,end);
         */
        //step>0 默认为 begin=0;end=array.length;
        System.out.println(slicer.arraySlice("1::2"));
        //[1, 3, 5, 7, 9]
        //步进为负数则从右向左遍历
        System.out.println(slicer.arraySlice("-2::-2"));
        //[8, 6, 4, 2, 0]

        //*与python不同的是，这里增加三种异常处理
        //当 step=0时，无意义操作，抛出异常
        slicer.arraySlice("1::0");
        //top.hikki.SlicerException

        //当 step>0 但是begin>=end时，得到的一定是空集，所以抛出异常
        //step<0 同理   在python3中： print(array[8:1:2]) => []
        slicer.arraySlice("8:1:2");
        slicer.arraySlice("1:8:-2");
        //top.hikki.SlicerException
        System.out.println(slicer.arraySlice("1:8:2"));
        //[1, 3, 5, 7]

        /*
        #4 对无效表达式抛出异常。
         */

    }
}
