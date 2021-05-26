package generator;

import generator.Promiscuouspackage;
import generator.PromiscuouspackageExample;
import generator.PromiscuouspackageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PromiscuouspackageDao {
    long countByExample(PromiscuouspackageExample example);

    int deleteByExample(PromiscuouspackageExample example);

    int deleteByPrimaryKey(PromiscuouspackageKey key);

    int insert(Promiscuouspackage record);

    int insertSelective(Promiscuouspackage record);

    List<Promiscuouspackage> selectByExample(PromiscuouspackageExample example);

    Promiscuouspackage selectByPrimaryKey(PromiscuouspackageKey key);

    int updateByExampleSelective(@Param("record") Promiscuouspackage record, @Param("example") PromiscuouspackageExample example);

    int updateByExample(@Param("record") Promiscuouspackage record, @Param("example") PromiscuouspackageExample example);

    int updateByPrimaryKeySelective(Promiscuouspackage record);

    int updateByPrimaryKey(Promiscuouspackage record);
}