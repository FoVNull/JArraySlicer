# JArraySlicer
Java Array Slicer
<br>jar download:http://hikki.top/archives/

Java实现python的数组切片功能，表达式逻辑相同，在python切片的基础上进行了一些微调。

使用JArraySlicer类进行操作。基本操作↓，详情见Demo.java
<pre>
int[] array={1,2,3};
JArraySlicer<Integer> slicer=new JArraySlicer<>(array);
ArrayList<Integer> res=slicer.arraySlice("begin:end:step");
</pre>

