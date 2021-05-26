package generator;

import generator.Projmember;
import generator.ProjmemberExample;
import generator.ProjmemberKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjmemberDao {
    long countByExample(ProjmemberExample example);

    int deleteByExample(ProjmemberExample example);

    int deleteByPrimaryKey(ProjmemberKey key);

    int insert(Projmember record);

    int insertSelective(Projmember record);

    List<Projmember> selectByExample(ProjmemberExample example);

    Projmember selectByPrimaryKey(ProjmemberKey key);

    int updateByExampleSelective(@Param("record") Projmember record, @Param("example") ProjmemberExample example);

    int updateByExample(@Param("record") Projmember record, @Param("example") ProjmemberExample example);

    int updateByPrimaryKeySelective(Projmember record);

    int updateByPrimaryKey(Projmember record);
}