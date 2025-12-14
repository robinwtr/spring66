package com.atguigu.bean;

import com.atguigu.anno.Bean;
import com.atguigu.anno.Di;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationApplicationContext implements ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();
    private static String rootPath;
    @Override
    public Object getBean(Class clazz) {
        return beans.get(clazz) ;
    }

    //创建有参数构造，传递包路径，设置包扫描规则
    //当前包及其子包，哪个类有@Bean注解，把这个类通过反射实例化

    public AnnotationApplicationContext(String basePackage){
        try {
            String packagePath = basePackage.replaceAll("\\.","\\\\");
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                //System.out.println(filePath);
                //获取包前面路径的部分，进行字符串截取
                rootPath = filePath.substring(0,filePath.length()-packagePath.length());
                //System.out.println(rootPath);
                //包扫描
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loadDi();

    }


    //
    private void loadDi(){
        //实例化对象在map集合中，得到，每个对象value，每个对象的属性都获得
        Set<Map.Entry<Class, Object>> entries = beans.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            Object object = entry.getValue();
            //获取class对象
            Class<?> clazz = object.getClass();
            //获取每个对象属性
            Field[] declaredFields = clazz.getDeclaredFields();
            //遍历得到每个属性
            for (Field field : declaredFields) {
                Di annotation = field.getAnnotation(Di.class);
                if (annotation != null) {
                    //私有属性 设置为可以设置
                    field.setAccessible(true);
                    //如果有Di注解，把对象进行设置（注入）
                    try {
                        //往对象中设置属性   这其实是对象的注入 也就是注入的是一个实例化的对象
                        field.set(object,beans.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        //判断属性是否有@Di标签
    }
    //包扫描 Bean实例化
    private void loadBean(File file) throws Exception {
        //判断当前是否是文件夹
        if(file.isDirectory()) {
            //获取文件夹里面所有的内容
            File[] childrenfiles = file.listFiles();
            //判断文件夹里面为空则返回
            if (childrenfiles == null || childrenfiles.length == 0)  { return;}
            //不为空就遍历文件夹所有内容
            for (File child : childrenfiles) {
                //对于遍历得到的每一个File对象，继续判断，如果还是文件夹，递归
                if (child.isDirectory()){ loadBean(child);}
                else {
                    //遍历得到的file对象不是文件夹，是文件
                    //得到包路径+类名称-字符串截取
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length() - 1);
                    //判断当前文件类型是否是.class文件
                    if (pathWithClass.endsWith(".class")) {
                        //如果是class类型，把路径中的\换成.  把.class去掉 得到 com.atguigu.service.UserServiceImpl
                        String allName = pathWithClass.replaceAll("\\\\", "\\.")
                                .replace(".class", "");

                        //判断类上面是否有注解@Bean，如果有则实例化
                        //获取类的Class
                        Class<?> clazz = Class.forName(allName);
                        //判断不是接口
                        if (!clazz.isInterface()) {
                            //判断类上面是否有注解@Bean
                            Bean annotation = clazz.getAnnotation(Bean.class);
                            if (annotation != null) {
                                //实例化
                                Object instance = clazz.getConstructor().newInstance();
                                //放入到map中
                                //当前类有接口  让接口的class作为map的key  没有接口则自己的class作为key
                                if (clazz.getInterfaces().length > 0) {
                                    beans.put(clazz.getInterfaces()[0],instance);
                                } else {
                                    beans.put(clazz,instance);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
