package generator;

import generator.Featureenvy;
import generator.FeatureenvyExample;
import generator.FeatureenvyKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeatureenvyDao {
    long countByExample(FeatureenvyExample example);

    int deleteByExample(FeatureenvyExample example);

    int deleteByPrimaryKey(FeatureenvyKey key);

    int insert(Featureenvy record);

    int insertSelective(Featureenvy record);

    List<Featureenvy> selectByExample(FeatureenvyExample example);

    Featureenvy selectByPrimaryKey(FeatureenvyKey key);

    int updateByExampleSelective(@Param("record") Featureenvy record, @Param("example") FeatureenvyExample example);

    int updateByExample(@Param("record") Featureenvy record, @Param("example") FeatureenvyExample example);

    int updateByPrimaryKeySelective(Featureenvy record);

    int updateByPrimaryKey(Featureenvy record);
}