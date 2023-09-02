package br.com.as.cinema.internal.domain;

import br.com.as.cinema.utils.ShortUuidUtils;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", insertable = true, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CinemaConstants.DEFAULT_SEQUENCE_NAME)
    @JsonIgnore
    protected Long id;

    @Column(name = "uuid", insertable = true, updatable = false, unique = true, length = 25)
    private String uuid = ShortUuidUtils.generateKey();

//    @CreatedBy
    @Column(name = "created_by", insertable = true, updatable = false, unique = false, length = 150)
    protected String createdBy;

//    @LastModifiedBy
    @Column(name = "updated_by", insertable = false, updatable = true, unique = false, length = 150)
    protected String updatedBy;

    @CreatedDate
    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "created_at", insertable = true, updatable = false, unique = false)
    protected LocalDate createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "updated_at", insertable = false, updatable = true, unique = false)
    protected LocalDate updatedAt;

}
