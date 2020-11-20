package dao;

import org.apache.ibatis.annotations.Param;

public interface TypeMapper {

    /**
     * 查询类型名
     * @param id
     * @return
     */
    String getTypeNameByID (@Param("id") int id);

    /**
     * 查询类型编号
     * @param name
     * @return
     */
    Integer getTypeIDByName (@Param("name") String name);
}
