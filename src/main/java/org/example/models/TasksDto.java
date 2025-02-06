package org.example.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;


import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Task")
@Table(name = "task", indexes = {
        @Index(name = "task_id_index", columnList = "id")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude
public class TasksDto implements Serializable {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    @JsonAlias("user_id")
    private UUID userId;

    @Column(name = "name", nullable = false)
    @JsonAlias("name")
    private String name;

    @Column(name = "description", nullable = false)
    @JsonAlias("description")
    private String description;

    @Column(name = "insert_date")
    @JsonIgnore
    private LocalDateTime insertedAt;

    @Column(name = "end_date")
    @JsonAlias("endAt")
    private LocalDateTime endAt;

    @Column(name = "status")
    private String status;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @PrePersist
    public void prePersist() {
        this.insertedAt = LocalDateTime.now(ZoneId.of( "Africa/Johannesburg"));
        this.id = UUID.randomUUID();
    }
}
