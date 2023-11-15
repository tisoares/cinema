package br.com.as.cinema.external.domain;

import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.enums.SeatStatus;
import br.com.as.cinema.internal.domain.enums.SeatType;
import br.com.as.cinema.utils.ShortUuidUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatRequest {
    private String uuid = ShortUuidUtils.generateKey();
    private Room room;
    private String row;
    private Integer number;
    private SeatType type = SeatType.NORMAL;
    private SeatStatus status = SeatStatus.ACTIVE;
}
