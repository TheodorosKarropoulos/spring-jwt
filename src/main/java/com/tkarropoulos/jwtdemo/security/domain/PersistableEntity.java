package com.tkarropoulos.jwtdemo.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tkarropoulos.jwtdemo.utils.DateConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@MappedSuperclass
abstract public class PersistableEntity implements Persistable<Long> {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Convert(converter = DateConverter.class)
    @Column(updatable = false)
    private Long createdDate;

    @LastModifiedDate
    @Convert(converter = DateConverter.class)
    private Long updatedDate;

    public PersistableEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    @JsonIgnore
    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    @JsonIgnore
    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    @Transient
    @JsonIgnore
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
