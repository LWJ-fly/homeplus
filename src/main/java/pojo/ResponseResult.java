package pojo;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-03-30 13:38
 **/
public class ResponseResult<T> {

    private Integer state = 200;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(int i, Exception e) {
        super();
        this.state = i;
        this.message = e.getMessage();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
