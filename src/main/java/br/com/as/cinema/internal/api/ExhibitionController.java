package br.com.as.cinema.internal.api;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = CinemaConstants.URL_PREFIX_V1 + "exhibitions")
public interface ExhibitionController {

    @GetMapping("/{uuid}")
    Optional<Exhibition> getByUuid(@PathVariable String uuid);

    @GetMapping
    Page<Exhibition> getAll(Pageable pageable);

    @PostMapping
    Exhibition create(@RequestBody Exhibition exhibition);

    @PutMapping("/{uuid}")
    Exhibition update(@PathVariable String uuid, @RequestBody Exhibition exhibition);

}
