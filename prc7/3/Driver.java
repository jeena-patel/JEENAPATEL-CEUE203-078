
public class Driver {
    public static void main(String[] args) throws Exception {

        String[] headers = {
            "student_name",
            "student_age"
        };

        String[] data = {
            "Riya",
            "20"
        };

        Student student = new Student();

        ColumnMapper.populate(student, headers, data);

        student.display();
    }
}
