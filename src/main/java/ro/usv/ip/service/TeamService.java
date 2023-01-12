package ro.usv.ip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.TeamDto;
import ro.usv.ip.model.Team;
import ro.usv.ip.repository.TeamRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream().map(TeamDto::from).collect(Collectors.toList());
    }

    public TeamDto createTeam(TeamDto teamDto, MultipartFile file) {
        Team team = new Team();
        team.setLocation(team.getLocation());
        team.setName(team.getName());
        try {
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            team.setLogo(byteObjects);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        team = teamRepository.save(team);

        return TeamDto.from(team);

    }
}
