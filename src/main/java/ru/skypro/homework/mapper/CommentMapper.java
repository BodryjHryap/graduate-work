package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.generatedDto.CommentDto;
import ru.skypro.homework.dto.generatedDto.CommentsDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "author.id")
    Comment commentDtoToComment(CommentDto commentDto);

    @Mapping(source = "size", target = "count")
    @Mapping(source = "commentList", target = "results")
    CommentsDto commentListToCommentsDto(Integer size, List<Comment> commentList);
}
