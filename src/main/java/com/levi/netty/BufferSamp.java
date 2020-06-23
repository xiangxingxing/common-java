package com.levi.netty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/*
 * file -> stream -> channel -> buffer详见basic/io/BufferedSamp
 * */
public class BufferSamp {
    public static void main(String[] args) {
        //buffer1();
        //bufferDataType();
        //bufferReadOnly();
        //mappedByteBufferSamp();
        bufferArraySamp();
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
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

    }

    /*
     * MappedByteBuffer
     *   1.可以让文件直接在内存中（堆外内存）修改，操作系统不需要拷贝一次【性能高】
     *   2.由channel.map()方法获取
     * */
    public static void mappedByteBufferSamp() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("levi.txt", "rw");
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
            mappedByteBuffer.put(0, (byte) 'J');
            mappedByteBuffer.put(3, (byte) 'a');

            System.out.println("修改成功！");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * scattering:将数据写入buffer时，可以使用buffer数组，依次写入
     * gathering:从buffer读取数据时，可以采用buffer数组，依次读
     * */
    public static void bufferArraySamp() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

            serverSocketChannel.socket().bind(inetSocketAddress);
            //创建buffer数组
            ByteBuffer[] buffers = new ByteBuffer[2];
            buffers[0] = ByteBuffer.allocate(5);
            buffers[1] = ByteBuffer.allocate(3);

            //等待客户端连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            int messageLength = 8;

            while (true) {
                int byteRead = 0;

                while (byteRead < messageLength) {
                    long l = socketChannel.read(buffers);
                    byteRead += l;
                    //使用流打印当前所有buffer的position和limit
                    Arrays.asList(buffers).stream().map(buf -> "position = " + buf.position() +
                            ", limit = " + buf.limit()).forEach(System.out::println);
                }

                //对所有的buffer进行flip
                Arrays.asList(buffers).forEach(buf -> buf.flip());

                //将数据读出显示到客户端
                long byteWrite = 0;
                while (byteWrite < messageLength){
                    long l = socketChannel.write(buffers);
                    byteWrite += l;

                    //使用流打印
                    Arrays.asList(buffers).forEach(buf -> buf.clear());

                    System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWrite);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
