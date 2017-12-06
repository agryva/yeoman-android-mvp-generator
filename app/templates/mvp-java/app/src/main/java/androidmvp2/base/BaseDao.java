package <%= packageName %>;

import lombok.Data;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */

@Data
public class BaseDao<T> {
    private boolean status;
    private int status_code;
    private String message;
    private T data;
}
