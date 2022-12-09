package pairmatching.service;

import java.util.List;
import pairmatching.CrewNameReader;
import pairmatching.model.Course;

public class CrewService {

    public List<String > findCrewsByCourse(Course course) {
        return CrewNameReader.getCrewNames(course);
    }
}
