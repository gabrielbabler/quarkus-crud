package com.github.gabrielbabler;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product extends PanacheEntity {
    public String name;
    public BigDecimal value;
    @CreationTimestamp
    public Date createdAt;
    @CreationTimestamp
    public Date updatedAt;
}
