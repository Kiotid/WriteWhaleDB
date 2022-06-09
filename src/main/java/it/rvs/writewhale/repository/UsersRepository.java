package it.rvs.writewhale.repository;

import it.rvs.writewhale.model.Users;
import org.springframework.data.repository.CrudRepository;

//repository that extends CrudRepository
public interface UsersRepository extends CrudRepository<Users, Integer>
{
}