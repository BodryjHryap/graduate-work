package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.generatedDto.CommentDto;
import ru.skypro.homework.dto.generatedDto.CommentsDto;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "pk", target = "id")
    Comment commentDtoToComment(CommentDto commentDto);
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.avatar", target = "authorImage", qualifiedByName = "getUserAvatarLink")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "size", target = "count")
    @Mapping(source = "commentList", target = "results")
    CommentsDto commentsListToCommentsDto(Integer size, List<Comment> commentList);

    @Named("getUserAvatarLink")
    default String getUserAvatarLink(Avatar avatar) {
        return (avatar == null) ? null : "/users/avatar/" + avatar.getId();
    }
}
