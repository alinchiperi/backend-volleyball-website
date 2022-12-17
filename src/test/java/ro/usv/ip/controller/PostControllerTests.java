package ro.usv.ip.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.dto.TagDto;
import ro.usv.ip.service.PostService;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;
    LocalDateTime currentTime = LocalDateTime.now();

    @Test
    void itShouldRetrievePost() throws Exception {
        PostDto post = getPost();
        Long postId = post.getId();

        given(postService.findPostById(postId)).willReturn(post);

        mockMvc.perform(get("/api/post/" + postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(postId))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value("Post title"))
                .andExpect(jsonPath("content").value("Post content"))
                .andExpect(jsonPath("createdBy").value("Alin Chiperi"))
                .andExpect(jsonPath("createdOn").value(currentTime.format(ISO_LOCAL_DATE_TIME)))
                .andExpect(jsonPath("tags.size()").value(2))
                .andExpect(jsonPath("tags[0].id").value(1L))
                .andExpect(jsonPath("tags[0].name").value("csm"))
                .andExpect(jsonPath("tags[1].id").value(2L))
                .andExpect(jsonPath("tags[1].name").value("Suceava"));
    }

    private PostDto getPost() {
        return PostDto.builder()
                .id(1L)
                .title("Post title")
                .content("Post content")
                .createdBy("Alin Chiperi")
                .underTitle("Post under title")
                .createdOn(currentTime)
                .tags(List.of(
                        TagDto.builder().id(1L).name("csm").build(),
                        TagDto.builder().id(2L).name("Suceava").build()))
                .build();
    }


}
