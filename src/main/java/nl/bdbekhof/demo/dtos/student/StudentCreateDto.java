package nl.bdbekhof.demo.dtos.student;

public record StudentCreateDto(String firstName, String lastName, String email) {
    public void setId(Object o) {
    }

    public String getEmail() {
        return this.email;
    }
}
