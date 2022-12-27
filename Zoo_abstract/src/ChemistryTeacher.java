public class ChemistryTeacher extends Worker implements lectionTeacher, laboratoryTeacher {

    ChemistryTeacher(int salary, String name) {
        super(salary, name);
    }

    public void markAbsent(){}
    public void setMark(){}
    public void startLesson(){}
    public void setHomework(){}
    public void doExperiment(){}
}
