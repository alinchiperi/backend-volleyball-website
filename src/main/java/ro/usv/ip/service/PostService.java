package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.PostCommentDto;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.exceptions.PostNotFoundException;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.PostComment;
import ro.usv.ip.model.PostImage;
import ro.usv.ip.model.Tag;
import ro.usv.ip.repository.PostImageRepository;
import ro.usv.ip.repository.PostRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final TagService tagService;


    public PostDto create(PostDto postDto, MultipartFile[] files) {
        List<Tag> tags = tagService.tagsFrom(postDto.getTags());

        Post post = postFor(postDto.getTitle(), tags);

        post.setContent(postDto.getContent());
        post.setCreatedBy(postDto.getCreatedBy());
        post.setUnderTitle(postDto.getUnderTitle());
        post.setLink(postDto.getLink());
        post.setCategory(postDto.getCategory());

        Post result = postRepository.save(post);

        saveImagesInDataBase(result.getId(), files);

        return PostDto.from(result);
    }


    /**
     * Create post for a specific tag
     * @param title title of posts
     * @param tags  list of tags
     * @return  post created
     */
    static Post postFor(String title, List<Tag> tags) {
        Post post = new Post();
        post.setTitle(title);
        post.getTags().clear();
        post.getTags().addAll(tags);
        post.setCreatedOn(LocalDateTime.now());
        return post;
    }

    /**
     * Find post by tag name
     * @param tagName tag name
     * @return list of posts
     */
    public List<PostDto> findPostByTagName(String tagName) {
        List<Post> posts = postRepository.findByTags_Name(tagName);

        return changePostToDto(posts);
    }

    public PostDto update( PostDto postDto) {
        long postId = postDto.getId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        List<Tag> tags = tagService.tagsFrom(postDto.getTags());

        post.setTitle(postDto.getTitle());
        post.getTags().clear();
        post.getTags().addAll(tags);

        post.setContent(postDto.getContent());
        post.setCreatedBy(postDto.getCreatedBy());
        post.setUnderTitle(postDto.getUnderTitle());
        post.setCategory(post.getCategory());

        Post result = postRepository.saveAndFlush(post);
        return PostDto.from(result);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }


    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return PostDto.from(post);
    }

    /**
     * Method to change a list of post to postDto object
     * @param posts list of posts
     * @return  list of postDto
     */
    static List<PostDto> changePostToDto(List<Post> posts) {
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }

    /**
     * Method to return post in ascended order
     * @return list of post
     */
    public List<PostDto> getPostsOrderByDate() {

        List<Post> posts = postRepository.findAllOrderByCreatedOnAsc();

        return changePostToDto(posts);

    }

    /**
     * Method to save in database images of a post
     * @param postId id of post
     * @param files images to save
     */
    public void saveImagesInDataBase(Long postId, MultipartFile[] files) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        for (MultipartFile file : files) {
            PostImage postImage = new PostImage();

            try {
                byte[] byteObjects = new byte[file.getBytes().length];
                int j = 0;
                for (byte b : file.getBytes()) {
                    byteObjects[j++] = b;
                }

                postImage.setPhoto(byteObjects);
                postImage.setName(file.getOriginalFilename());
                postImage.setPost(post);

                postImageRepository.save(postImage);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public List<byte[]> getPostImages(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        List<byte[]> images= new ArrayList<>();

        List<PostImage> postImages = post.getPostImage();

        for (PostImage img: postImages){
            images.add(img.getPhoto());
        }

        return images;
    }

    public void addComment(Long postId, PostCommentDto postCommentDto) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException(postId));
        PostComment postComment = commentFrom(postCommentDto);
        postComment.setPost(post);
        post.getComments().add(postComment);
        postRepository.save(post);
    }

    private PostComment commentFrom(PostCommentDto postCommentDto){
        return new PostComment(postCommentDto.getCreatedBy(), postCommentDto.getContent());

    }


    //for local save
    /*public String saveImagesInFolder(Long id,  MultipartFile[] files){

        if (files == null || files.length == 0) {
            throw new RuntimeException("You must select at least one file for uploading");
        }

        String path = makePostsDirectory();

        path+= id;

        File directory = new File(path);
        directory.mkdir();


        return "";

    }*/

   /* public String makePostsDirectory() {
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        String imagePath = absolutePath + "/src/main/resources/images/posts/";
        File directory = new File(imagePath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        return imagePath;
    }*/

}
