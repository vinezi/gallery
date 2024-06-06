package com.gallery.art.server.dto.tag;

import com.gallery.art.server.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Тэги")
public class Tag extends BaseDto {

    @Schema(description = "Название тэга")
    private String name;
}
