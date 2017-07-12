package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Daniel.Diaconu on 17/07/12.
 */
@Table(name = "Jobs")
public class Job {

    @Id(name = "hjk")
    private long id;

    @Column(name = "salary")
    private long salary;

    @Column(name = "max_salary")
    private long max_salary;

    @Column(name = "min_salary")
    private long min_salary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public long getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(long max_salary) {
        this.max_salary = max_salary;
    }

    public long getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(long min_salary) {
        this.min_salary = min_salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", salary=" + salary +
                ", max_salary=" + max_salary +
                ", min_salary=" + min_salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (id != job.id) return false;
        if (salary != job.salary) return false;
        if (max_salary != job.max_salary) return false;
        return min_salary == job.min_salary;
    }


}
