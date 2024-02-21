package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.utils.ShortUuidUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", insertable = true, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CinemaConstants.DEFAULT_SEQUENCE_NAME)
    @JsonIgnore
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "uuid", insertable = true, updatable = false, unique = true, length = 25)
    @EqualsAndHashCode.Include
    private String uuid = ShortUuidUtils.generateKey();

    @CreatedBy
    @Column(name = "created_by", insertable = true, updatable = false, unique = false, length = 150)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", insertable = false, updatable = true, unique = false, length = 150)
    private String updatedBy;

    @CreationTimestamp
    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "created_at", insertable = true, updatable = false, unique = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "updated_at", insertable = false, updatable = true, unique = false)
    private LocalDateTime updatedAt;

}
