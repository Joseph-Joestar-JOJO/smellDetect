package generator;

import generator.Blob;
import generator.BlobExample;
import generator.BlobKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlobDao {
    long countByExample(BlobExample example);

    int deleteByExample(BlobExample example);

    int deleteByPrimaryKey(BlobKey key);

    int insert(Blob record);

    int insertSelective(Blob record);

    List<Blob> selectByExample(BlobExample example);

    Blob selectByPrimaryKey(BlobKey key);

    int updateByExampleSelective(@Param("record") Blob record, @Param("example") BlobExample example);

    int updateByExample(@Param("record") Blob record, @Param("example") BlobExample example);

    int updateByPrimaryKeySelective(Blob record);

    int updateByPrimaryKey(Blob record);
}