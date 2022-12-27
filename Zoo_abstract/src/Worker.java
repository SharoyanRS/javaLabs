public abstract class Worker {
    private int salary;
    private String name;

    Worker(int salary,String name){
        this.salary=salary;
        this.name=name;
    }

    protected int getSalary() {return salary;}
    protected String getName() {return name;}
    protected void goWork(){};
}
