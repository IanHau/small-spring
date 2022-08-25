package cn.ian.springframework.beans;

public class BeansException extends Throwable {

    public BeansException(String msg,Exception e) {
        super(msg,e);
    }

    public BeansException(String message) {
        super(message);
    }
}
