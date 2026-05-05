/*
* Copyright (C) 2026 Talha Berktan Baş
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*/
package me.ainalrami.juztracker.backend.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.With;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table("users")
@With
public record User(
    @Id UUID id,
    String username,
    String password) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return username.equalsIgnoreCase("admin") ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) : Collections.emptyList();
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}