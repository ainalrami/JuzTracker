/*
* Copyright (C) 2026 Talha Berktan Baş
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*/
package me.ainalrami.juztracker.backend.model.utility;

import com.github.f4b6a3.uuid.UuidCreator;
import me.ainalrami.juztracker.backend.model.User;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserBeforeConvertCallback implements BeforeConvertCallback<User> {
    @Override
    public Publisher<User> onBeforeConvert(User user, SqlIdentifier table) {
        return Mono.just(user.id() == null ? user.withId(UuidCreator.getTimeOrderedEpoch()) : user);
    }
}