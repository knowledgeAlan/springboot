package com.springboot01.demo;

import com.springboot01.demo.dto.WorkBean;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by zzm on 2018/4/16
 **/
public class ReflectDemo {

    /**
     * 测试class
     */
    static Class<?> clazz = WorkBean.class;
    /**
     * 公用构造方法缓存
     */
    private static final HashMap<String, Constructor<?>> constructors = new HashMap<String, Constructor<?>>();
    /**
     * 公用method缓存
     */
    private static final ConcurrentHashMap<String, MethodCache> methods = new ConcurrentHashMap<String, MethodCache>();

    /**
     * 不经过优化的构造方法性能测试
     */
    @Test
    public void constructorNormalTest() {
        long start = System.currentTimeMillis();
        try {
            Object obj = null;
            for(int i = 0; i < 1000000; i ++) {
                Constructor<?> cons1 = clazz.getConstructor();
                obj = cons1.newInstance();
                Constructor<?> cons2 = clazz.getConstructor(String.class);
                obj = cons2.newInstance("littlehow");
                Constructor<?> cons3 = clazz.getConstructor(String.class, String.class);
                obj = cons3.newInstance("littlehow", "future company");
            }
            System.out.println(obj);//littlehow在future company工作了0年，现在的薪水是0.0
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use time : " + (end - start) + "ms");//平均840毫秒左右
    }

    /**
     * 对单一的进行缓存，速度最快，但功用性不够好
     * 相对于
     * @see constructorNormalTest()方法，性能提升10倍
     * 可见反射中对构造方法的获取非一个非常耗时的操作
     */
    @Test
    public void constrctorBufferOne() {
        long start = System.currentTimeMillis();
        try {
            Constructor<?> cons1 = clazz.getConstructor();
            Constructor<?> cons2 = clazz.getConstructor(String.class);
            Constructor<?> cons3 = clazz.getConstructor(String.class, String.class);
            Object obj = null;
            for(int i = 0; i < 1000000; i ++) {
                obj = cons1.newInstance();
                obj = cons2.newInstance("littlehow");
                obj = cons3.newInstance("littlehow", "future company");
            }
            System.out.println(obj);//littlehow在future company工作了0年，现在的薪水是0.0
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use time : " + (end - start) + "ms");//平均80毫秒左右
    }

    /**
     * key值的计算耗时也比较大，可以再进行优化
     * @param clazz
     * @param arguClass
     * @return
     * @throws NoSuchMethodException
     */
    private static Constructor<?> getConstrucor(Class<?> clazz, Class<?> ...arguClass) throws NoSuchMethodException {
        String key = clazz.getName();
        for (Class<?> argu : arguClass) {
            key = key + argu.getName();//这里会有编译优化
        }
        Constructor<?> constructor = constructors.get(key);
        if (constructor == null) {
            constructor = clazz.getConstructor(arguClass);
            constructors.put(key, constructor);
        }
        return constructor;
    }

    /**
     * 公用缓存，在性能和开发维护中这种处理，在性能要求不是超高的
     * 系统中可以选用该方式进行构造方法的缓存，提升开发速度
     * 效率相对第一个测试性能也提升了一倍多
     */
    @Test
    public void constructorBufferAll() {
        long start = System.currentTimeMillis();
        try {
            Constructor<?> cons1 = clazz.getConstructor();
            Constructor<?> cons2 = clazz.getConstructor(String.class);
            Constructor<?> cons3 = clazz.getConstructor(String.class, String.class);
            Object obj = null;
            for(int i = 0; i < 1000000; i ++) {
                obj = getConstrucor(clazz).newInstance();
                obj = getConstrucor(clazz, String.class).newInstance("littlehow");
                obj = getConstrucor(clazz, String.class, String.class).newInstance("littlehow", "future company");
            }
            System.out.println(obj);//littlehow在future company工作了0年，现在的薪水是0.0
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use time : " + (end - start) + "ms");//平均330毫秒左右
    }

    /**
     * 用class直接newInstance
     * 直接用class的newInstance方式性能非常高，
     * 但是局限于newInstance只能调用无参构造方法;
     * 因为newInstance调用的是其内部的cacheConstructor
     * 所以实例化的时候非常快
     * 所以如果是无参构造方法的话，就不需要单独获取constructor，
     * 因为性能相差无几
     */
    @Test
    public void clazzNewInstance() {
        long start = System.currentTimeMillis();
        try {
            Object obj = null;
            for(int i = 0; i < 1000000; i ++) {
                obj = clazz.newInstance();
            }
            System.out.println(obj);//null在null工作了0年，现在的薪水是0.0
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use time : " + (end - start) + "ms");//平均20毫秒左右
    }

    /**
     * 方法反射示例,方法每次都去反射中寻找
     */
    @Test
    public void methodNormalTest() {
        long start = System.currentTimeMillis();
        try {
            Object obj = getConstrucor(clazz, String.class, String.class).newInstance("littlehow", "old company");
            for (int i = 0; i < 1000000; i ++) {
                Method m = clazz.getDeclaredMethod("setWorkAge", int.class);
                m.invoke(obj, i);
                m = clazz.getDeclaredMethod("setSalary", double.class);
                m.invoke(obj, 3500.0);
            }
            System.out.println(obj);//littlehow在old company工作了999999年，现在的薪水是3500.0
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use time : " + (end - start) + "ms");//平均850毫秒左右
    }

    /**
     * 单独缓存方法，大规模反射应用不易管理
     * 相对非缓存，性能提高了14倍
     */
    @Test
    public void methodBufferOne() {
        long start = System.currentTimeMillis();
        try {
            Method m1 = clazz.getDeclaredMethod("setWorkAge", int.class);
            Method m2 = clazz.getDeclaredMethod("setSalary", double.class);
            Object obj = getConstrucor(clazz, String.class, String.class).newInstance("littlehow", "old company");
            for (int i = 0; i < 1000000; i ++) {
                m1.invoke(obj, i);
                m2.invoke(obj, 3500.0);
            }
            System.out.println(obj);//littlehow在old company工作了999999年，现在的薪水是3500.0
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use time : " + (end - start) + "ms");//平均56毫秒左右
    }

    /**
     * 获取方法,这里只是最初略的写了个获取的方法
     * @param clazz
     * @param argus
     * @return
     */
    private static Method getMethod(Class<?> clazz, String name, Class<?> ...argus) throws NoSuchMethodException {
        MethodCache mc = methods.get(clazz.getName());
        if (mc == null) {
            mc = new MethodCache(clazz);
            methods.put(clazz.getName(), mc);
        }
        return mc.getMethod(name, argus);
    }

    /**
     * 进一步缓存，牺牲内存提升速度，速度提升明显
     */
    static class MethodCache {
        private Class<?> clazz;
        /**
         * 缓存
         */
        private Map<String, Method> methods = new HashMap<String, Method>();
        public MethodCache(Class<?> clazz) {
            this.clazz = clazz;
            Method[] methodArr = clazz.getDeclaredMethods();
            List<String> remove = new ArrayList<String>();
            //私有的method不缓存,static方法不缓存
            for (Method m : methodArr) {
                if (!m.isAccessible() && ((1 & m.getModifiers()) > 0) && ((8 & m.getModifiers()) == 0)) {
                    Method old = methods.put(m.getName(), m);
                    //如果old不为空,表示方法多了重载,需要处理特殊处理
                    if (old != null) {
                        put(old);
                        put(m);
                        remove.add(m.getName());
                    }
                }
            }
            for (String s : remove) {
                methods.remove(s);
            }
        }

        /**
         * 按照参数进行put
         * @param method
         */
        private void put(Method method) {
            Type[] types = method.getParameterTypes();
            String key = method.getName() + types.length;
            for (Type type : types) {
                String str = type.toString();
                if (str.startsWith("class ")) {
                    str = str.substring(6);
                }
                key += str;
            }
            methods.put(key, method);
        }

        /**
         * 获取方法
         * @param name
         * @param argus
         * @return
         * @throws NoSuchMethodException
         */
        public Method getMethod(String name, Class<?> ...argus)  throws NoSuchMethodException{
            Method m = methods.get(name);
            if (m == null) {
                name = name + argus.length;
                for(Class<?> argu : argus) {
                    name = name + argu.getName();
                }
                m = methods.get(name);
            }
            return m;
        }
    }

    /**
     * 因为在大规模反射应用中，
     * @see methodBufferOne()方法并不容易扩展
     * 特别是自己写的动态代理进行aop切入时，
     * 对一些方法进行置换处理时，就需要有个管理的缓存
     */
    @Test
    public void methodBufferAll() {
        long start = System.currentTimeMillis();
        try {
            Object obj = getConstrucor(clazz, String.class, String.class).newInstance("littlehow", "old company");
            //测试是否能获取不同的重载方法
            getMethod(clazz, "workAt").invoke(obj);//littlehow工作了好久了
            getMethod(clazz, "workAt", boolean.class).invoke(obj, false);//littlehow在下午上午工作的时候一直在打瞌睡!
            getMethod(clazz, "workAt", boolean.class, String.class).invoke(obj, true, "一直努力解决问题");//littlehow在上午一直努力解决问题
            for (int i = 0; i < 1000000; i ++) {
                getMethod(clazz, "setWorkAge", int.class).invoke(obj, i);
                getMethod(clazz, "setSalary", double.class).invoke(obj, 3500.0);
            }
            System.out.println(obj);//littlehow在old company工作了999999年，现在的薪水是3500.0
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use time : " + (end - start) + "ms");//平均130毫秒左右
    }
}
