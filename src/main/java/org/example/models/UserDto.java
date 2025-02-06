package org.example.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "task_user")
@Table(name = "task_user", indexes = {
        @Index(name = "user_id_index", columnList = "id")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude
public class UserDto implements Serializable {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    @JsonAlias("name")
    private String name;

    @Column(name = "surname", nullable = false)
    @JsonAlias("surname")
    private String surname;

    @Column(name = "insert_date")
    private LocalDateTime insertedAt;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "role")
    @NotNull
    private String role;

    @PrePersist
    public void prePersist() {
        this.insertedAt = LocalDateTime.now(ZoneId.of( "Africa/Johannesburg"));
        this.id = UUID.randomUUID();
    }
}
