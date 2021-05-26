package generator;

import generator.Smellcomment;
import generator.SmellcommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmellcommentDao {
    long countByExample(SmellcommentExample example);

    int deleteByExample(SmellcommentExample example);

    int deleteByPrimaryKey(Integer commentid);

    int insert(Smellcomment record);

    int insertSelective(Smellcomment record);

    List<Smellcomment> selectByExample(SmellcommentExample example);

    Smellcomment selectByPrimaryKey(Integer commentid);

    int updateByExampleSelective(@Param("record") Smellcomment record, @Param("example") SmellcommentExample example);

    int updateByExample(@Param("record") Smellcomment record, @Param("example") SmellcommentExample example);

    int updateByPrimaryKeySelective(Smellcomment record);

    int updateByPrimaryKey(Smellcomment record);
}