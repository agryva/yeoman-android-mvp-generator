package <%= packageName %>;

import lombok.Data;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */

@Data
public class BaseDaoTest<T> {
    private int page;
    private int total_results;
    private int total_pages;
    private T results;

    public T getResults() {
        return results;
    }
}
