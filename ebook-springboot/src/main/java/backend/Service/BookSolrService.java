package backend.Service;

import backend.Entity.BookSolr;

import java.util.List;
import java.util.Map;

public interface BookSolrService {
    void add(Map<String, String> data);
    void delete(String isbn);
    List<BookSolr> queryAll(String keyword);
}
