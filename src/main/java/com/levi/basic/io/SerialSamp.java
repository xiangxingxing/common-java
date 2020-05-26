package com.levi.basic.io;

/*
*   1.类型若序列化需要实现Serializable接口，虚拟机会自动生成一个序列化版本号
*   2.实现方式：ObjectInputStream/ObjectOutputStream
*             new ObjectOutputStream(new FileOutputStream(""))
*   3.建议将序列化版本号写出来，方便更新修改源代码
* 【注意】除枚举类型之外的所有可序列化类都明确声明serialVersionUID值，因为默认的serialVersionUID计算为
*
* */

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialSamp implements Serializable {
    //在设置里的inspection里打钩：自动生成UID
    private static final long serialVersionUID = 948494645849915458L;

    public static void main(String[] args) {

    }
}
