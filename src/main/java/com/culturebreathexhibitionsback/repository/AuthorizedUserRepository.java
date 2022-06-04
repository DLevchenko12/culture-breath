package com.culturebreathexhibitionsback.repository;

import com.culturebreathexhibitionsback.model.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorizedUserRepository extends JpaRepository<AuthorizedUser, UUID> {
}