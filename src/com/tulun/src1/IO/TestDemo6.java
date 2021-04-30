package com.tulun.src1.IO;

import java.io.BufferedInputStream;

/**
 * 转换流和缓冲流
 * BufferedInputStram
 */
//统一接口  InputStream
interface Component{
    void method();
}

//被装饰的类 FileInputStream
class ConcreteComponent implements Component {

    @Override
    public void method() {
        System.out.println("原先的功能");
    }
}

//装饰类 FilterInputStream
abstract class Decorate implements Component{
    protected ConcreteComponent com;

    public Decorate(Component component){
        //可以给com初始化
        super();
        this.com = (ConcreteComponent) component;
    }

    public void method(){
        com.method();
    }
}

//具体装饰类 BufferInputStream InputStreamReader
class ConcreteDecorateA extends Decorate{

    public ConcreteDecorateA(Component component) {
        super(component);
    }

    public void methodA(){
        System.out.println("装饰类A拓展的功能");
    }

    public void method(){
        System.out.println("拓展原先功能");
    }
}

//具体装饰类
class ConcreteDecorateB extends Decorate{

    public ConcreteDecorateB(Component component) {
        super(component);
    }

    public void methodB(){
        System.out.println("装饰类B拓展的功能");
    }

    public void method(){
        System.out.println("拓展原先功能");
    }
}
public class TestDemo6 {
    public static void main(String[] args) {

    }
}
