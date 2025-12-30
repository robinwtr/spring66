import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerOutput;
import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.DynamicSqlMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ResultMapTest {

    /**
     * 数据库字段名与dao中实体类的属性名不一致的情况 就是在sql语言中设置别名
     * a. 为字段起别名 别名与属性名一致
     * b. 设置全局配置 将_自动设置为驼峰
     * <setting name="mapUnderscoreToCameLCase"  value="true"/>
     */

    /**
     * resultMap：设置自定义映射关系
     * id：唯一标识，不能重复
     * type：设置映射关系中实体类的类型
     * 子标签：
     * id：主键的映射关系
     * result：普通属性的映射关系
     * property：设置映射关系中的属性名 必须是type属性所设置的实体类类型中的属性名
     * column：设置映射关系中的字段名 必须是sql语句查询出的字段名
     */


    /**
     * 处理多对一的映射关系：
     * a. 级联属性赋值
     * b. association标签  专门处理多对一的关系
     * c. 分步查询
     *
     * 处理一对多的映射关系
     * a. collection
     * b. 分步查询
     */
    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.selectAllEmp();
        emps.forEach(emp -> System.out.println(emp));
    }


    @Test
    public void test2() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.selectEmpAndDeptById(3);
        System.out.println(emp);
    }

    @Test
    public void test3() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.selectEmpAndDeptByStepOne(4);
        System.out.println(emp.getEmpName());
        System.out.println("=======================");
        System.out.println(emp.getDept());
    }

    @Test
    public void test4() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.selectDeptAndEmp(1);
        System.out.println(dept);
    }

    @Test
    public void test5() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(3);
        System.out.println(dept);
    }


    /**
     * 动态sql：
     * 1. if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到sql语句中
     * 2. where:
     * where标签中有内容时 会自动生成where关键字，并且将内容前多余的and或or去掉
     * where标签中没有内容时 where标签没有任何效果 内容后的and和or不能去掉
     * 3. trim标签
     * prefix/suffix：将trim标签中内容前面或后面添加指定内容
     * prefixOverrides/suffixOverrides: 将trim标签前面后面添加去掉某些内容
     * 4. choose when otherwise 相当于 if...else if...else
     * when至少有一个
     * otherwise至多有一个
     * 5. foreach
     *
     */
    @Test
    public void test6() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        List<Emp> empByCondition = mapper.getEmpByCondition(new Emp(null, "", 32, "", ""));
        for (Emp emp : empByCondition) {
            System.out.println(emp);
        }
    }

    @Test
    public void test7() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        List<Emp> empByCondition = mapper.getEmpByChoose(new Emp(null, "", 32, "", ""));
        for (Emp emp : empByCondition) {
            System.out.println(emp);
        }
    }

    @Test
    public void test8() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        int i = mapper.deleteMoreByArray(new Integer[]{6, 7, 8});
        System.out.println(i);
    }

    @Test
    public void test9() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp1 = new Emp(null, "ZZ", 23, "男", "0@qq.com");
        Emp emp2 = new Emp(null, "grf", 25, "男", "9@qq.com");
        Emp emp3 = new Emp(null, "dyy", 24, "女", "8@qq.com");
        Emp emp4 = new Emp(null, "zjx", 23, "女", "7@qq.com");
        Emp emp5 = new Emp(null, "wtr", 23, "男", "6@qq.com");
        List<Emp> list = Arrays.asList(emp1, emp2, emp3, emp4, emp5);
        int i = mapper.insertMoreByList(list);
        System.out.println(i);
    }

    /**
     * 一级cache的范围是sqlsession  任意一次增删改都会清空缓存
     */
    @Test
    public void test10() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp empById1 = mapper.getEmpById(1);
        System.out.println(empById1);
        //mapper.insertEmp(new Emp(null,"qwe",12,"男","11@qq.com"));
        sqlSession.clearCache();
        Emp empById2 = mapper.getEmpById(1);
        System.out.println(empById2);
    }

    /**
     * 二级缓存开启的条件
     * 1 在核心配置文件中，设置全局配置属性cacheEnable=“true”默认为trye
     * 2 在映射文件中设置标签《cache/>
     * 3 二级缓存必须在sqlSession关闭火锅提交之后才有效
     * 4 查询的数据所转换的实体类对象必须实现序列化接口
     */

    @Test
    public void test11() {
        try{
            InputStream is = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            SqlSession session1 = build.openSession(true);
            CacheMapper mapper1 = session1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpById(1));
            session1.close();
            SqlSession session2 = build.openSession(true);
            CacheMapper mapper2 = session2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getEmpById(1));
            session2.close();
        }
        catch (Exception e) {

        }

    }
}
