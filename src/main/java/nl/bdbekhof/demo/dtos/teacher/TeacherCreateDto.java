package nl.bdbekhof.demo.dtos.teacher;

public record TeacherCreateDto(String firstName, String lastName, String subject, String email) {
    public String getEmail() {
        return this.email;
    }
}
