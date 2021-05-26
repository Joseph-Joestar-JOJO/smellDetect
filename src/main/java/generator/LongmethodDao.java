package generator;

import generator.Longmethod;
import generator.LongmethodExample;
import generator.LongmethodKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LongmethodDao {
    long countByExample(LongmethodExample example);

    int deleteByExample(LongmethodExample example);

    int deleteByPrimaryKey(LongmethodKey key);

    int insert(Longmethod record);

    int insertSelective(Longmethod record);

    List<Longmethod> selectByExample(LongmethodExample example);

    Longmethod selectByPrimaryKey(LongmethodKey key);

    int updateByExampleSelective(@Param("record") Longmethod record, @Param("example") LongmethodExample example);

    int updateByExample(@Param("record") Longmethod record, @Param("example") LongmethodExample example);

    int updateByPrimaryKeySelective(Longmethod record);

    int updateByPrimaryKey(Longmethod record);
}