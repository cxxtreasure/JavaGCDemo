public class JavaGCTest {
    private static final Integer _1MB=1024*1024;
    public static void main(String[] args){
        testAllocation();
        testAllocation2();
        //testPretenureSizeThreshold();
        //testAllocationBigObject();
       // testAllocationBigBigObject();
    }

    /**
     * 对象优先在Eden分配
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
     */
    public static void testAllocation(){

        byte[] allocation1,allocation2;
        allocation1=new byte[6*_1MB];
        allocation2=new byte[4*_1MB];//出现一次Minor GC
    }
    //-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
    //-XX:+UseSerialGC

    /**s0分配
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
     */
    public static void testAllocation2(){

        byte[] allocation1;
        byte[] allocation2;
        byte[] allocation3;
        byte[] allocation4;

        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation4=new byte[4*_1MB];//出现一次Minor GC;
    }
    /**大于S0/S1的空间，但小于eden的空间。
     * 大对象直接进入老年代
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation=new byte[4*_1MB];
    }
    /**
     * 大于S0/S1的空间，且大于eden的空间
     * -verbose:gc -Xms20M -Xmx20M -Xmn18M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
     */
    public static void testAllocationBigObject(){
        byte[] allocation1,allocation2;
        allocation1=new byte[6*_1MB];
        allocation2=new byte[6*_1MB];
    }
    /**
     * 大于eden、S0/S1的空间，且大于eden的空间
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
     */
    public static void testAllocationBigBigObject(){
        byte[] allocation1;
        allocation1=new byte[50*_1MB];
    }
}
