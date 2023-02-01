package pl.kurs.programmingschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kurs.programmingschool.model.mapper.*;

@Configuration
public class MapstructConfig {

    @Bean
    public TeacherMapper teacherMapper() {
        return new TeacherMapperImpl();
    }

    @Bean
    public StudentMapper studentMapper() {
        return new StudentMapperImpl();
    }

    @Bean
    public LessonMapper lessonMapper() {
        return new LessonMapperImpl();
    }

}
