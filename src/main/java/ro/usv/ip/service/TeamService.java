package ro.usv.ip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.TeamDto;
import ro.usv.ip.model.Team;
import ro.usv.ip.repository.TeamRepository;

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

}
