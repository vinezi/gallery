package com.gallery.art.server.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Редактирование тэгов")
public class EditTag {

    @Schema(description = "Название тэга")
    private String name;
}
