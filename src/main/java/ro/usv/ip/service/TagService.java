package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.TagDto;
import ro.usv.ip.exceptions.TagNotFoundException;
import ro.usv.ip.model.Tag;
import ro.usv.ip.repository.TagRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tags) {
        this.tagRepository = tags;
    }

    public List<Tag> tagsFrom(List<TagDto> tags) {

        List<TagDto> tagDtos = Optional.ofNullable(tags).orElse(Collections.emptyList());
        return tagDtos.stream().map(this::toTag).collect(Collectors.toList());
    }

    private Tag toTag(TagDto tagDto) {
        Tag tag;
        Optional<Tag> tagFromName = tagRepository.findByName(tagDto.getName());
        if (tagDto.getId() != null) {
            tag = tagRepository.findById(tagDto.getId())
                    .orElseThrow(() -> new TagNotFoundException(tagDto.getId()));
        } else if(tagFromName.isPresent()) {
            tag=tagFromName.get();
        }
        else {
            tag = tagDto.toTag();
            tagRepository.save(tag);

        }
        return tag;
    }
}