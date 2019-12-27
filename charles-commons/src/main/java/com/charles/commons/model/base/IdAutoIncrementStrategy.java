package com.charles.commons.model.base;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-12-27
 * Time: 14:06
 */
public class IdAutoIncrementStrategy {

    @Id
    @KeySql(dialect = IdentityDialect.INFORMIX)
    @Column(insertable = false, updatable = false)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
