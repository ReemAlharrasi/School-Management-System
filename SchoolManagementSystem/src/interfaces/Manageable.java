package interfaces;

// Every service (StudentService, TeacherService, ...) can manage a list of items:
// add one, remove one by its id, and give back all of them.
// No generics are used, so we work with plain Object / Object[].
public interface Manageable {

    void add(Object item);          // store one entity

    boolean removeById(String id);  // remove one by id, true if it was found

    Object[] getAll();              // return every stored entity
}
