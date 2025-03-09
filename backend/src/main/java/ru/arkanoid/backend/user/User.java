package ru.arkanoid.backend.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.arkanoid.backend.user.finance.Wallet;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface User extends UserDetails {
    UUID getUuid();
    Role getRole();
    String getEmail();
    String getReferral();
    Wallet getWallet();

    void setPassword(String newPassword);
    void setUsername(String newUsername);

    @Override
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    default boolean isAccountNonExpired() {
        return true;
    }

    @Override
    default boolean isAccountNonLocked() {
        return true;
    }

    @Override
    default boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    default boolean isEnabled() {
        return true;
    }
}
