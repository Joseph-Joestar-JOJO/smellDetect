package generator;

import com.ruoyi.system.domain.Projcommit;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjcommitDao {
    long countByExample(ProjcommitExample example);

    int deleteByExample(ProjcommitExample example);

    int deleteByPrimaryKey(ProjcommitKey key);

    int insert(Projcommit record);

    int insertSelective(Projcommit record);

    List<Projcommit> selectByExample(ProjcommitExample example);

    Projcommit selectByPrimaryKey(ProjcommitKey key);

    int updateByExampleSelective(@Param("record") Projcommit record, @Param("example") ProjcommitExample example);

    int updateByExample(@Param("record") Projcommit record, @Param("example") ProjcommitExample example);

    int updateByPrimaryKeySelective(Projcommit record);

    int updateByPrimaryKey(Projcommit record);
}