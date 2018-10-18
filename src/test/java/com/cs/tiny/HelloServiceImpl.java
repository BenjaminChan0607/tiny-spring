package com.cs.tiny;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 2:48
 */
public class HelloServiceImpl implements HelloService {

    private String text;
    private PropertyService propertyService;

    public void setText(String text) {
        this.text = text;
    }

    public void setPropertyService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public void hello() {
        System.out.println("hello service");
//        propertyService.property();
    }
}
