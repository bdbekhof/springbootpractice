package nl.bdbekhof.demo.dtos.teacher;

public record TeacherUpdateDto(Long id, String firstName, String lastName, String email, String subject) {

    public String getEmail() {
        return this.email;
    }
}
