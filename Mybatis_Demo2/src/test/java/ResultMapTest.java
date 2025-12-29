import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerOutput;
import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

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
     * b. 分布查询
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
}
