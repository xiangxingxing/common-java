package com.levi.netty;

import java.nio.IntBuffer;

public class BufferSamp {
    public static void main(String[] args) {
        buffer1();
    }

    public static void buffer1(){
        //创建一个Buffer，大小为5
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++){
            intBuffer.put(i * 3);
        }

        intBuffer.flip();//反转读取
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());//get方法 -> 索引自动+1
        }
    }
}
