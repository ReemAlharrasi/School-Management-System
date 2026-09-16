package interfaces;

// Every service can also be searched: by a keyword, or by an exact id.
public interface Searchable {

    Object[] search(String keyword);  // all items that match the keyword

    Object searchById(String id);     // the single item with this id, or null
}
