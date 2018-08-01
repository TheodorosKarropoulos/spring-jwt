package com.tkarropoulos.jwtdemo.utils;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@MappedSuperclass
public class PersistableEntity implements Persistable<Long> {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Convert(converter = DateConverter.class)
    private Long createdDate;

    protected PersistableEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if(obj instanceof PersistableEntity && !this.isNew() && !((PersistableEntity) obj).isNew())
            return ((PersistableEntity) obj).getId().equals(this.id);

        return super.equals(obj);
    }
}
