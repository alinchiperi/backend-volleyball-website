package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.CoachDto;
import ro.usv.ip.exceptions.CoachNotFoundException;
import ro.usv.ip.model.Coach;
import ro.usv.ip.model.Player;
import ro.usv.ip.repository.CoachRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;

    public CoachDto addCoach(CoachDto coachDto, MultipartFile file) {
        Coach coach = new Coach();

        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());

        try {
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            coach.setPhoto(byteObjects);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //toDO: find team id by name and add here

        coach = coachRepository.save(coach);
        return CoachDto.from(coach);
    }


    public List<CoachDto> getCoaches() {
        List<Coach> coaches = coachRepository.findAll();
        List<CoachDto> coachDtos = new ArrayList<>();

        for (Coach ch :
                coaches) {
            coachDtos.add(CoachDto.from(ch));
        }

        return coachDtos;
    }

    public CoachDto deleteCoach(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException(id));
        coachRepository.delete(coach);
        return CoachDto.from(coach);
    }


    public CoachDto updateCoach(CoachDto coachDto) {
        Coach coach = coachRepository.findById(coachDto.getId()).orElseThrow();
        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());

        // TODO: 07.11.2022 coach.setPhoto

        //toDO: find team id by name and add here
        coach = coachRepository.save(coach);
        return CoachDto.from(coach);
    }

    public byte[] getCoachImage(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException(id));
        return coach.getPhoto();
    }

    public CoachDto getCoach(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException(id));
        return CoachDto.from(coach);

    }

    @Transactional
    public void updateProfileImage(Long id, MultipartFile file) {
        Coach coach = coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException(id));
        try {
            byte[] byteOnjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteOnjects[i++] = b;
            }
            coach.setPhoto(byteOnjects);
            coachRepository.save(coach);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
