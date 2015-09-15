package example;

import javax.xml.ws.Endpoint;

/**
 * Created by raul on 30/08/15.
 */
public class HelloWorldPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorld());
    }
}
