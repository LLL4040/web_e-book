package backend.ServiceImpl;

import backend.Entity.BookSolr;
import backend.Service.BookSolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookSolrServiceImpl implements BookSolrService {
    @Autowired
    SolrClient solrClient;

    @Override
    public void add(Map<String, String> data) {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("isbn", data.get("isbn"));
        document.setField("bookname", data.get("bookname"));
        document.setField("author", data.get("author"));
        document.setField("amount", data.get("amount"));
        document.setField("price", data.get("price"));
        document.setField("authorInfo", data.get("authorinfo"));
        document.setField("contentInfo", data.get("contentinfo"));
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String isbn) {
        try {
            solrClient.deleteById(isbn);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BookSolr> queryAll(String keyword) {
        List<BookSolr> books = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery("contentInfo:*" + keyword + "*");
        solrQuery.setSort("isbn", SolrQuery.ORDER.asc);
        try {
            QueryResponse queryResponse = solrClient.query(solrQuery);
            if (queryResponse != null) {
                books = queryResponse.getBeans(BookSolr.class);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}
