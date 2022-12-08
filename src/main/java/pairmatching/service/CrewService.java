package pairmatching.service;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.CrewNameReader;
import pairmatching.model.Course;
import pairmatching.model.Crew;

public class CrewService {

    public List<Crew> findCrewsByCourse(Course course) {
        return CrewNameReader.getCrewNames(course)
                .stream().map(name -> new Crew(course, name))
                .collect(Collectors.toList());
    }
}
