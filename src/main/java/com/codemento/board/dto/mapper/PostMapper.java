package com.codemento.board.dto.mapper;

import com.codemento.board.dto.post.PostDetailDto;
import com.codemento.board.dto.post.PostListDto;
import com.codemento.board.dto.post.PostRequestDto;
import com.codemento.board.entity.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.nickname", target = "authorNickname")
    PostListDto toPostListDto(Post post);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.nickname", target = "authorNickname")
    PostDetailDto toPostDetailDto(Post post);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.nickname", target = "authorNickname")
    PostRequestDto toPostRequestDto(Post post);


    @InheritInverseConfiguration
    Post listDtoToEntity(PostListDto postListDto);

    @InheritInverseConfiguration
    Post detailDtoToEntity(PostDetailDto postDetailDto);

    @InheritInverseConfiguration
    Post requestDtoToEntity(PostRequestDto postRequestDto);
}
