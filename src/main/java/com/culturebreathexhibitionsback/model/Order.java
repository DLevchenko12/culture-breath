package com.culturebreathexhibitionsback.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "orders",
        uniqueConstraints = {
                @UniqueConstraint(name = "orders_name_key", columnNames = "id")
        })
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "order_price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private AuthorizedUser user;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_exhibitions",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    foreignKey = @ForeignKey(name = "orders_exhibitions_order_id_fk")),
            inverseJoinColumns = @JoinColumn(name = "exhibition_id",
                    foreignKey = @ForeignKey(name = "orders_exhibitions_exhibition_id_fk"))
    )
    private Set<Exhibition> exhibitions = new HashSet<>();
}
