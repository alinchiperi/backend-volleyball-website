package ro.usv.ip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        team.setLocation(teamDto.getLocation());
        team.setName(teamDto.getName());
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

    public TeamDto updateTeam(TeamDto teamDto, MultipartFile file) {
        Team team = teamRepository.findById(teamDto.getId()).orElseThrow(()->new UsernameNotFoundException("Team not found"));

        if(file != null){
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
        }
        team.setLocation(teamDto.getLocation());
        team.setName(teamDto.getName());
        team = teamRepository.save(team);

        return TeamDto.from(team);
    }

    public TeamDto delete(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Team not found"));
        teamRepository.delete(team);
        return TeamDto.from(team);
    }
}
