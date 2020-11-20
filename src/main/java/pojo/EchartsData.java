package pojo;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-04-20 15:33
 **/
public class EchartsData {
    private String name;
    private Integer num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "EchartsData{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
