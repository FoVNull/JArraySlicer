# JArraySlicer
## Java Array Slicer
## jar download: http://hikki.top/archives/

Java实现数组切片功能，表达式逻辑与python相同，在python切片的基础上进行了一些微调。  
>使用JArraySlicer类进行操作。基本操作↓，详情见Demo.java
<pre>
int[] array={1,2,3};
JArraySlicer<Integer> slicer=new JArraySlicer<>(array);
ArrayList<Integer> res=slicer.arraySlice("begin:end:step");
</pre>


>2020.09.06更新  
增加range功能，类似于python中的range()，返回值为ArrayList，异常处理完全参照Slicer。
Demo ↓↓↓  
<pre>
JRange.range(5,1)
//output: [5,4,3,2]
JRange.range(1,5)
//output: [1,2,3,4]
JRange.range(8,1,-2)
//output: [8,6,4,2]
</pre>