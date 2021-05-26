package generator;

import generator.Misplacedclass;
import generator.MisplacedclassExample;
import generator.MisplacedclassKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MisplacedclassDao {
    long countByExample(MisplacedclassExample example);

    int deleteByExample(MisplacedclassExample example);

    int deleteByPrimaryKey(MisplacedclassKey key);

    int insert(Misplacedclass record);

    int insertSelective(Misplacedclass record);

    List<Misplacedclass> selectByExample(MisplacedclassExample example);

    Misplacedclass selectByPrimaryKey(MisplacedclassKey key);

    int updateByExampleSelective(@Param("record") Misplacedclass record, @Param("example") MisplacedclassExample example);

    int updateByExample(@Param("record") Misplacedclass record, @Param("example") MisplacedclassExample example);

    int updateByPrimaryKeySelective(Misplacedclass record);

    int updateByPrimaryKey(Misplacedclass record);
}