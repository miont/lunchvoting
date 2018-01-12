package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.PersonDao;
import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.security.AuthorizedPerson;
import com.example.lunchvoting.util.ValidationUtil;
import com.example.lunchvoting.util.exception.NotFoundException;

import com.example.lunchvoting.util.mapping.MappingUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.lunchvoting.util.UserUtil.prepareToSave;

/**
 *
 */
@Service("personService")
public class PersonServiceImpl implements PersonService, UserDetailsService {

    @Autowired
    private PersonDao dao;

    @Autowired
    Mapper mapper;

    @Transactional
    @Override
    public PersonDto create(PersonDto person) {
        Assert.notNull(person, ValidationUtil.getArgumentIsNullMsg("person"));
        return mapper.map(dao.save(prepareToSave(mapper.map(person, Person.class))), PersonDto.class);
    }

    @Override
    public PersonDto get(long id) throws NotFoundException {
        Person person = dao.get(id);
        ValidationUtil.checkNotFoundWithId(person, id);
        return mapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, ValidationUtil.getArgumentIsNullMsg("email"));
        Person person = dao.getByEmail(email);
        if(person == null) {
            throw new NotFoundException(String.format("Not found person with email = %d", email));
        }
        return mapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto getByUsername(String username) throws NotFoundException {
        throw new NotImplementedException();   // TODO: implement or delete
    }

    @Override
    public List<PersonDto> getAll() {
        return MappingUtil.mapList(mapper, dao.getAll(), PersonDto.class);
    }

    @Transactional
    @Override
    public void update(PersonDto person) {
        Assert.notNull(person, ValidationUtil.getArgumentIsNullMsg("person"));
        dao.save(prepareToSave(mapper.map(person, Person.class)));
    }

    @Transactional
    @Override
    public void delete(long id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(dao.delete(id), id);
    }

    @Override
    public AuthorizedPerson loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = dao.getByUsername(username);
        if(person == null) throw new UsernameNotFoundException("User" + username + "not found");
        return new AuthorizedPerson(person);
    }
}
