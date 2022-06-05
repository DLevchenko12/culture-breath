package com.culturebreathexhibitionsback.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authorized_users")
@Getter
@Setter
@PrimaryKeyJoinColumn(
        name = "user_id",
        foreignKey = @ForeignKey(name = "authorized_user_user_id_fk")
)
public class AuthorizedUser extends User {
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
