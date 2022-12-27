public class Main {
    public static void main(String[] args) {
         lectionTeacher a = new MathTeacher(40000, "Ivanov A.A.");
         laboratoryTeacher b = new PhysicsTeacher(40000, "Ivanov A.A.");

         a.markAbsent();
         b.setMark();

         PhysicsTeacher c = new PhysicsTeacher(40000, "Ivanov A.A.");
         setMark(c);
        }

    public static void setMark(laboratoryTeacher t){     t.setMark();     }
    }
