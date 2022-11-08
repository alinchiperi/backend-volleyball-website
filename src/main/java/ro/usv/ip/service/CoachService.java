package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.CoachDto;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.exceptions.CoachNotFoundException;
import ro.usv.ip.model.Coach;
import ro.usv.ip.repository.CoachRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository ;

    public CoachDto addCoach(CoachDto coachDto){
        Coach coach = new Coach();

        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());

        // TODO: 07.11.2022 coach.setPhoto

        //toDO: find team id by name and add here
        coach = coachRepository.save(coach);
        return CoachDto.from(coach);
    }


    public List<CoachDto> getPlayers() {
        List<Coach> coaches = coachRepository.findAll();
        List<CoachDto> coachDtos = new ArrayList<>();

        for (Coach ch :
                coaches) {
            coachDtos.add(CoachDto.from(ch));
        }

        return coachDtos;
    }

    public CoachDto deleteCoach(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(()-> new CoachNotFoundException(id));
        coachRepository.delete(coach);
        return CoachDto.from(coach);
    }


    public CoachDto updatePlayer(CoachDto coachDto) {
        Coach coach = coachRepository.findById(coachDto.getId()).orElseThrow();
        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());

        // TODO: 07.11.2022 coach.setPhoto

        //toDO: find team id by name and add here
        coach = coachRepository.save(coach);
        return CoachDto.from(coach);
    }
}
