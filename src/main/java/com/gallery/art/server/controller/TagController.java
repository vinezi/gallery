package com.gallery.art.server.controller;

import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.dto.tag.EditTag;
import com.gallery.art.server.dto.tag.Tag;
import com.gallery.art.server.filters.TagSearch;
import com.gallery.art.server.filters.common.PageInfo;
import com.gallery.art.server.service.ITagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag API")
@RestController
@RequestMapping("tag")
@RequiredArgsConstructor
public class TagController {

    private final ITagService tagService;

    @Operation(summary = "Получение пагинированного списка всех тегов")
    @PostMapping("action/search-all")
    public Page<Tag> findAllTag(@RequestBody PageInfo pageInfo){
        return tagService.findAllTag(pageInfo);
    }

    @Operation(summary = "Поиск тегов")
    @PostMapping("action/search-by-filter")
    public Page<Tag> searchTag(@RequestBody TagSearch filter){
        return tagService.searchTag(filter);
    }

    @Operation(summary = "Получение тега")
    @GetMapping("{id}")
    public Tag findTagById(@Positive @PathVariable("id") Long tagId){
        return tagService.findTagById(tagId);
    }

    @Operation(summary = "Создание тега")
    @PostMapping()
    public Tag saveTag(@RequestBody EditTag tag){
        return tagService.saveTag(tag);
    }

    @Operation(summary = "Обновление тега")
    @PutMapping("{id}")
    public Tag updateTag(@Positive @PathVariable("id") Long tagId, @RequestBody EditTag tag){
        return tagService.updateTag(tagId, tag);
    }

    @Operation(summary = "Удаление тегов")
    @DeleteMapping()
    public List<StatusesById> deleteTags(@RequestParam List<Long> tagIds){
        return tagService.deleteTags(tagIds);
    }
}
