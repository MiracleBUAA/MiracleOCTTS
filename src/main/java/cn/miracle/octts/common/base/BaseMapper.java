package cn.miracle.octts.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hf on 2017/6/25.
 */
public interface BaseMapper<E> {

    /**
     * find add query
     */
    List<E> findAll();

    /**
     * find an Entity by id
     *@param id id of the entity
     * */
    E findById(Integer id);

    /**
     * select count
     * */
    int selectCount(E record);

    /**
     * insert into database
     * @param record object record
     * */
    void insert(E record);

    /**
     * delete record
     * @param record object to be deleted
     * */
    void delete(E record);

    /**
     * delete record by id
     * @param id id to delete
     * */
    void deleteById(Integer id);
}
