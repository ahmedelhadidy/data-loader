package eg.com.dataload.repository.env;

import org.springframework.data.repository.CrudRepository;

import eg.com.dataload.model.env.Database;

public interface DatabaseRepository extends CrudRepository<Database, Integer> {

}
