package ch.fhnw.workshop.web;

import ch.fhnw.workshop.domain.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by roman on 10.03.16.
 */
public class BaseController <M extends BaseModel, R extends JpaRepository<M, Long>> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected R baseRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<M> create(@Valid @RequestBody M entity) {
        entity = baseRepository.save(entity);
        log.debug("Created entity");
        return new ResponseEntity<M>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<M> update(@Valid @RequestBody M entity) {
        entity = baseRepository.save(entity);
        log.debug("Updated entity");
        return new ResponseEntity<M>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        baseRepository.delete(id);
        log.debug("Deleted entity");
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
