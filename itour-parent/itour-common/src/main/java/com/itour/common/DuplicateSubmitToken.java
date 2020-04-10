package com.itour.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DuplicateSubmitToken {
    boolean save() default true;
}
/**https://www.jb51.net/article/161279.htm
 * https://blog.csdn.net/xsp_happyboy/article/details/80987484
 * 1.自定义注解的约定
 * a.只能定义属性名,不能定义方法;
 * b.属性的可见性只有public和defualt,不写默认defulat;
 * c.属性的类型只能支持：基本数据类型、string、class、enum、Annotation类型及以上类型的数组;
 * d.可以加上defult关键字指明默认值，当某字段不指明默认值时，必须在进行注解标注的时候进行此字段值的指定;
 * e.当使用value作为属性名称时，可以不显式指定value=“xxx”，如可以直接使用@Service("xxxService")
 * 
 * 2.元注解(java中默认实现的专门对注解进行注解的注解。元注解的总数就5个)
 * A.@Target(ElementType.METHOD):表示当前注解的使用范围
 *   METHOD:表示该注解只能注解到方法上;
 *   TYPE:表示该注解注解可注解到类、接口、或者枚举类型上面的;
 * B.@Retention(RetentionPolicy.RUNTIME)：表示当前注解的生命周期;
 *   RUNTIME:表示在程序运行期间依然有效，此时就可以通过反射拿到注解的信息；
 * C.@Documented：当被此注解所注解时，使用javadoc工具生成文档就会带有注解信息;
 * D.@Inherited:
 *    此注解与继承有关,当一个注解添加了该注解后,添加到某个类上的时候,此类的子类也会继承该注解;
 * E:@Repeatable
 *    表示：拥有可以重复注解的能力
 */
