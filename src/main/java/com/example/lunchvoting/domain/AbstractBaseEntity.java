package com.example.lunchvoting.domain;

import com.example.lunchvoting.util.HasId;
import org.hibernate.Hibernate;

import javax.persistence.*;

/**
 *  Database entity with id
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements DomainObject, HasId {

    public static final int START_SEQ = 100000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    protected Integer version;

    public AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Version
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion() {
//        this.version = version;
//    }

    public String toString() {
        return String.format("Entity %s (%d)", getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(Hibernate.getClass(o))) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
