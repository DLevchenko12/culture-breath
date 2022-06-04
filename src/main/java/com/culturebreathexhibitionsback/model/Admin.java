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
@Table(name = "admins")
@Getter
@Setter
@PrimaryKeyJoinColumn(
        name = "user_id",
        foreignKey = @ForeignKey(name = "admin_user_id_fk")
)
public class Admin extends User{
    @OneToMany
    @JoinColumn(name = "admin_user_fk")
    private List<Exhibition> exhibitions = new ArrayList<>();
}
