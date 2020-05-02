package fileordatabase;

import java.util.List;
import java.util.Set;

/**
 * Created by Elham on 4/7/2020.
 */
public interface IDao {


    List<Person> getAllPerson();

    void delete();

    void insert();

    void update(Person person);

}
