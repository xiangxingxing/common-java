package com.levi.netty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/*
 * file -> stream -> channel -> buffer详见basic/io/BufferedSamp
 * */
public class BufferSamp {
    public static void main(String[] args) {
        //buffer1();
        //bufferDataType();
        //bufferReadOnly();
        mappedByteBufferSamp();
    }

    public static void buffer1() {
        //创建一个Buffer，大小为5
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 3);
        }

        intBuffer.flip();//反转读取
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());//get方法 -> 索引自动+1
        }
    }

    public static void bufferDataType() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.putInt(129);
        byteBuffer.putChar('l');
        byteBuffer.putLong(9);
        byteBuffer.putFloat((float) 5.21);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getFloat());
    }

    public static void bufferReadOnly() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i = 0; i < 16; i++) {
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

    }

    /*
    * MappedByteBuffer
    *   1.可以让文件直接在内存（堆外内存）修改，操作系统不需要拷贝一次【性能高】
    *   2.由channel.map()方法获取
    * */
    public static void mappedByteBufferSamp(){
        try(RandomAccessFile randomAccessFile = new RandomAccessFile("levi.txt", "rw");
            //获取对应的通道
            FileChannel fileChannel = randomAccessFile.getChannel()) {
            /*
            * 参数
            *   1.FileChannel.MapMode.READ_WRITE:文件读写模式
            *   2.position:可以直接修改的起始位置
            *   3.size：映射到内存的大小，即levi.txt的多少字节映射到内存
            *
            * 实际类型：DirectByteBuffer
            * */
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            mappedByteBuffer.put(0, (byte)'J');
            mappedByteBuffer.put(3,(byte)'a');

            System.out.println("修改成功！");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
