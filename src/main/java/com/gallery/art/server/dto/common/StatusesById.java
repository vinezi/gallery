package com.gallery.art.server.dto.common;

import com.gallery.art.server.enums.Statuses;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Статус удаляемой сущности")
public class StatusesById {

    @Schema(description = "Идентификатор сущности")
    protected Long id;

    @Schema(description = "Статус удаления", enumAsRef = true)
    protected Statuses status;

    @Schema(description = "Сообщение об ошибке")
    protected String errorMessage;
}