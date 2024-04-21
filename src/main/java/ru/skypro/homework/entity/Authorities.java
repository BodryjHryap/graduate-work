package ru.skypro.homework.entity;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Entity
@Component
@SessionScope()
@Table(name = "authorities")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @Enumerated(EnumType.STRING)
    private Role authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getAuthority() {
        return authority;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authority=" + authority +
                '}';
    }
}
