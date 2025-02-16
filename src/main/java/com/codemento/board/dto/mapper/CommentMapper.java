package com.codemento.board.dto.mapper;

import com.codemento.board.dto.comment.CommentListDto;
import com.codemento.board.dto.comment.CommentSaveDto;
import com.codemento.board.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "parentComment.id", target = "parentCommentId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "author.nickname", target = "authorNickname")
    CommentListDto toListDto(Comment comment);

    @Mapping(source ="post.id", target = "postId")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "parentComment.id", target = "parentCommentId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    CommentSaveDto toSaveDto(Comment comment);


    @InheritInverseConfiguration
    Comment listToEntity(CommentListDto dto);

    @InheritInverseConfiguration
    Comment saveToEntity(CommentSaveDto commentSaveDto);

}
