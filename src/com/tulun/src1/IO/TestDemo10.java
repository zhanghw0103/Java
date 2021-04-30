package com.tulun.src1.IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile
 * 自由的访问文件任意位置
 * file poniter -》 文件指针 用来标示当前读取或者写入的位置
 * getFileponiter() 返回文件指针的位置
 * seek(pos) 设置文件指针的位置
 *
 * 构造函数
 * RandomAccessFile(File file, String mode);
 * RandomAccessFile(String name, String mode);
 *
 * mode：4种模式
 * 1) "r": 以只读的方式打开  调用write方法会抛出IOException
 * 2）"rw": 便于读取和写入
 * 3）"rws": 便于读取和写入，同时对文件的内容和元数据的更新都会同步存储
 * 到基础存储设备
 * 4）"rwd": 便于读取和写入，同时对文件的内容的更新都会同步存储到基础存
 * 储设备
 *
 * 课堂练习1：随机访问文件当中的某一部分数据，打印出来500之后的数据
 * 课堂练习2：在指定位置追加"西安图论软件科技有限公司"
 */
public class TestDemo10 {
    public static void main(String[] args) {
        //随机访问文件当中的某一部分数据
        RandomAccessFile raf = null;
        {
            try {
                raf = new RandomAccessFile("a.txt", "r");
                //将文件指针移动到指定位置
                raf.seek(3);

                byte[] buf = new byte[256];
                int hasRead = 0;//真实读取到的字节数 <= buf.length
                while((hasRead=raf.read(buf)) > 0){
                    System.out.println(new String(buf, 0, hasRead));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
